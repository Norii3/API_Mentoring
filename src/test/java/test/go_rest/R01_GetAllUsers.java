package test.go_rest;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class R01_GetAllUsers extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v2/users
    When
        Send the get request
    Then
        Status code should be 200
    And
        Response body should have keys:
        id, name, email, gender, status
   */

    @Test
    void getAllUsersTest(){

        //Send the request https://gorest.co.in/public/v2/users
        Response response = given(spec).get("/public/v2/users");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body(
                        "[0].id", notNullValue(),
                        "[0].name", notNullValue(),
                        "[0].email", notNullValue(),
                        "[0].gender", notNullValue(),
                        "[0].status", notNullValue()
                );
    }
}
