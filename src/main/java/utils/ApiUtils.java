package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ApiUtils {


    public static Response createObject(String payload, String path) {
        return given().contentType(ContentType.JSON).body(payload).log().body()
                .when().post(path).then().extract().response();
    }

    public Response getAllObject(String path) {
        return given().when().get(path).then().extract().response();
    }

    public Response getObjectById( String path) {
        return given().log().all().when().get(path).then().extract().response();
    }

    public Response updateObject(String payload, String path) {
        return given().body(payload).contentType(ContentType.JSON).when()
                .put(path).then().extract().response();
    }

    public Response deleteObject(String path){
        return given().when().delete(path).then().extract().response();
    }
}
