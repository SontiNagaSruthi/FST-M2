package Github_RestAssured_Projects;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssured_Project {

    RequestSpecification requestSpec;
    String sshkey;
    int num;
    int keyid;

    @BeforeClass
    public void setUp()
    {
        requestSpec=new RequestSpecBuilder()
                .setBaseUri("https://api.github.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","token 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")
                .build();
    }

    @Test(priority = 1)
    public void testPostRequest()
    {
        String reqBody="{\"title\":\"TestAPIKey\",\"key\":\"ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIEmfCggTWs3kKXBljCzs9/bRSBJrGcWpnpHBGI5GPp0V\"}";
        Response response = given().spec(requestSpec)
                .body(reqBody)
                .when().post("/user/keys");
        System.out.println(response.getBody().asPrettyString());
        keyid = response.then().extract().body().path("id");
        response.then().statusCode(201);
    }

    @Test(priority = 2)
    public void getRequestTest()
    {
        Response response = given().spec(requestSpec)
                .pathParam("keyId",keyid)
                .when().get("/user/keys/{keyId}");
        System.out.println(response.getBody().asPrettyString());
        response.then().statusCode(200);
    }

    @Test(priority = 3)
    public void deleteRequestTest()
    {
        Response response = given().spec(requestSpec)
                .pathParam("keyId",keyid)
                .when().delete("/user/keys/{keyId}");
        System.out.println(response.getBody().asPrettyString());
        response.then().statusCode(204);

    }
}
