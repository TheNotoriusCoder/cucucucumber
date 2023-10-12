package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.Driver;

public class Hooks {

    @Before
    public  void beforeScenario(){
        System.out.println("Before");
        System.out.println();
    }
    @After
    public void afterScenario(){
        Driver.closeDriver();
        System.out.println("After");
        System.out.println();
    }

}
