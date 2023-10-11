package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FirstCucumberStepsTest {




    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("correct {string} username")
    public void correct_username(String username) {
        // Write code here that turns the phrase above into concrete actions

        System.out.println("username- " + username);
    }
    @When("correct {string} password")
    public void correct_password(String password) {

        System.out.println("password- " + password);
        System.out.println();
    }
    @When("user is clicking login button")
    public void user_is_clicking_login_button() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("verify user logs in")
    public void verify_user_logs_in() {
        // Write code here that turns the phrase above into concrete actions

    }


}

