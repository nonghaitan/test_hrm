package pageObjects;

import data.TestUserData;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminPageUI;

public class AdminPageObject extends SidePanelPageObject {
    private WebDriver driver;

    public AdminPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickAddUser() {
        clickToElement(driver, AdminPageUI.ADD_BUTTON);
    }

    public void createUser(TestUserData user) {
        selectUserRole(user.getRole());
        inputEmployeeName(user);
        selectStatus(user.getStatus());
        inputUsername(user.getUsername());
        inputPassword(user.getPassword());
        clickSave();
    }


    private void selectUserRole(String userRole) {
        selectItemInCustomDropdown(driver, AdminPageUI.ADD_USER_ROLE_PARENT_DROPDOWN, AdminPageUI.ADD_USER_ROLE_CHILDREN_ITEMS, userRole);
    }

    private void inputEmployeeName(TestUserData user) {
        sendKeyToElement(driver, AdminPageUI.EMPLOYEE_NAME, user.getDisplayName());
        waitForElementClickable(driver, AdminPageUI.EMPLOYEE_SUGGEST_FIRST_ITEM);
        sleepInSecond(3);
        clickToElement(driver, AdminPageUI.EMPLOYEE_SUGGEST_FIRST_ITEM);
        waitForElementAttributeNotEmpty(driver, AdminPageUI.SELECTED_EMPLOYEE_FULL_NAME_DISPLAY, "value");
        String employeeFullName = getElementAttribute(driver, AdminPageUI.SELECTED_EMPLOYEE_FULL_NAME_DISPLAY, "value");
        user.setEmployeeName(employeeFullName);
    }


    private void selectStatus(String status) {
        selectItemInCustomDropdown(driver, AdminPageUI.STATUS_PARENT_DROPDOWN, AdminPageUI.STATUS_CHILDREN_ITEMS, status);
    }

    private void inputUsername(String username) {
        sendKeyToElement(driver, AdminPageUI.USERNAME_TEXTBOX, username);
    }


    private void inputPassword(String password) {
        sendKeyToElement(driver, AdminPageUI.PASSWORD_TEXTBOX, password);
        sendKeyToElement(driver, AdminPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    private void clickSave() {
        clickToElement(driver, AdminPageUI.SAVE_BUTTON);
    }

    public boolean verifySuccess() {
        waitForElementVisible(driver, AdminPageUI.SUCCESS_TOAST);
        return isElementDisplayed(driver, AdminPageUI.SUCCESS_TOAST);
    }

    public void searchUser(String userName) {
        waitForElementInvisible(driver, AdminPageUI.FORM_LOADER);
        sendKeyToElement(driver, AdminPageUI.SEARCH_USERNAME, userName);
        clickToElement(driver, AdminPageUI.SEARCH_BUTTON);
        waitForElementInvisible(driver, AdminPageUI.FORM_LOADER);
    }

    public boolean verifyUserInTable(String value) {
        return isElementDisplayed(driver, AdminPageUI.DYNAMIC_RESULT_ROW, value);
    }

    public boolean isRecordDisplaySuccessfully() {
        return isElementDisplayed(driver, AdminPageUI.RESULT_1_RECORD_FOUND);
    }

    public boolean isHeaderDisplayed() {
        return isElementDisplayed(driver, AdminPageUI.ADMIN_HEADER);
    }
}
