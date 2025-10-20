package test;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class C06_Groovy extends ReqresBaseUrl {

    /*
    Given:
        URL: https://reqres.in/api/users
    When
        Send get request
    Then
        Status code should be 200
    And
        'Emma's email is "emma.wong@reqres.in"
    And
        Total number of users is 6
     */

    @Test
    void groovyTest() {

        //Send the request
        Response response = given(spec).get("/api/users");
        response.prettyPrint();

        //Do assertion
        JsonPath jsonPath = response.jsonPath();
        assertEquals(response.statusCode(), 200);
        String emmaEmail = jsonPath.getString("data.find{it.first_name=='Emma'}.email");
        assertEquals(emmaEmail, "emma.wong@reqres.in");
        int totalNumber = jsonPath.getInt("data.size()");
        assertEquals(totalNumber, 6);



    }



}
