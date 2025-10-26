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
import static utilities.ObjectMapperUtils.getJsonNode;

public class R07_GetUserNegative extends GoRestBaseUrl {

     /*
    Given
        https://gorest.co.in/public/v2/users/userId
    When
        Send the get request
    Then
        Status code should be 20o
    And
        Response body should be like:
         {
            "message": "Resource not found"
         }

    */

    @Test
    void getUserTest(){

        //Send the request
        Response response = given(spec).get("/public/v2/users/" + userId);
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(404)
                .time(lessThan(2000L))
                .body("message", equalTo("Resource not found"));
    }
}
