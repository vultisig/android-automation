package utils;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;

public class GmailOtpFetcher {

    /**
     * Fetch 4-digit OTP from Gmail Inbox
     * @param userEmail     Your Gmail address
     * @param appPassword   16-character App Password (without spaces)
     * @param senderKeyword Part of the sender email or name (e.g., "vultisig")
     * @param timeoutSec    How long to wait for OTP
     * @return OTP as String
     */
    public static String fetchOtp(String userEmail, String appPassword, String senderKeyword, int timeoutSec) throws Exception {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail.imaps.ssl.enable", "true");

        Session session = Session.getInstance(props, null);
        Store store = session.getStore("imaps");
        String normalizedAppPassword = appPassword == null ? "" : appPassword.replaceAll("\\s+", "");
        if (normalizedAppPassword.length() != 16) {
            throw new IllegalArgumentException("App password must be 16 characters (spaces are ignored).");
        }
        store.connect("imap.gmail.com", userEmail, normalizedAppPassword);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        long endTime = System.currentTimeMillis() + timeoutSec * 1000L;
        String otp = null;

        System.out.println("📩 Checking for new OTP emails...");

        while (System.currentTimeMillis() < endTime) {
            Message[] messages = inbox.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false)
            );

// 🔥 Process newest emails first
            for (int i = messages.length - 1; i >= 0; i--) {

                Message message = messages[i];

                String from = message.getFrom()[0].toString().toLowerCase();

                if (from.contains(senderKeyword.toLowerCase())) {

                    Object content = message.getContent();
                    String bodyText;

                    if (content instanceof String) {
                        bodyText = (String) content;
                    } else if (content instanceof MimeMultipart) {
                        bodyText = getTextFromMimeMultipart((MimeMultipart) content);
                    } else {
                        bodyText = "";
                    }

                    Pattern p = Pattern.compile("\\b(\\d{4})\\b");
                    Matcher m = p.matcher(bodyText);

                    if (m.find()) {
                        otp = m.group(1);
                        message.setFlag(Flags.Flag.SEEN, true);
                        System.out.println("✅ OTP Found (latest): " + otp);
                        break;
                    }
                }
            }


            if (otp != null) break;

            System.out.println("⏳ Waiting for OTP...");
            Thread.sleep(3000);
        }

        // ✅ Once OTP fetched, delete from Inbox, All Mail, and Trash it
        if (otp != null) {
            System.out.println("🧹 Clearing Inbox and All Mail after OTP fetch...");
            deleteFromFolder(store, "INBOX");
            deleteFromFolder(store, "[Gmail]/All Mail");
            moveAllToTrash(store);
        }

        inbox.close(true); // expunge deleted messages
        store.close();

        if (otp == null) {
            throw new RuntimeException("❌ OTP not received in time");
        }

        return otp;
    }

    // 🔽 Extract text from HTML/Multipart emails
    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append(Jsoup.parse(html).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }

    // 🔽 Delete messages from a specific Gmail folder
    private static void deleteFromFolder(Store store, String folderName) {
        try {
            Folder folder = store.getFolder(folderName);
            if (folder.exists()) {
                folder.open(Folder.READ_WRITE);
                Message[] allMessages = folder.getMessages();
                for (Message msg : allMessages) {
                    msg.setFlag(Flags.Flag.DELETED, true);
                }
                folder.close(true); // expunge deleted messages
                System.out.println("🗑️ Deleted messages from " + folderName);
            } else {
                System.out.println("⚠️ Folder not found: " + folderName);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Failed to delete from " + folderName + ": " + e.getMessage());
        }
    }

    // 🔽 Move remaining messages to Trash for full deletion
    private static void moveAllToTrash(Store store) {
        try {
            Folder allMail = store.getFolder("[Gmail]/All Mail");
            Folder trash = store.getFolder("[Gmail]/Trash");

            if (allMail.exists() && trash.exists()) {
                allMail.open(Folder.READ_WRITE);
                trash.open(Folder.READ_WRITE);

                Message[] allMessages = allMail.getMessages();
                if (allMessages.length > 0) {
                    allMail.copyMessages(allMessages, trash);
                    for (Message msg : allMessages) {
                        msg.setFlag(Flags.Flag.DELETED, true);
                    }
                    System.out.println("🚮 Moved all messages to Trash.");
                }

                allMail.close(true);
                trash.close(true);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error moving mails to Trash: " + e.getMessage());
        }
    }
}
