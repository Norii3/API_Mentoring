package test.go_rest;

import base_urls.GoRestBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static utilities.ObjectMapperUtils.getJsonNode;

public class R02_CreateUser extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v2/users
    And
        {
            "name":"John",
            "gender":"male",
            "email":"john.doe@gmail.com",
            "status":"active"
         }
    When
        Send the post request
    Then
        Status code should be 201
    And
        Response body should be like:

    */

    public static int userId;
    public static String randomEmail;
    @Test
    void createUserTest(){

        //Set the payload
        JsonNode payload = getJsonNode("user_body");
        randomEmail = Math.random()+"@gmail.com";
        ((ObjectNode) payload).put("email",randomEmail);

        //Send the request
        Response response = given(spec).body(payload).post("/public/v2/users");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .time(lessThan(2000L))
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo(payload.get("email").asText()),
                        "gender", equalTo(payload.get("gender").asText()),
                        "status", equalTo(payload.get("status").asText())
                );

        userId = response.jsonPath().getInt("id");

    }
}
