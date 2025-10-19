package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class C02_Assertion {

    @Test
    void assertionTest() {
        Response response = RestAssured.get("");
        response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("title", equalTo("at nam consequatur ea labore ea harum"));
    }
}
