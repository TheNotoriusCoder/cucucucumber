package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashwiseAuthorizationToken;

import java.util.List;

public class CashwiseBankAccountTest {

    @Test
    public void getAllBankAccount(){
        String token = CashwiseAuthorizationToken.getToken();
        Response response = RestAssured.given().auth().oauth2(token).get("https://backend.cashwise.us/api/myaccount/bankaccount");
        System.out.println("status code: " + response.statusCode());
        response.prettyPrint();

//        String bankAccountItself = response.jsonPath().getString("[0].bank_account_name");
//        System.out.println(bankAccountItself);
//        int size = response.jsonPath().getInt("$.size()");

        List<Integer> id =  response.jsonPath().getList("id");
        List<Integer> fake_id =  response.jsonPath().getList("fake_id");
        List<String> accountName =  response.jsonPath().getList("bank_account_name");
        List<String> description =  response.jsonPath().getList("description");
        List<Integer> type_of_pay =  response.jsonPath().getList("type_of_pay");
        List<Float> balance =  response.jsonPath().getList("balance");
        List<Integer> history_balance_responses =  response.jsonPath().getList("history_balance_responses");


        for (int i = 0; i < accountName.size(); i++) {
            System.out.println(id.get(i));
            System.out.println(fake_id.get(i));
            System.out.println(accountName.get(i));
            System.out.println(description.get(i));
            System.out.println(type_of_pay.get(i));
            System.out.println(balance.get(i));
            System.out.println(history_balance_responses.get(i));
            System.out.println("------------------------");
        }
    }
}
