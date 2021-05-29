package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoadingExcel;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.RegisterPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class registrationTestGrid extends GridTestBase {
    //variables
    RegisterPage registerPage;
    MyAccountPage myAccountPage;
    LoginPage loginPage;

    //exel reader
    @DataProvider(name="Excel data")
    public static Object[][] userData() throws IOException {
        // read from loadExcl
        LoadingExcel exelReader=new LoadingExcel();
        return exelReader.getExcelData();
    }
    //register
    @Test(dataProvider="Excel data",priority = 1)
    public  void userCanRegister(String firstname , String lastname ,String phone, String email , String password,String  confirmedPassword) {

        registerPage = new RegisterPage(getDriver());
        registerPage.navigateToRegisterPage();
        registerPage.UserCanRegister(firstname, lastname, phone, email, password, confirmedPassword);
        myAccountPage = new MyAccountPage(getDriver());
        Assert.assertEquals(myAccountPage.getNameOfAccount(), "Merhaba " + firstname + " " + lastname);
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        myAccountPage.userCanLogOut();
        loginPage=new LoginPage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        loginPage.userCanSignUp();


    }
    @Test
    public  void ONEuserCanLogout(){
        registerPage = new RegisterPage(getDriver());
        registerPage.navigateToRegisterPage();
        registerPage.UserCanRegister("firstname", "lastname", "phone", "3881500@ni.nm", "password", "password");
        Assert.assertFalse(registerPage.assertOnMassage());
        myAccountPage = new MyAccountPage(getDriver());
        Assert.assertEquals(myAccountPage.getNameOfAccount(), "Merhaba firstname lastname");
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        myAccountPage.userCanLogOut();
        loginPage=new LoginPage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        loginPage.userCanSignUp();


    }
    @Test
    public  void  Verifyuserlogincorrectly(){
        registerPage = new RegisterPage(getDriver());
        registerPage.navigateToRegisterPage();
        registerPage.UserCanRegister("firstname", "lastname", "phone", "4ni.nm", "password", "password");
        Assert.assertFalse(registerPage.assertOnMassage());
        myAccountPage = new MyAccountPage(getDriver());
        Assert.assertEquals(myAccountPage.getNameOfAccount(), "Merhaba firstname lastname");
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        myAccountPage.userCanLogOut();
        loginPage=new LoginPage(getDriver());
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        loginPage.userCanLogin("4@ni.nm","password");
        Assert.assertEquals(myAccountPage.getNameOfAccount(), "Merhaba firstname lastname");
    }
}
