package test;

import config.ConfigReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;

public class BasicCRUDTest {
    ApiUtils apiUtils = new ApiUtils();
    String payload;
    String putPayLoad;
    String id;

    {
        try {
            payload = new String(Files.readAllBytes(Paths.get("src/test/resources/data/postPayLoad.json")));
            putPayLoad = new String(Files.readAllBytes(Paths.get("src/test/resources/data/putPayLoad.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 0)
    public void createObjectTest() {
        Response response = apiUtils.createObject(payload, ConfigReader.getObjectsPath());
        id = response.path("id");
        response.then().log().status();
        System.out.println("payload1" + payload);
    }

    @Test(priority = 1, dependsOnMethods = "createObjectTest")
    public void getObjectTest() {
        Response response = apiUtils.getObjectById(id, ConfigReader.getObjectIdPath());
        response.then().body("id", is(id));
    }

    @Test(priority = 2)
    public void modifyObjectTest() {
        Object name = apiUtils.updateObject(id, putPayLoad, ConfigReader.getObjectIdPath()).path("name");
        Assert.assertEquals((String) name, "KK's IPHONE xx");
    }

    @Test(priority = 3)
    public void deleteObjectTest() {
        Response response = apiUtils.deleteObject(id, ConfigReader.getObjectIdPath());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

}
