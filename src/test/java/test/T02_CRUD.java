package test;

import base_urls.CRUDActiviteUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ActivitiesPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class T02_CRUD extends CRUDActiviteUrl {

    /*
        Task: Write code that performs all CRUD operations on "activities"
        using the Fake REST API at https://fakerestapi.azurewebsites.net
        Requirements:
        1. Use POJO classes for all operations
        2. Implement CREATE (POST) - Add new activity
        3. Implement READ (GET) - Retrieve activity details
        4. Implement UPDATE (PUT) - Modify existing activity
        5. Implement DELETE - Remove activity
        6. Add appropriate assertions for each operation
*/
    ActivitiesPojo expectedData;

    //post
    @Test(priority = 1)
    void createUserTest() {
        expectedData = new ActivitiesPojo(1, "Activity 1", "2025-10-21T13:11:44.8085893+00:00", true);

        //Send the request
        Response response = given(spec).body(expectedData).post("/api/v1/Activities/");
        response.prettyPrint();

        ActivitiesPojo actualData = response.as(ActivitiesPojo.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getId(), expectedData.getId());
        assertEquals(actualData.getTitle(), expectedData.getTitle());
        assertEquals(actualData.getDueDate(), expectedData.getDueDate());
        assertEquals(actualData.getCompleted(), expectedData.getCompleted());

    }

    @Test(priority = 2)
    void getUserTest(){

        Response response = given(spec).get("/api/v1/Activities/1");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

    }

    @Test(priority = 3)
    void updateUserTest(){

        ActivitiesPojo updatedData = new ActivitiesPojo(3, "Coffee", "2025-11-11T00:00:00", false);
        Response response = given(spec).body(updatedData).put("/api/v1/Activities/1");
        response.prettyPrint();
        ActivitiesPojo actualData = response.as(ActivitiesPojo.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getTitle(), "Coffee");
        assertEquals(actualData.getDueDate(), "2025-11-11T00:00:00");
        assertEquals(actualData.getCompleted(), false);
    }

    @Test(priority = 4)
    void deleteUserTest(){
        Response response = given(spec).delete("/api/v1/Activities/3");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }
}
