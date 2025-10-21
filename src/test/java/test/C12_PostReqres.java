package test;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ReqresUserPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C12_PostReqres extends ReqresBaseUrl {

/*
    Given: Base URL: https://reqres.in/api/users
    Request Body:
    {
    "name": "morpheus",
    "job": "leader"
    }
    When: Send a POST request to the URL
    Then: Assert the status code is 201
    Verify the response body matches the structure:
    {
    "name": "morpheus",
    "job": "leader",
    "id": "496",
    "createdAt": "2022-10-04T15:18:56.372Z"
    }
    Note: Add authentication header: "x-api-key" with value "reqres-free-v1"
*/

    @Test
    void postReqresTest() {

        //Prepare the expected data
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("name", "morpheus");
        expectedData.put("job", "leader");

        //Send the request
        Response response = given(spec).body(expectedData).post("/api/users");
        response.prettyPrint();

        //Do assertion
        Map actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(),201);
        assertEquals(actualData.get("name"),expectedData.get("name"));
        assertEquals(actualData.get("job"),expectedData.get("job"));


    }

    @Test
    void postReqresPojoTest() {

        //Prepare the expected data
        ReqresUserPojo expectedData = new ReqresUserPojo("morpheus", "leader");

        //Send the request
        Response response = given(spec).body(expectedData).post("/api/users");
        response.prettyPrint();

        //Do assertion
        ReqresUserPojo actualData = response.as(ReqresUserPojo.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.getName(), expectedData.getName());
        assertEquals(actualData.getJob(), expectedData.getJob());
    }
}
