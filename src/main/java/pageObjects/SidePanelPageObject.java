package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.SidePanelPageUI;

public class SidePanelPageObject extends BasePage {
    private WebDriver driver;

    public SidePanelPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AdminPageObject openAdminPage() {
        waitForElementClickable(driver, SidePanelPageUI.MAIN_MENU_ADMIN_PAGE);
        clickToElement(driver, SidePanelPageUI.MAIN_MENU_ADMIN_PAGE);
        return PageGeneratorManager.getAdminPage(driver);
    }

}
