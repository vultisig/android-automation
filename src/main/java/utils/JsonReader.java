package utils;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

public class JsonReader {
    public static JSONObject read(String file) {
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(file);
        assert is != null;
        return new JSONObject(new JSONTokener(is));
    }
}
