package pages;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StudyMateLoginPage {
    WebDriver driver;
    public StudyMateLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "email")
    public WebElement email;
    @FindBy(name = "password")
    public WebElement password;
    @FindBy(xpath = "//button[text()='Log in']")
    public WebElement loginBtn;








}
