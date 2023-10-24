package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashwiseAuthorizationToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CashwiseSellersTest {

    @Test
    public void verifySellersList(){
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/sellers";
        Map<String, Object>params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 4);

        Response respone = RestAssured.given().auth().oauth2(token).params(params).get(url);
        respone.prettyPrint();

    }

    @Test
    public void getSingleSeller(){
        int id = 1769;
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/sellers/" + id;

        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();
    }
    @Test
    public void createSeller() throws JsonProcessingException {
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("my ego");
        requestBody.setSeller_name("my Pojo friend");
        requestBody.setEmail("jureanic@gmail.com");
        requestBody.setPhone_number("22106124433");
        requestBody.setAddress("puskina 22");

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponse = mapper.readValue(response.asString(), CustomResponses.class);

        System.out.println("seller id: " + customResponse.getSeller_id());

    }
}

