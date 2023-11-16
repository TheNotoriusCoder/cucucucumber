package api;

import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;

public class CashwiseProfileTest {


    @Test
    public void getProfile(){
        String path = "/api/myaccount/editors/get/profile";
        APIRunner.runGET(path);

        Assert.assertNotNull(APIRunner.getCustomResponses().getBusiness_area().getEnTitle().isEmpty());

    }
}
