package utils;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class Base {

    ConfigReader configReader = new ConfigReader();
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI=configReader.getBaseUrl();

    }
}
