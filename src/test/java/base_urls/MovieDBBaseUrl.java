package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class MovieDBBaseUrl {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://api.themoviedb.org")
                .addQueryParam("api_key","2eeebe74d17da380e718f9066997a62a")
                .build();
    }

}
