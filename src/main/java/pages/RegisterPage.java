package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class RegisterPage {
    //variable
    WebDriver driver;
    String homeUrl = "https://www.phptravels.net/register";
    Util utilPage;

    //constrain
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        utilPage = new Util(this.driver);
    }

    //element
    private By elementToValidate = By.xpath("//h3[text()=\"Sign Up\"]");
    private By firstnameLocator = By.name("firstname");
    private By lastnameLocator = By.name("lastname");
    private By phoneLocator = By.name("phone");
    private By emailLocator = By.name("email");
    private By passwordLocator = By.name("password");
    private By confirmPasswordLocator = By.name("confirmpassword");
    private By signUpButtonLocator = By.cssSelector("button.signupbtn.btn_full.btn.btn-success.btn-block.btn-lg");
    private By alertMassageLocator = By.xpath("//div[@class=\"alert alert-danger\"]");

    //action

    public void navigateToRegisterPage() {
        utilPage.navigateToPage(homeUrl, elementToValidate);
    }

    public void UserCanRegister(String firstName , String lastName ,String phone, String email , String password,String confirmPassword) {
        utilPage.setText(firstnameLocator,firstName,false);
        utilPage.setText(lastnameLocator,lastName,false);
        utilPage.setText(phoneLocator,phone,false);
        utilPage.setText(emailLocator,email,false);
        utilPage.setText(passwordLocator,password,false);
        utilPage.setText(confirmPasswordLocator,confirmPassword,false);
        driver.findElement(signUpButtonLocator).submit();

    }
    public boolean assertOnMassage(){
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       String realMassage= driver.findElement(alertMassageLocator).getText();
        System.out.println(realMassage);
        if (realMassage==" Email Already Exists. "
                ||realMassage=="The Email field must contain a valid email address."
                ||realMassage=="Password not matching with confirm password."
        ||realMassage=="The Email field must contain a valid email address.\n" +
                "\n" +
                "The Password field must be at least 6 characters in length."){
            return false;
        }
        else
            return true;
    }
}

