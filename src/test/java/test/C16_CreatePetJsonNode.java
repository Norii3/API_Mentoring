package test;

import base_urls.PetStoreBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.pet_store.PetPojo;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static json_data.PetStoreData.PET_BODY;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class C16_CreatePetJsonNode extends PetStoreBaseUrl {
    /*
        Reference the API documentation at https://petstore.swagger.io/
        Send a POST request to the create pet endpoint with your POJO as the body
        Assert that the response status code is successful (200 or 201)
        Assert that the returned pet object contains the data you sent
     */

    @Test
    void C15_CreatePetPojoTest() throws IOException {

        //Prepare the payload
        JsonNode payload = new ObjectMapper().readTree(new File("src/test/resources/test_data/pet_body.json"));
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/v2/pet");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "id", equalTo(payload.get("id").intValue()),
                        "category.id", equalTo(payload.get("category").get("id").intValue()),
                        "category.name", equalTo(payload.get("category").get("name").textValue()),
                        "name", equalTo(payload.get("name").textValue()),
                        "photoUrls[0]", equalTo(payload.get("photoUrls").get(0).textValue()),
                        "photoUrls[1]", equalTo(payload.get("photoUrls").get(1).textValue()),
                        "tags[0].id", equalTo(payload.get("tags").get(0).get("id").intValue()),
                        "tags[0].name", equalTo(payload.get("tags").get(0).get("name").textValue()),
                        "tags[1].id", equalTo(payload.get("tags").get(1).get("id").intValue()),
                        "tags[1].name", equalTo(payload.get("tags").get(1).get("name").textValue()),
                        "status", equalTo(payload.get("status").textValue())
                );


    }
}