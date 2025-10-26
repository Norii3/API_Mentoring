package test.go_rest;

import base_urls.GoRestBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static test.go_rest.R02_CreateUser.randomEmail;
import static test.go_rest.R02_CreateUser.userId;
import static utilities.ObjectMapperUtils.getJsonNode;
import static utilities.ObjectMapperUtils.setJsonNode;

public class R04_UpdateUser extends GoRestBaseUrl {

    @Test
    void updateUserTest(){

        //Prepare the payload
        JsonNode payload = getJsonNode("user_body_updated");
        randomEmail = Math.random() + "@sda.com";
        setJsonNode(payload, "email", randomEmail);

        //Send the request
        Response response = given(spec)
                .body(payload)
                .put("/public/v2/users/" + userId);
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo(randomEmail),
                        "gender", equalTo(payload.get("gender").asText()),
                        "status", equalTo(payload.get("status").asText())
                );
    }
}
