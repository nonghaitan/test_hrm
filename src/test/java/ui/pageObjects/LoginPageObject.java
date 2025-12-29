package ui.pageObjects;

import commons.base.GlobalConstants;
import org.openqa.selenium.WebDriver;
import ui.pageUIs.LoginPageUI;

public class LoginPageObject extends SidePanelPageObject{
    private WebDriver driver;

    public LoginPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public LoginPageObject openLoginPage() {
        driver.get(GlobalConstants.UI_APP_URL);
        return this;
    }

    public DashboardPageObject loginAsAdminUser() {
        sendKeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, GlobalConstants.UI_ADMIN_USER);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, GlobalConstants.UI_ADMIN_PASSWORD);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getDashboardPage(driver);
    }
}
