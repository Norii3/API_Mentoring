package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class GoRestBaseUrl {

    protected RequestSpecification spec;
    private static String baseUrl = "https://gorest.co.in";

    @BeforeMethod
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer ed3d823cad051cc8500d856807b8ba16c22be4a15ae63b20945d97805dcc4b9a")
                .build();
    }

}