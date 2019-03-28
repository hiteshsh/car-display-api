package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static Properties properties;

    public static void loadProperties(){
        if (properties==null)
            properties= createProperties();
    }

    private static Properties createProperties(){
        InputStream is = null;
        String env = System.getProperty("environment");
        if (env!=null && env.equalsIgnoreCase("test")) {
            is = PropertiesReader.class.getResourceAsStream("/properties" +  File.separator  + "test.properties");
        }

        Properties prop = new Properties();

        try {
            prop.load(is);
        } catch (IOException e) {

        }

        return prop;
    }

    public String getCarDisplayBaseUrl(){
        return properties.getProperty("CAR_DISPLAY_BASE_URL");
    }
}
