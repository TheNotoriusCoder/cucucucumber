package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public  void beforeScenario(){
        System.out.println("Before");
        System.out.println();
    }
    @After
    public void afterScenario(){
        System.out.println("After");
        System.out.println();
    }

}
