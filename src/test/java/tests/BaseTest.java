package tests;

import org.junit.BeforeClass;
import utils.PropertiesReader;

public class BaseTest {

    protected final String wa_key="coding-puzzle-client-449cc9d";

    @BeforeClass
    public static void setUp(){
        PropertiesReader.loadProperties();
    }
}
