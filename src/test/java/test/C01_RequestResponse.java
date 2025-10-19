package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

public class C01_RequestResponse {

    @Test
    void test1() {
        Response response = RestAssured.get("https://bookstore.demoqa.com/BookStore/v1/Books");
        response.prettyPrint();

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
        System.out.println("statusCode = " + statusCode);

        boolean isTimeLess = response.time()<2000;
        Assert.assertTrue(isTimeLess);

        Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
        Assert.assertEquals(response.header("Server"),"nginx/1.17.10 (Ubuntu)");
        Assert.assertEquals(response.header("Connection"),"keep-alive");
        Assert.assertTrue(response.contentType().contains("application/json"));

        Assert.assertTrue(response.asString().contains("Git Pocket Guide"));


        System.out.println(response.headers());
        response.prettyPrint();


        //Assertions
        response.then().statusCode(200);
        response.then().assertThat().header("Content-Type", containsString("application/json"));
        response.then().assertThat().body("books[0].title", containsString("Git Pocket Guide"));
    }
}
