package test;

import base_urls.FakeStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C10_CreateProductMap extends FakeStoreBaseUrl {

    @Test
    void createProductStringTest() {

        //Set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("title", "Book");
        expectedData.put("price", 5);
        expectedData.put("description", "History of the human beings");
        expectedData.put("category", "History");
        expectedData.put("image", "http://example.com");

        //Send the request
        Response response = given(spec).body(expectedData).post("/products");
        response.prettyPrint();

        //Do assertion
        Map actualData = response.as(Map.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("title"), expectedData.get("title"));
        assertEquals(actualData.get("price"), expectedData.get("price"));
        assertEquals(actualData.get("description"), expectedData.get("description"));
        assertEquals(actualData.get("image"), expectedData.get("image"));
        assertEquals(actualData.get("category"), expectedData.get("category"));



    }

}