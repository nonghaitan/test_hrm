package ui.steps;

import ui.hooks.UIHooks;
import commons.report.UIExtentTestManager;
import data.TestUserData;
import data.UserDataFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.pageObjects.AdminPageObject;
import ui.pageObjects.DashboardPageObject;
import ui.pageObjects.LoginPageObject;
import ui.pageObjects.PageGeneratorManager;

public class CreateUserSteps {

    private WebDriver driver = UIHooks.getDriver();
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private AdminPageObject adminPage;
    private TestUserData user;
    private String loggedInUserDisplayName;

    @Given("I open OrangeHRM login page")
    public void openLogin() {
        UIExtentTestManager.getTest().info("Open OrangeHRM login page");
        loginPage = PageGeneratorManager.getLoginPage(driver).openLoginPage();
    }

    @When("I login as admin user")
    public void login() {
        UIExtentTestManager.getTest().info("Login as admin user");
        dashboardPage = loginPage.loginAsAdminUser();
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed(), "Dashboard header missing");
        loggedInUserDisplayName = dashboardPage.getLoggedInUserName();
    }

    @When("I navigate to User Management page")
    public void goAdmin() {
        UIExtentTestManager.getTest().info("Navigate to User Management page");
        adminPage = dashboardPage.openAdminPage();
    }

    @When("I create a new valid user")
    public void createUser() {
        UIExtentTestManager.getTest().info("Create a new valid user");
        user = UserDataFactory.createAdminUser();
        user.setDisplayName(loggedInUserDisplayName);
        adminPage.clickAddUser();
        adminPage.createUser(user);
    }

    @Then("The user should be created successfully")
    public void verifyCreate() {
        UIExtentTestManager.getTest().info("Verify user created successfully");
        Assert.assertTrue(adminPage.verifySuccess(), "User creation failed");
    }

    @When("I search for the created user")
    public void searchUser() {
        UIExtentTestManager.getTest().info("Search for created user");
        adminPage.searchUser(user.getUsername());
    }

    @Then("The user should appear in result table")
    public void verifyUser() {
        UIExtentTestManager.getTest().info("Verify user appears in result table");
        Assert.assertTrue(
                adminPage.verifyUserInTableFull(
                        user.getUsername(),
                        user.getRole(),
                        user.getDisplayName(),
                        user.getStatus()
                ),
                "User row not found with all expected values"
        );
    }

    @Then("The user record found is displayed successfully")
    public void theUserRecordFoundIsDisplayedSuccessfully() {
        UIExtentTestManager.getTest().info("Verify user record displayed successfully");
        Assert.assertTrue(adminPage.isRecordDisplaySuccessfully(), "Record not displayed");
    }

    @Then("Admin header is displayed successfully")
    public void adminHeaderIsDisplayedSuccessfully() {
        UIExtentTestManager.getTest().info("Verify Admin header displayed");
        Assert.assertTrue(adminPage.isHeaderDisplayed(), "Admin header not displayed");
    }
}
