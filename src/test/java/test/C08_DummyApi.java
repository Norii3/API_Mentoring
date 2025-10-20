package test;

import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C08_DummyApi extends DummyApiBaseUrl {

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends GET request
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among them
    And
        Highest age = 66
    And
        Youngest = "Tatyana Fitzpatrick"
    And
        Total salary = 6,644,770
     */

    @Test
    void dummyApiTest() {

        //Send the request
        Response response = given(spec).get("/api/v1/employees");
        response.prettyPrint();

        //Do assertion
        JsonPath jsonPath = response.jsonPath();

//        Status code = 200
        assertEquals(response.statusCode(), 200);

//        There are 24 employees
        assertEquals(jsonPath.getInt("data.size()"), 24);

//        "Tiger Nixon" and "Garrett Winters" are among them
        assert jsonPath.getBoolean("data.any{it.employee_name.contains('Tiger Nixon')}");
        assert jsonPath.getBoolean("data.any{it.employee_name.contains('Garrett Winters')}");

//        Highest age = 66
        int maxAge = jsonPath.getInt("data.max{it.employee_age}.employee_age");
        assertEquals(maxAge, 66);

//        Youngest = "Tatyana Fitzpatrick"
        String youngestEmployee = jsonPath.getString("data.min{it.employee_age}.employee_name");
        assertEquals(youngestEmployee, "Tatyana Fitzpatrick");

//        Total salary = 6,644,770
        assertEquals(jsonPath.getInt("data.collect{it.employee_salary.toInteger()}.sum()"), 6644770);



    }


}