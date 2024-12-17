package utils;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ApiUtils {

    static {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }

    public Response createObject(String payload, String path) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .log().body()
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public Response getAllObject(String path) {
        return given()
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public Response getObjectById(String value, String path) {
        return given()
                .pathParam("id", value)
                .log().all()
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public Response updateObject(String value, String payload, String path) {
        return given()
                .pathParam("id", value)
                .body(payload)
                .contentType(ContentType.JSON).when()
                .put(path)
                .then()
                .extract()
                .response();
    }

    public Response deleteObject(String value, String path) {
        return given()
                .pathParam("id", value)
                .when()
                .delete(path)
                .then()
                .extract()
                .response();
    }
}
