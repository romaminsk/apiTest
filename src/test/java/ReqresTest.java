import org.example.models.RegisterUserData;
import org.example.models.Specifications;
import org.example.models.SuccessUserRegistrationData;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    private String URL = "https://reqres.in/";

    @Test
    public void isUserRegistered() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        RegisterUserData userData = new RegisterUserData("eve.holt@reqres.in", "pistol");
        SuccessUserRegistrationData successUser = given()
                .body(userData)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(SuccessUserRegistrationData.class);
        Assert.assertEquals(id, successUser.getId());
        Assert.assertEquals(token, successUser.getToken());
    }
}
