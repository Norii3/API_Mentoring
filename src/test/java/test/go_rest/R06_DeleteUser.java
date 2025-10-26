package test.go_rest;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.lessThan;
import static test.go_rest.R02_CreateUser.userId;

public class R06_DeleteUser extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v2/users/userId
    When
        Send the delete request
    Then
        Status code should be 204
    And
        Response body should be empty
    */
    @Test
    void deleteUserTest() {

        //Send the request
        Response response = given(spec).delete("/public/v2/users/" + userId);
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(204)
                .time(lessThan(3000L))
                .body(emptyString());
    }
}

