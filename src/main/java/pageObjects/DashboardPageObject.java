package pageObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.DashboardPageUI;

public class DashboardPageObject extends SidePanelPageObject{
    private WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isDashboardHeaderDisplayed() {
        return isElementDisplayed(driver, DashboardPageUI.DASHBOARD_HEADER);
    }

    public String getLoggedInUserName() {
        return getElementText(driver, DashboardPageUI.USER_PROFILE_NAME).trim();
    }
}
