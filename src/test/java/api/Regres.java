package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class Regres {


    public static void main(String[] args) {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();
        String email = response.jsonPath().get("data.email").toString();
        String firstName = response.jsonPath().get("data.first_name").toString();
        String lastName = response.jsonPath().get("data.last_name").toString();
        String avatar = response.jsonPath().get("data.avatar").toString();
        System.out.println(email);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(avatar);
        Assert.assertFalse("email is empty", email.isEmpty());
        Assert.assertFalse("fisrt name is empty", firstName.isEmpty());
        Assert.assertFalse("last name is empty", lastName.isEmpty());
        Assert.assertFalse("avatar is empty", avatar.isEmpty());
        Assert.assertTrue("Avatar is not .jpg or .png",avatar.endsWith(".jpg") || avatar.endsWith(".png") );
    }
}
