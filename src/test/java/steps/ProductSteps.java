package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.APIRunner;

import java.util.HashMap;

public class ProductSteps {

    @Given("API path {string} and parameters page {int} size {int}")
    public void api_path_and_parameters_page_size(String path, Integer page, Integer size) {
        HashMap params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        APIRunner.runGET(path, params);

    }

    @Then("verify there is a service type and category for each product")
    public void verify_there_is_a_service_type_and_category_for_each_product() {
        System.out.println("response: " + APIRunner.getCustomResponses().getResponseBody());
        int size = APIRunner.getCustomResponses().getResponses().size();

        for (int i = 0; i < size; i++) {
            Assert.assertNotNull(APIRunner.getCustomResponses().getResponses().get(i).getCategory());
            Assert.assertNotNull(APIRunner.getCustomResponses().getResponses().get(i).getService_type());
        }
    }


    @Then("verify service type to be {string} or {string}")
    public void verify_service_type_to_be_or(String service, String product) {
        int size = APIRunner.getCustomResponses().getResponses().size();
        for (int i = 0; i < size; i++) {
            int serviceId = APIRunner.getCustomResponses().getResponses().get(i).getService_type().getService_type_id();
            String serviceType = APIRunner.getCustomResponses().getResponses().get(i).getService_type().getService_type();
            if (serviceId == 1) {
                Assert.assertEquals(service, serviceType.trim());
            }
            if (serviceId == 2) {

                Assert.assertEquals(product, serviceType.trim());
            }
        }
    }

}
