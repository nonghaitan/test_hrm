package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private WebDriverWait explicitWait;
    private JavascriptExecutor jsExecutor;
    private WebElement element;

    private WebElement getElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    private By getByLocator(String locatorType) {
        By by;
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("locatorType type is not supported!");
        }
        return by;
    }

    private String getDynamicXpath(String locatorType, String... params) {
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
            locatorType = String.format(locatorType, (Object[]) params);
        }
        return locatorType;
    }

    protected void clickToElement(WebDriver driver, String locatorType) {
        waitForElementClickable(driver, locatorType);
        getElement(driver, locatorType).click();
    }

    protected void clickToElement(WebDriver driver, String locatorType, String... params) {
        locatorType = getDynamicXpath(locatorType, params);
        clickToElement(driver, locatorType);
    }

    protected void sendKeyToElement(WebDriver driver, String locatorType, String value) {
        waitForElementVisible(driver, locatorType);
        highlightElement(driver, locatorType);
        element = getElement(driver, locatorType);
        element.clear();
        element.sendKeys(value);
    }

    protected String getElementText(WebDriver driver, String locatorType) {
        waitForElementVisible(driver, locatorType);
        return getElement(driver, locatorType).getText().trim();
    }

    protected String getElementText(WebDriver driver, String locatorType, String... params) {
        locatorType = getDynamicXpath(locatorType, params);
        return getElementText(driver, locatorType);
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        clickToElement(driver, parentLocator);

        List<WebElement> allItems = waitForAllElementsPresence(driver, childItemLocator);

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);

                item.click();
                break;
            }
        }
    }



    protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
        try {
            return getElement(driver, locatorType).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
        return getElement(driver, getDynamicXpath(locator, params)).isDisplayed();
    }


    protected void waitForElementVisible(WebDriver driver, String locatorType) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    protected void waitForElementClickable(WebDriver driver, String locatorType) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public List<WebElement> waitForAllElementsPresence(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        return explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((getByLocator(locator))));
    }


    protected void highlightElement(WebDriver driver, String locatorType) {
        try {
            jsExecutor = (JavascriptExecutor) driver;
            element = getElement(driver, locatorType);
            String originalStyle = element.getAttribute("style");
            jsExecutor.executeScript(
                    "arguments[0].setAttribute(arguments[1], arguments[2])",
                    element, "style", "border: 2px solid red; border-style: dashed;");
            jsExecutor.executeScript(
                    "arguments[0].setAttribute(arguments[1], arguments[2])",
                    element, "style", originalStyle);
        } catch (Exception ignored) {}
    }

    protected void clickToElementByJS(WebDriver driver, String locatorType) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locatorType));
    }
}

