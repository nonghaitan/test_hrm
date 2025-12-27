package commons;

import org.openqa.selenium.WebDriver;

public class DriverManager {
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public static WebDriver getDriver(){
//        return driver.get();
//    }
//
//    public static void setDriver(WebDriver driverValue){
//        driver.set(driverValue);
//    }
//
//    public static void quitDriver(){
//        if(driver.get()!=null){
//            driver.get().quit();
//            driver.remove();
//        }
//    }

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
