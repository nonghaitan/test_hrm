package steps;

import commons.BaseTest;
import commons.Context;
import commons.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.*;

public class CreateUserSteps extends BaseTest {
    private WebDriver driver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    AdminPageObject adminPage;

//    @Parameters("browser")
//    @BeforeClass()
//    public void testSetUp(String browserName) {
//        log.info("Pre_Condition: Init browser");
//        driver = getBrowserDriver(browserName);
//    }

    @Given("I open OrangeHRM login page")
    public void openLogin() {
        loginPage = PageGeneratorManager.getLoginPage(driver).openLoginPage();
    }


    @When("I login as admin user")
    public void login() {
        dashboardPage = loginPage.loginAsAdminUser();
        verifyTrue(dashboardPage.isDashboardHeaderDisplayed());
    }

    @When("I navigate to User Management page")
    public void goAdmin() {
        adminPage = dashboardPage.openAdminPage();
    }

    @When("I create a new valid user")
    public void createUser() {
        adminPage.clickAddUser();
        adminPage.createUser();
    }

    @Then("The user should be created successfully")
    public void verifyCreate() {
        verifyTrue(adminPage.verifySuccess());
    }

    @When("I search for the created user")
    public void searchUser() {
        adminPage.searchUser((String) Hooks.context.get(String.valueOf(Context.USER_NAME)));
    }

    @Then("The user should appear in result table")
    public void verifyUser() {
        String userName = (String) Hooks.context.get(String.valueOf(Context.USER_NAME));
        String role = (String) Hooks.context.get(String.valueOf(Context.ROLE));
        String employeeName = (String) Hooks.context.get(String.valueOf(Context.EMPLOYEE_NAME));
        String status = (String) Hooks.context.get(String.valueOf(Context.STATUS));
        verifyTrue(adminPage.verifyUserInTable(userName));
        verifyTrue(adminPage.verifyUserInTable(role));
        verifyTrue(adminPage.verifyUserInTable(employeeName));
        verifyTrue(adminPage.verifyUserInTable(status));
    }

    @Then("The user record found is displayed successfully")
    public void theUserRecordFoundIsDisplayedSuccessfully() {
        adminPage.isRecordDisplaySuccessfully();
    }

    @AfterClass(alwaysRun = true)
    public void closeApplication() {
        log.info("Post_Condition: Close browser and driver");
        closeBrowserAndDriver(driver);
    }


}
