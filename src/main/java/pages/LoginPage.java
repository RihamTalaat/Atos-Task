package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    WebDriver driver;
    public String homeUrl = "https://www.phptravels.net/login";
    Util utilPage;

    //constrain
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        utilPage = new Util(this.driver);
    }

    //element
   protected By loginTextLocator = By.xpath("h3[text()=\"Login\"]");
    By dropDownLocator = By.cssSelector("div.dropdown.dropdown-login.dropdown-tab");
    By signupLinkLocator = By.xpath("//a[@class=\"dropdown-item tr\"]");
    By emailLocator = By.xpath(" //input[@type=\"email\"]");
    By PasswordLocator = By.xpath(" //input[@type=\"password\"]");
    By loginButtonLocator = By.xpath("// button[@class=\"btn btn-primary btn-lg btn-block loginbtn\"]");


    //action
    public String LoginPageAssert(){
        return utilPage.getText(loginTextLocator);
    }
    public void userCanSignUp(){
        driver.findElement(dropDownLocator).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(signupLinkLocator).click();

    }
    public void userCanLogin( String email , String password){
        utilPage.setText(emailLocator,email,true);
        utilPage.setText(PasswordLocator,password,true);
        driver.findElement(loginButtonLocator).click();

    }



}
