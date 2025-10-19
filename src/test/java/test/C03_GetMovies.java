package test;

import base_urls.MovieDBBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C03_GetMovies extends MovieDBBaseUrl {
    /*
        https://api.themoviedb.org/3/tv/popular?api_key=2eeebe74d17da380e718f9066997a62a
        Objective:
        Test the popular TV shows API endpoint using various Hamcrest matchers
        Test Requirements:
        1. Verify that the "page" field in the response equals 1
        2. Check that the "results" array contains items with names "Peacemaker" and "One-Punch Man"
        3. Assert that the "overview" of the first item in "results" contains the substring "Ed Gein"
        4. Confirm that the "results" array has a size of 20 items
        5. Verify that the "vote_count" for "Game of Thrones" in the results is greater than 25000
        6. Check that the "results" field is an instance of a List/Array
     */

    @Test
    void getMoviesTest(){

        Response response = given(spec).get("/3/tv/popular");

        response.prettyPrint();

//        1. Verify that the "page" field in the response equals 1
//        2. Check that the "results" array contains items with names "Peacemaker" and "One-Punch Man"
//        3. Assert that the "overview" of the first item in "results" contains the substring "Ed Gein"
//        4. Confirm that the "results" array has a size of 20 items
//        5. Verify that the "vote_count" for "Game of Thrones" in the results is greater than 25000
//        6. Check that the "results" field is an instance of a List/Array




    }



}
