package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserAction {
    //******************************************** variable ********************************************
    private WebDriver driver;

    //******************************************** constrain *******************************************
    public BrowserAction (WebDriver driver){

        this.driver= driver;
    }

    //******************************************** action ********************************************
    public WebDriver driverSetup( String browser) {
        try
        {
            if (browser.equals("chrome")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                return driver;
            }
            else if (browser.equals("fireFox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver() ;
                return driver;
            }
            else if (browser.equalsIgnoreCase("headless")) {
                DesiredCapabilities cap=new DesiredCapabilities();
                cap.setJavascriptEnabled(true);
              //  cap.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"E:\\project\\automation\\src\\main\\resources\\phantomjs.exe");
                String[] phantomJsArg={"--web-security=no","--ignore-ssl-errors=yes"};
                cap.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,phantomJsArg);
               WebDriverManager.phantomjs().setup();
                driver = new PhantomJSDriver(cap) ;
                return driver;
            }
            else if (browser.equalsIgnoreCase("chrome_headless")) {
                // System.setProperties("webdriver.chrome.driver","E:\\iti\\project\\automation\\src\\main\\resources\\chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", "E:\\project\\automation\\chromedriver.exe");
                ChromeOptions opt=new ChromeOptions();
                opt.addArguments("--headless");
                opt.addArguments("--window_size=1920,1080");
                driver = new ChromeDriver(opt);
                return driver;
            }
            else{
                System.out.println("browser are not correct");
                return null;
            }

        }
        catch (Exception e )
        {
            System.out.println("cant open browser");
            return null;
        }

    }
    public void maxBrowser(){
        driver.manage().window().maximize();
    }
    public void closeBrowser(){
        driver.quit();

    }
}
