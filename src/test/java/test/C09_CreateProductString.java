package test;

import base_urls.FakeStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C09_CreateProductString extends FakeStoreBaseUrl {

    @Test
    void createProductStringTest(){

        String expectedData = """
                {
                "title": "Book",
                "price": 5,
                "description": "History of human beings",
                "category": "History",
                "image": "http://example.com"
                }
                """;

        //Send the request
        Response response = given(spec).body(expectedData).post("/products");
        response.prettyPrint();

        //Do assertion
        Map actualData = response.as(Map.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("title"), "Book");
        assertEquals(actualData.get("price"), 5);
        assertEquals(actualData.get("description"), "History of human beings");
        assertEquals(actualData.get("category"), "History");
        assertEquals(actualData.get("image"), "http://example.com");




    }
}
