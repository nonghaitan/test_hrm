package steps;

import commons.Hooks;
import commons.ExtentTestManager;
import data.TestUserData;
import data.UserDataFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.*;

public class CreateUserSteps {
    private WebDriver driver = Hooks.getDriver();
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private AdminPageObject adminPage;
    private TestUserData user;
    private String loggedInUserDisplayName;

    @Given("I open OrangeHRM login page")
    public void openLogin() {
        ExtentTestManager.getTest().info("Open OrangeHRM login page");
        loginPage = PageGeneratorManager.getLoginPage(driver).openLoginPage();
    }

    @When("I login as admin user")
    public void login() {
        ExtentTestManager.getTest().info("Login as admin user");
        dashboardPage = loginPage.loginAsAdminUser();
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed(), "Dashboard header missing");
        loggedInUserDisplayName = dashboardPage.getLoggedInUserName();
    }

    @When("I navigate to User Management page")
    public void goAdmin() {
        ExtentTestManager.getTest().info("Navigate to User Management page");
        adminPage = dashboardPage.openAdminPage();
    }

    @When("I create a new valid user")
    public void createUser() {
        ExtentTestManager.getTest().info("Create a new valid user");
        user = UserDataFactory.createAdminUser();
        user.setDisplayName(loggedInUserDisplayName);
        adminPage.clickAddUser();
        adminPage.createUser(user);
    }

    @Then("The user should be created successfully")
    public void verifyCreate() {
        ExtentTestManager.getTest().info("Verify user created successfully");
        Assert.assertTrue(adminPage.verifySuccess(), "User creation failed");
    }

    @When("I search for the created user")
    public void searchUser() {
        ExtentTestManager.getTest().info("Search for created user");
        adminPage.searchUser(user.getUsername());
    }

    @Then("The user should appear in result table")
    public void verifyUser() {
        ExtentTestManager.getTest().info("Verify user appears in result table");
        Assert.assertTrue(adminPage.verifyUserInTable(user.getUsername()), "Username not found");
        Assert.assertTrue(adminPage.verifyUserInTable(user.getRole()), "Role not found");
        Assert.assertTrue(adminPage.verifyUserInTable(user.getDisplayName()), "Employee name not found");
        Assert.assertTrue(adminPage.verifyUserInTable(user.getStatus()), "Status not found");
    }

    @Then("The user record found is displayed successfully")
    public void theUserRecordFoundIsDisplayedSuccessfully() {
        ExtentTestManager.getTest().info("Verify user record displayed successfully");
        Assert.assertTrue(adminPage.isRecordDisplaySuccessfully(), "Record not displayed");
    }

    @Then("Admin header is displayed successfully")
    public void adminHeaderIsDisplayedSuccessfully() {
        ExtentTestManager.getTest().info("Verify Admin header displayed");
        Assert.assertTrue(adminPage.isHeaderDisplayed(), "Admin header not displayed");
    }
}
