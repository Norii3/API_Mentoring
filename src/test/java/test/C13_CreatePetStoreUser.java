package test;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PetStoreUserPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class C13_CreatePetStoreUser extends PetStoreBaseUrl {

    PetStoreUserPojo expectedData;

    @Test
    void createPetStoreUserTest(){

        expectedData = new PetStoreUserPojo("69865724", "DavidJohn", "David", "John", "davidJohn@gmail.com", "davi.123", "123456", 0);

        //Send the request
        Response response = given(spec).body(expectedData).post("/v2/user");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .body("code", equalTo(200),
        "type", equalTo("unknown"),
        "message", equalTo(expectedData.getId()));
    }

    @Test
    void getPetStoreUserTest(){

        //Send the request
        Response response = given(spec).get("/v2/user/" + expectedData.getUsername());
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .body("username", equalTo(expectedData.getUsername()),
                        "firstName", equalTo(expectedData.getFirstName()),
                        "lastName", equalTo(expectedData.getLastName()),
                        "email", equalTo(expectedData.getEmail()),
                        "password", equalTo(expectedData.getPassword()),
                        "phone", equalTo(expectedData.getPhone()),
                        "userStatus", equalTo(expectedData.getUserStatus())
                );
    }
}
