package seleniumMail.helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {

        try {
            FileReader reader = new FileReader("src/test/java/resources/credentials.properties");
            PROPERTIES.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
