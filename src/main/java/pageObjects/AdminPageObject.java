package pageObjects;

import commons.Context;
import commons.DriverManager;
import commons.Hooks;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminPageUI;

public class AdminPageObject extends SidePanelPageObject {
    private WebDriver driver;

    public AdminPageObject(WebDriver driver) {
        super(driver);
//        this.driver = driver;

        this.driver = DriverManager.getDriver();
    }

    public void clickAddUser() {
        clickToElement(driver, AdminPageUI.ADD_BUTTON);
    }

    public void createUser() {
        String username = "auto" + System.currentTimeMillis();
        String role = "Admin";
        String employeeName = "manda akhil user";
        String status = "Enabled";
        String password = "Admin@123";

        selectUserRole(role);
        inputEmployeeName(employeeName);
        selectStatus(status);
        inputUsername(username);
        inputPassword(password);
        inputConfirmPassword(password);
        clickSave();

        Hooks.context.set(String.valueOf(Context.USER_NAME), username);
        Hooks.context.set(String.valueOf(Context.ROLE), role);
        Hooks.context.set(String.valueOf(Context.EMPLOYEE_NAME), employeeName);
        Hooks.context.set(String.valueOf(Context.STATUS), status);
        Hooks.context.set(String.valueOf(Context.PASSWORD), password);
    }


    private void selectUserRole(String userRole) {
        selectItemInCustomDropdown(driver, AdminPageUI.ADD_USER_ROLE_PARENT_DROPDOWN, AdminPageUI.ADD_USER_ROLE_CHILDREN_ITEMS, userRole);
    }

    private void inputEmployeeName(String employeeName) {
        sendKeyToElement(driver, AdminPageUI.EMPLOYEE_NAME, employeeName);
    }

    private void selectStatus(String status) {
        selectItemInCustomDropdown(driver, AdminPageUI.STATUS_PARENT_DROPDOWN, AdminPageUI.STATUS_CHILDREN_ITEMS, status);
    }

    private void inputUsername(String username) {
        sendKeyToElement(driver, AdminPageUI.USERNAME_TEXTBOX, username);
    }


    private void inputPassword(String password) {
        sendKeyToElement(driver, AdminPageUI.PASSWORD_TEXTBOX, password);
    }

    private void inputConfirmPassword(String password) {
        sendKeyToElement(driver, AdminPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    private void clickSave() {
        clickToElement(driver, AdminPageUI.SAVE_BUTTON);
    }

    public boolean verifySuccess() {
        return isElementDisplayed(driver, AdminPageUI.SUCCESS_TOAST);
    }

    public void searchUser(String userName) {
        sendKeyToElement(driver, AdminPageUI.SEARCH_USERNAME, userName);
        clickToElement(driver, AdminPageUI.SEARCH_BUTTON);
    }

    public boolean verifyUserInTable(String value) {
        return isElementDisplayed(driver, AdminPageUI.DYNAMIC_RESULT_ROW, value);
    }

    public boolean isRecordDisplaySuccessfully() {
        return isElementDisplayed(driver, AdminPageUI.RESULT_1_RECORD_FOUND);
    }
}
