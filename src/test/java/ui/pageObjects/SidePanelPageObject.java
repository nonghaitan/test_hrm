package ui.pageObjects;

import commons.base.BasePage;
import org.openqa.selenium.WebDriver;
import ui.pageUIs.SidePanelPageUI;

public class SidePanelPageObject extends BasePage {
    protected WebDriver driver;

    public SidePanelPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AdminPageObject openAdminPage() {
        waitForElementClickable(driver, SidePanelPageUI.MAIN_MENU_ADMIN_PAGE);
        clickToElement(driver, SidePanelPageUI.MAIN_MENU_ADMIN_PAGE);
        return PageGeneratorManager.getAdminPage(driver);
    }
}
