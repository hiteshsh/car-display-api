package tests;

import org.junit.BeforeClass;
import utils.PropertiesReader;

public class BaseTest {

    protected final String wa_key="";

    @BeforeClass
    public static void setUp(){
        PropertiesReader.loadProperties();
    }
}
