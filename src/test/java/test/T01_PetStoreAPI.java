package test;

import base_urls.BeasUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class T01_PetStoreAPI extends BeasUrl {

/*

            Task 2: Pet Store API - Sold Pets Analysis

        API Documentation: https://petstore.swagger.io/
        Objective: Test sold pets endpoint and verify the number of sold pets is greater than 5
        API Endpoint:
        GET /pet/findByStatus?status=sold

        Test Requirements:
        Verify that the response array contains at least one item where the status field equals "sold"
        Check that the response array contains items with specific pet names (choose any two names that exist in the response)
        Assert that at least one item in the response array has a name field that contains a specific substring
        Confirm that the response array has a specific size (count the number of items in the array)
        Verify that at least one item in the response array has an id field with a value greater than a specified number
        Check that the response is an instance of a List/Array
        Verify that at least one item in the response array has a name field that starts with a specific prefix
        Check that every item in the response array has a status field with the value "sold"
        Assert that at least one item in the response array has either a specific name or a specific id value

*/


        @Test
        void petStoreAPITest() {

            Response response = given(spec)
                    .queryParam("status", "sold")
                    .when()
                    .get("/v2/pet/findByStatus");
            response.prettyPrint();

            response
                    .then()
//            Verify that the response array contains at least one item where the status field equals "sold"
                    .body("status", hasItem("sold"))
//            Check that the response array contains items with specific pet names (choose any two names that exist in the response)
                    //.body("name", hasItems("Rex", "Bella"))
//            Assert that at least one item in the response array has a name field that contains a specific substring
                    .body("name", hasItem(containsString("o")))
//            Confirm that the response array has a specific size (count the number of items in the array)
                    .body("size()", greaterThan(5))
//            Verify that at least one item in the response array has an id field with a value greater than a specified number
                    .body("id", hasItem(greaterThan(1000)))
//            Check that the response is an instance of a List/Array
                    .body("", instanceOf(List.class))
//            Verify that at least one item in the response array has a name field that starts with a specific prefix
                    .body("name", hasItem(startsWith("S")))
//            Check that every item in the response array has a status field with the value "sold"
                    .body("status", everyItem(equalTo("sold")))
//            Assert that at least one item in the response array has either a specific name or a specific id value
                    .body("", hasItem(hasEntry("name", "Spot")))
            ;

        }

}
