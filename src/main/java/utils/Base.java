package utils;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class Base {

    ConfigReader configReader = new ConfigReader();
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI=configReader.getBaseUrl();
    }
}
