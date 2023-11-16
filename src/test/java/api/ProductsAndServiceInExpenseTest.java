package api;

import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashwiseAuthorizationToken;
import utilities.Config;

public class ProductsAndServiceInExpenseTest {

    @Test
    public void createProductOrService(){
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/expenses/";

        RequestBody requestBody = new RequestBody();

        requestBody.setProduct_title("made in java2");
        requestBody.setProduct_price(100);
        requestBody.setService_type_id(1);
        requestBody.setCategory_id(2);
        requestBody.setProduct_description("made in java");
        requestBody.setDate_of_Payment("2023-10-24");
        requestBody.setRemind_before_day(2);
        requestBody.setDo_remind_every_month("REPEAT_EVERY_MONTH");

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);

        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void getProductOrService(){
        int id = 261;
        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/products/" + id ;


        Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();
    }
    @Test
    public void updateProduct(){

        String token = CashwiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashwiseURI") + "/api/myaccount/expenses/261/-1";

        RequestBody requestBody = new RequestBody();
        requestBody.setProduct_title("updated in java3");
        requestBody.setProduct_price(69);
        requestBody.setService_type_id(1);
        requestBody.setCategory_id(1);
        requestBody.setProduct_description("description");
        requestBody.setDate_of_Payment("2023-10-29");
        requestBody.setRemind_before_day(1);
        requestBody.setDo_remind_every_month("REPEAT_EVERY_MONTH");



        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).put(url);
        System.out.println(response.statusCode());
        response.prettyPrint();
    }

//    @Test
//    public void deleteProduct(){
//        String token
//    }
}
