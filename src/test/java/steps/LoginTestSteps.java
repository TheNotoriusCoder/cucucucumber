package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.StudyMateLoginPage;
import utilities.Config;
import utilities.Driver;

public class LoginTestSteps {
    WebDriver driver = Driver.getDriver();
    StudyMateLoginPage loginPage = new StudyMateLoginPage();


    @Given("user navigate to login page")
    public void user_navigate_to_login_page() {
        driver.get(Config.getProperty("studyMateUrl"));
        Assert.assertEquals("","StudyMate",driver.getTitle());
        System.out.println("user is on login page");
    }
    @When("user enters email {string} to the email field")
    public void user_enters_email_to_the_email_field(String email) {
        loginPage.email.sendKeys(email);
        System.out.println("user enters email: " + email);
    }
    @When("user enters password {string} to the password field")
    public void user_enters_password_to_the_password_field(String password){
        loginPage.password.sendKeys(password);
        System.out.println("user enters password: " + Config.getProperty("password"));
    }
    @Then("user performs click action on the login button")
    public void user_performs_click_action_on_the_login_button() {
        loginPage.loginBtn.click();
        System.out.println("user clicked login button");
    }
    @Then("user should be logged in to the application")
    public void user_should_be_logged_in_to_the_application() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        String expectedUrl = "https://codewiser.studymate.us/admin/analytics";
        Thread.sleep(2000);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("successful login");

    }
}
