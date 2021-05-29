package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BrowserAction;
import utility.HelperClass;

public class TestBase {
    //******************************************** variable ********************************************
    public static WebDriver driver;
    private BrowserAction browserAction;
    //******************************************** before class ********************************************
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(@Optional("chrome") String browser) {
        browserAction = new BrowserAction (driver);
        driver = browserAction.driverSetup(browser);
        browserAction.maxBrowser();
    }
    //******************************************** afterClass ********************************************
    @AfterTest
    public void afterClass() {
        //  browserAction.closeBrowser();
        // driver.quit();
    }
    //********************************************  screenshotOnFail ********************************************
    @AfterMethod
    public  void screenshotOnFail(ITestResult result){
        if (result.getStatus()==ITestResult.FAILURE){
            System.out.println("Fail ...taking screenshot");
            HelperClass.screenshotMethod(driver, result.getTestName());
        }

}
}
