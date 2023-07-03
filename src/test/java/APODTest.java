import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APODTest {

    private String URL = "https://api.nasa.gov/planetary/";

    @Test
    public void checkMediaTypeIsImage() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "apod?api_key=yQrch9tw87aBWodZjssBnIPE4YeNDaiEvettdG9O&date=2023-07-03")
                .then().log().all()
                .assertThat().statusCode(200)
                .and().body("media_type", Matchers.is("image"));
    }

    @Test
    public void checkInvalidDataMessage() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "apod?api_key=yQrch9tw87aBWodZjssBnIPE4YeNDaiEvettdG9O&date=1900-07-03")
                .then().log().all()
                .assertThat().statusCode(400)
                .and().body("msg", Matchers.is("Date must be between Jun 16, 1995 and Jul 03, 2023."));
    }
}
