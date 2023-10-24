package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashwiseAuthorizationToken;
import utilities.Config;

public class CashwiseReportChartTest {

    @Test
    public void createIncomeCategory() throws JsonProcessingException {
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/categories";

        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("made in java");
        requestBody.setCategory_description("java yava");
        requestBody.setFlag(true);

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();

        String jsonResponse = response.asString();

        //storing our response as a string, then using mapper from Jackson library
        //and mapping the response that was stored as string to our POJO customResponse Class.
        //Now we can get any variable in customREsponse class using getters and setters.
        //getters and setters are generated with the help of lombok plugin and dependency.
        ObjectMapper mapper = new ObjectMapper();
        CustomResponses customResponse = mapper.readValue(jsonResponse, CustomResponses.class);

        System.out.println("category id : " + customResponse.getCategory_id());
        System.out.println("created: " + customResponse.getCreated());


    }
}
