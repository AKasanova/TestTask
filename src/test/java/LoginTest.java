import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private static final String BASE_URL = "https://api.sa-tech.de";
    private static final String TENANT = "ch";
    private static final String USERNAME = "shop-user@redteclab.com";
    private static final String PASSWORD = "aA1!bB2@cC3#dD4$";
    private static final String DEVICE_TYPE = "browser";

    @Test
    public void testLogin() {
        // set base URI
        RestAssured.baseURI = BASE_URL;

        // make the request and save the response
        Response response = null;
            response = given()
                    .pathParam("tenant", TENANT)
                    .header("Sae-Device-Type", DEVICE_TYPE)
                    .contentType(ContentType.JSON)
                    .body("{ \"username\": \"" + USERNAME + "\", \"password\": \"" + PASSWORD + "\" }")
                    .when()
                    .post("/auth/v2/{tenant}/login")
                    .then()
                    .extract()
                    .response();

            // assert the response code

            Assertions.assertThat(response.getStatusCode())
                    .as("wrong status code \n"+ response.asString())
                    .isEqualTo(HttpStatus.SC_CREATED);

    }
    }


