package test;

import base_urls.BookerBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class C04_JsonPath extends BookerBaseUrl {

/*
    URL: https://restful-booker.herokuapp.com/booking/283
    Send get request to the URl
    Assert the status code is 200
    Response body should be like:
    {
    "firstname": "Jane",
    "lastname": "Doe",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Extra pillows please"
    }
*/

    @Test
    void jsonPathTest() {

        //Send the request
        Response response = given(spec).get("/booking/42");
        response.prettyPrint();

        //Do assertion
//        response
//                .then()
//                .statusCode(200)
//                .body(
//                        "firstname", equalTo("Jane"),
//                        "lastname", equalTo("Doe"),
//                        "totalprice", equalTo(111),
//                        "depositpaid", equalTo(true),
//                        "bookingdates.checkin", equalTo("2018-01-01"),
//                        "bookingdates.checkout", equalTo("2019-01-01"),
//                        "additionalneeds", equalTo("Extra pillows please")
//                );

        //Beside this, you can retrive the data from body and use in assertion or anywhere.
        //Create JsonPath Object using response
        JsonPath jsonPath = response.jsonPath();
        assertEquals(response.statusCode(), 200);
        assertEquals(jsonPath.getString("firstname"), "Jane");
        assertEquals(jsonPath.getString("lastname"), "Doe");
        assertEquals(jsonPath.getInt("totalprice"), 111);
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01");
        assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01");
        assertEquals(jsonPath.getString("additionalneeds"), "Extra pillows please");

    }
}
