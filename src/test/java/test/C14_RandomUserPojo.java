package test;

import base_urls.RandomUserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.random_user.RandomUserPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class C14_RandomUserPojo extends RandomUserBaseUrl {

/*
    Send a GET request to
    https://randomuser.me/api
    Deserialize the response into a POJO class
    Assert that the following fields are NOT null:
        Email
        Username
        Password
        Medium picture URL
 */

    @Test
    void randomUserPojoTest() {

        //Send the request
        Response response = given(spec).get("/api");
        response.prettyPrint();

        //Do assertion
        RandomUserPojo actualData = response.as(RandomUserPojo.class);
        assertEquals(response.statusCode(), 200);
//        Assert that the following fields are NOT null:
//        Email
        assertNotNull(actualData.getResults().getFirst().getEmail());
//        Username
        assertNotNull(actualData.getResults().getFirst().getLogin().getUsername());
//        Password
        assertNotNull(actualData.getResults().getFirst().getLogin().getPassword());
//        Medium picture URL
        assertNotNull(actualData.getResults().getFirst().getPicture().getMedium());

    }
}
