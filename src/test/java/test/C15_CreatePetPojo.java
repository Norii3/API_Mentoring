package test;

import base_urls.PetStoreBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.pet_store.PetPojo;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static json_data.PetStoreData.PET_BODY;
import static org.testng.Assert.assertEquals;

public class C15_CreatePetPojo extends PetStoreBaseUrl {
    /*
        Reference the API documentation at https://petstore.swagger.io/
        Send a POST request to the create pet endpoint with your POJO as the body
        Assert that the response status code is successful (200 or 201)
        Assert that the returned pet object contains the data you sent
     */

    @Test
    void C15_CreatePetPojoTest() throws JsonProcessingException {

        //Prepare the payload
        PetPojo payload = new ObjectMapper().readValue(PET_BODY, PetPojo.class);
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/v2/pet");
        response.prettyPrint();

        //Do assertion
        PetPojo actualData = response.as(PetPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getId(), payload.getId());
        assertEquals(actualData.getCategory().getId(), payload.getCategory().getId());
        assertEquals(actualData.getCategory().getName(), payload.getCategory().getName());
        assertEquals(actualData.getName(), payload.getName());
        assertEquals(actualData.getPhotoUrls().getFirst(), payload.getPhotoUrls().getFirst());
        assertEquals(actualData.getPhotoUrls().getLast(), payload.getPhotoUrls().getLast());
        assertEquals(actualData.getTags().getFirst().getId(), payload.getTags().getFirst().getId());
        assertEquals(actualData.getTags().getFirst().getName(), payload.getTags().getFirst().getName());
        assertEquals(actualData.getTags().getLast().getId(), payload.getTags().getLast().getId());
        assertEquals(actualData.getTags().getLast().getName(), payload.getTags().getLast().getName());
        assertEquals(actualData.getStatus(), payload.getStatus());

    }
}
