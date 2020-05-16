package webdriver;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver webDriver;

    private Driver() throws IllegalAccessError {
        throw new IllegalAccessError("Cannot create Instance of utility class");
    }

    public static void initDriver() {

    }

    public static WebDriver getWebdriver() {
        if (webDriver == null){
            webDriver = DriverManager.getChromeDriver();
        }
        return webDriver;
    }

    public static void closeDriver() {
        webDriver.close();
        webDriver.quit();
    }

    public static void setTimeout() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    public static void removeTiemout() {
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(0, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(0, TimeUnit.SECONDS);
    }

    public static void maximize() {
        webDriver.manage().window().maximize();
    }
}

