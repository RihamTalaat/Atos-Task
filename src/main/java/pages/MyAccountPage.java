package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class MyAccountPage {
    //variables
    WebDriver driver;
    public String homeUrl = "https://www.phptravels.net/account/";
    Util utilPage;

    //constrain
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        utilPage = new Util(this.driver);
    }

    //element
    By myAccountNameLocator = By.cssSelector("h3.text-align-left");
    By dropdownLocator = By.cssSelector("div.dropdown.dropdown-login.dropdown-tab");
    By logOutLinkLocator = By.xpath("//a[@class=\"dropdown-item tr\"]");

    //action
    public void navigateToRegisterPage() {
        utilPage.navigateToPage(homeUrl,myAccountNameLocator);
    }
    public String getNameOfAccount(){
       return utilPage.getText(myAccountNameLocator);
    }
    public void userCanLogOut(){

        driver.findElement(dropdownLocator).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(logOutLinkLocator).click();

    }

}
