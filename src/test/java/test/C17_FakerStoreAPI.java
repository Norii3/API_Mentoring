package test;

import base_urls.FakeStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C17_FakerStoreAPI extends FakeStoreBaseUrl {
    /*
    Task 3: Fake Store API - Create a Cart with JsonNode Payload
    Objective: Create a new shopping cart using the Fake Store API with JsonNode for dynamic payload handling.
    Requirements:
    Reference the API documentation at https://fakestoreapi.com/docs
    Use JsonNode to create the request payload dynamically
    Create a cart with properties like:
    userId
    date
    products (array of product objects with productId and quantity)
    Modify the JsonNode to add additional fields as needed
    Send a POST request to the create cart endpoint
    Assert that the response status code indicates success
    Assert that the returned cart contains the expected data
     */
    ///Create a new shopping cart using the Fake Store API with JsonNode for dynamic payload handling.
    //https://fakestoreapi.com/carts

    @Test
    void fakerStoreAPITest() throws IOException {

        //Prepare the payload
        JsonNode payload = new ObjectMapper().readTree(new File("src/test/resources/test_data/cart_body.json"));
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/carts");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body(
                        "userId", equalTo(payload.get("userId").intValue()),
                        "products[0].id", equalTo(payload.get("products").get(0).get("id").intValue()),
                        "products[0].title", equalTo(payload.get("products").get(0).get("title").textValue()),
                        "products[0].price", equalTo(payload.get("products").get(0).get("price").intValue()),
                        "products[0].description", equalTo(payload.get("products").get(0).get("description").textValue()),
                        "products[0].category", equalTo(payload.get("products").get(0).get("category").textValue()),
                        "products[0].image", equalTo(payload.get("products").get(0).get("image").textValue()),
                        "products[1].id", equalTo(payload.get("products").get(1).get("id").intValue()),
                        "products[1].title", equalTo(payload.get("products").get(1).get("title").textValue()),
                        "products[1].price", equalTo(payload.get("products").get(1).get("price").intValue()),
                        "products[1].description", equalTo(payload.get("products").get(1).get("description").textValue()),
                        "products[1].category", equalTo(payload.get("products").get(1).get("category").textValue()),
                        "products[1].image", equalTo(payload.get("products").get(1).get("image").textValue())
                );
    }
}
