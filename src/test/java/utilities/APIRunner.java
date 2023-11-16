package utilities;

import entities.CustomResponses;
import entities.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Map;


public class APIRunner {

    @Getter
    private static CustomResponses customResponses;



    public static void runGET(String path){
        //url domain and no parameters
        //parameter and no parameters we will use overloading
        //token
        String url = Config.getProperty("cashwiseURI") + path;
        String token = CashwiseAuthorizationToken.getToken();
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println();
        System.out.println("response code: " + response.statusCode());
        System.out.println("-------------------");
        if(response.statusCode() < 200 || response.statusCode() > 201){
            System.out.println(response.asPrettyString());
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is most likely list of response");
        }
    }


    public static void runGET(String path, Map<String , Object> params){
        String url = Config.getProperty("cashwiseURI") + path;
        String token = CashwiseAuthorizationToken.getToken();
        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println();
        System.out.println("response code: " + response.statusCode());
        System.out.println("-------------------");
        System.out.println(response.prettyPrint());
        if(response.statusCode() < 200 || response.statusCode() > 201){
            System.out.println(response.prettyPrint());
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is most likely list of response");
        }
    }

    public static void runPOST(String path, RequestBody requestBody){
        String url = Config.getProperty("cashwiseURI") + path;
        String token = CashwiseAuthorizationToken.getToken();
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        System.out.println();
        System.out.println("response code: " + response.statusCode());
        System.out.println("-------------------");
        System.out.println(response.prettyPrint());
        if(response.statusCode() < 200 || response.statusCode() > 201){
            System.out.println(response.asPrettyString());
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is most likely list of response");
        }

    }
    public static void runPUT(String path, RequestBody requestBody){
        String url = Config.getProperty("cashwiseURI") + path;
        String token = CashwiseAuthorizationToken.getToken();
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).put(url);
        System.out.println();
        System.out.println("response code: " + response.statusCode());
        System.out.println("-------------------");
        if(response.statusCode() < 200 || response.statusCode() > 201){
            System.out.println(response.asPrettyString());
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());
        } catch (JsonProcessingException e) {
            System.out.println("This is most likely list of response");
        }

    }
}
