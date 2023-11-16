package steps;

import entities.CustomResponses;
import entities.RequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.APIRunner;

import java.util.HashMap;
import java.util.Map;

public class SellersAPISteps {
    RequestBody requestBody = new RequestBody();
    int id;
    @Given("user hits get all sellers api with {string} and parameters: isArchived false, page {int}, size {int}")
    public void user_hits_get_all_sellers_api_with_and_parameters_is_archived_false_page_size(String isArchived, Integer page, Integer size) {
        String path = "/api/myaccount/sellers";
        Map<String,Object> params = new HashMap<>();
        params.put("isArchived",false);
        params.put("page",page);
        params.put("size",size);
        APIRunner.runGET(path,params);
    }
    @Then("user gets all sellers and their phone number")
    public void user_gets_all_sellers_and_their_phone_number() {
        int counter = 0;
        for (CustomResponses cr: APIRunner.getCustomResponses().getResponses()){
            System.out.println("seller's phone number: " + cr.getPhone_number());
            counter++;
        }
        System.out.println("total: " + counter);
    }



    @Given("data {string}, {string}, {string}, {string} and {string} to build request body")
    public void data_and_to_build_request_body(String companyName, String sellerName, String email, String phoneNumber, String address) {
        requestBody.setCompany_name(companyName);
        requestBody.setSeller_name(sellerName);
        requestBody.setEmail(email);
        requestBody.setPhone_number(phoneNumber);
        requestBody.setAddress(address);
    }
    @Then("hit seller API endpoint {string} and seller's id")
    public void hit_seller_api_endpoint_and_seller_s_id(String path) {
        APIRunner.runPOST(path,requestBody);
        id = APIRunner.getCustomResponses().getSeller_id();
        System.out.println("id from create seller API: " + id);
    }
    @Then("update email {string} and use the same rest of data {string}, {string}, {string} and {string}")
    public void update_email_and_use_the_same_rest_of_data_and(String companyName, String sellerName, String newEmail, String phoneNumber, String address) {
        requestBody.setCompany_name(companyName);
        requestBody.setSeller_name(sellerName);
        requestBody.setEmail(newEmail);
        requestBody.setPhone_number(phoneNumber);
        requestBody.setAddress(address);

    }
    @Then("hit update seller API endpoint {string} concatenated by above seller_id")
    public void hit_update_seller_api_endpoint_concatenated_by_above_seller_id(String path) {
        APIRunner.runPUT(path + id,requestBody);
        id = APIRunner.getCustomResponses().getSeller_id();
        System.out.println("id from update seller is: " + id);
    }
    @Then("verify that seller's email got updates succesfully to {string}")
    public void verify_that_seller_s_email_got_updates_succesfully_to(String email) {
        Assert.assertEquals("email is not updated ", email, APIRunner.getCustomResponses().getEmail());
        System.out.println("Seller's email got updated successfully");
    }
    @Then("hit get seller API endpoint {string} concatenated by above seller_id")
    public void hit_get_seller_api_endpoint_concatenated_by_above_seller_id(String path) {
        APIRunner.runGET(path + id);
        System.out.println("id from get seller API: " + APIRunner.getCustomResponses().getSeller_id());
    }
    @Then("verify that id in updated seller API and id in get seller API is the same")
    public void verify_that_id_in_updated_seller_api_and_id_in_get_seller_api_is_the_same() {
        Assert.assertEquals("id is not as expected ", id, APIRunner.getCustomResponses().getSeller_id());
    }



}
