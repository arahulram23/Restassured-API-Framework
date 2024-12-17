package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matcher.*;

public class BasicCRUDTest {
    ApiUtils apiUtils = new ApiUtils();
    String payload = "{\n" +
            "   \"name\": \"xxs IPHONE xx\",\n" +
            "   \"data\": {\n" +
            "      \"year\": 2023,\n" +
            "      \"price\": 1849,\n" +
            "      \"CPU model\": \"M1\",\n" +
            "      \"Hard disk size\": \"1 TB\"\n" +
            "   }\n" +
            "}\n";
    String putPayload = "{\n" +
            "   \"name\": \"KK's IPHONE xx\",\n" +
            "   \"data\": {\n" +
            "      \"year\": 2023,\n" +
            "      \"price\": 1849,\n" +
            "      \"CPU model\": \"M1\",\n" +
            "      \"Hard disk size\": \"1 TB\"\n" +
            "   }\n" +
            "}\n";
    String basePath = "https://api.restful-api.dev/objects";
    String idBasePath = "https://api.restful-api.dev/objects/{id}";
    String id;

    @Test(priority = 0)
    public void createObjectTest() {

        Response object = apiUtils.createObject(payload, basePath);
        System.out.println(object.asPrettyString());
        id = (String) object.path("id");


    }

    @Test(priority = 1,dependsOnMethods = "createObjectTest")
    public void getObjectTest() {
        Object id1 = apiUtils.getObjectById(basePath+"/"+id).path("id");
        Assert.assertEquals(id, id1);
    }

    @Test(priority = 2)
    public void modifyObjectTest() {
        Object name = apiUtils.updateObject(putPayload, basePath+"/"+id).path("name");
        Assert.assertEquals((String) name, "KK's IPHONE xx");
    }

    @Test(priority = 3)
    public void deleteObjectTest() {
        Response response = apiUtils.deleteObject(basePath+"/"+id);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

}
