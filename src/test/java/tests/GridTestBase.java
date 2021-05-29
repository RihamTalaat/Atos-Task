package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BrowserAction;
import utility.HelperClass;

import java.net.MalformedURLException;
import java.net.URL;

public class GridTestBase {
    //to run run from .xml
    //******************************************** variable ********************************************
   // public static String url = "https://www.phptravels.net/register";
    protected ThreadLocal<RemoteWebDriver> driver = null; //to open more than one thread and more than one browser
    //webDriver take place in memory decliration

    //******************************************** before class ********************************************
    @Parameters("browser")
    @BeforeClass
    public void setDriver(@Optional("firefox") String browser) throws MalformedURLException {
        driver = new ThreadLocal<>();// intiate driver with thread
        DesiredCapabilities cap = new DesiredCapabilities(); // used with remote driver
        cap.setBrowserName("firefox");
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap));
        //get method to set desirecapaplies and put port number
      //  getDriver().navigate().to(url);
    }

    //******************************************** getDriver ********************************************
    public WebDriver getDriver() {
        return driver.get();
    }  // method git driver using thread must change all driver to get driver method
    //******************************************** afterClass ********************************************
    @AfterClass
    public void closetDriver() {
        getDriver().quit();
        driver.remove();
    }
   //********************************************  screenshotOnFail ******************************************
    @AfterMethod
    public void screenshotOnFail(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Fail ...taking screenshot");
            HelperClass.screenshotMethod(getDriver(), result.getTestName());
        }
    }

}

