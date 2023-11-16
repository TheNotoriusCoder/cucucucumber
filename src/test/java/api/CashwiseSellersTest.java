package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponses;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;
import utilities.CashwiseAuthorizationToken;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class CashwiseSellersTest {

    @Test
    public void verifySellersList() {
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 4);

        Response respone = RestAssured.given().auth().oauth2(token).params(params).get(url);
        respone.prettyPrint();

    }

    @Test
    public void getSingleSeller() {
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

        Assert.assertEquals(200, response.statusCode());

    }

    @Test
    public void createManySeller() {
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/sellers";
        Faker faker = new Faker();

        RequestBody requestBody = new RequestBody();
        for (int i = 0; i < 10; i++) {
            requestBody.setCompany_name(faker.company().name());
            requestBody.setSeller_name(faker.name().fullName());
            requestBody.setEmail(faker.internet().emailAddress());
            requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
            requestBody.setAddress(faker.address().fullAddress());
            Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
            System.out.println("status code: " + response.statusCode());
            response.prettyPrint();

        }
    }

    @Test
    public void getEmailOfSellers() throws JsonProcessingException {
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/sellers";

        Map<String, Object> request = new HashMap<>();
        request.put("isArchived", false);
        request.put("page", 1);
        request.put("size", 10);

        Response response = RestAssured.given().auth().oauth2(token).params(request).get(url);
        System.out.println("status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponses = mapper.readValue(response.asString(), CustomResponses.class);

//        int size = customResponses.getResponses().size();

//        for (int i = 0; i < size; i++) {
//            System.out.println("user's email: " + customResponses.getResponses().get(i).getEmail());
//        }
    }

    @Test
    public void getSeller() {
        String path = "/api/myaccount/sellers/1937";
        APIRunner.runGET(path);
        System.out.println("seller's name " + APIRunner.getCustomResponses().getSeller_name());
        System.out.println("seller's email " + APIRunner.getCustomResponses().getEmail());
    }

    @Test
    public void getSellersList() {
        String path = "/api/myaccount/sellers";
        String url = Config.getProperty("cashwiseURI") + path;

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        Response response = RestAssured.given().auth().oauth2(CashwiseAuthorizationToken.getToken()).params(params).get(url);

        int pageSize = Integer.parseInt(response.jsonPath().getString("total_page"));

        int sellersCounter = 0;
        for (int i = 1; i <= pageSize; i++) {
            params.put("isArchived", false);
            params.put("page", i);
            params.put("size", 10);

            APIRunner.runGET(path, params);
            System.out.println("Page " + i);
            System.out.println("-------------------");

            int responsesOnOnePage = APIRunner.getCustomResponses().getResponses().size();
            for (int j = 0; j < responsesOnOnePage; j++) {
                System.out.println(j + 1 + ". " + APIRunner.getCustomResponses().getResponses().get(j).getCompanyName());
                sellersCounter++;
            }
        }
        System.out.println("=======================");
        System.out.println("Found: " + sellersCounter + " sellers.");
        System.out.println("=======================");
    }

    @Test
    public void createNewSeller(){
        Faker faker = new Faker();
        String path = "/api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();

        String company = faker.company().name();

        requestBody.setCompany_name(company);
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
        requestBody.setAddress(faker.address().fullAddress());

        APIRunner.runPOST(path,requestBody);
        System.out.println(APIRunner.getCustomResponses().getResponseBody());

        String path2 = "/api/myaccount/sellers/" + APIRunner.getCustomResponses().getSeller_id();
        APIRunner.runGET(path2);
        String expectedName = APIRunner.getCustomResponses().getCompanyName();
        Assert.assertEquals(company, expectedName);
        //should do assertion for all fields






    }
}

