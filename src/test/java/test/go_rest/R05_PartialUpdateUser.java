package test.go_rest;

import base_urls.GoRestBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static test.go_rest.R02_CreateUser.randomEmail;
import static test.go_rest.R02_CreateUser.userId;
import static utilities.ObjectMapperUtils.*;

public class R05_PartialUpdateUser extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v2/users/userId
    And
        {
            "name":"John"
         }

    When
        Send the put request
    Then
        Status code should be 200
    And
        Response body should be like:
         {
            "id":321435,
            "name":"John",
            "gender":"male",
            "email":"john.doe@gmail.com",
            "status":"active"
         }

    */

    @Test
    void partialUpdateUserTest(){

        //Set the payload
        JsonNode payload = getJsonNode("user_body");
        setJsonNode(payload, "name", "Liam");
        removeField(payload, "email");
        removeField(payload, "gender");
        removeField(payload, "status");
        System.out.println(payload);

        //Send the request
        Response response = given(spec)
                .body(payload)
                .patch("/public/v2/users/" + userId);
        response.prettyPrint();

        //Do assertion
        JsonNode updatedUser = getJsonNode("user_body_updated");
        response
                .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo(randomEmail),
                        "gender", equalTo(updatedUser.get("gender").asText()),
                        "status", equalTo(updatedUser.get("status").asText())
                );
    }
}
