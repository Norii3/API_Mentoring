package test;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C05_JsonPathReqres extends ReqresBaseUrl {


    /*

**1. URL:**
`https://reqres.in/api/unknown/3`

**2. Test Steps:**

* **Given:** The API endpoint URL.
* **When:** A `GET` request is sent to the URL.
* **Then:**
{
  "data": {
    "id": 3,
    "name": "true red",
    "year": 2002,
    "color": "#BF1932",
    "pantone_value": "19-1664"
  },
  "support": {
    "url": "https://reqres.in/#support-heading",
    "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
  }
}
     */

    @Test
    void C05_JsonPathReqresTest(){

        //Send the request
        Response response = given(spec).get("api/unknown/3");
        response.prettyPrint();

        //Do assertion
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.getString("data.name");
        int year = jsonPath.getInt("data.year");
        String color = jsonPath.getString("data.color");
        String pantoneValue = jsonPath.getString("data.pantone_value");

        assertEquals(response.statusCode(), 200);
        assertEquals(name, "true red");
        assertEquals(year, 2002);
        assertEquals(color, "#BF1932");
        assertEquals(pantoneValue, "19-1664");
    }
}
