package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverManager {

    public static WebDriver getDriver(Config config) throws MalformedURLException {

        switch (config) {
            case CHROME:
                return getChromeDriver();
            case FF:
                return getFFDriver();
            case EDGE:
                return getEdgeDriver();
            case REMOTE:
                return getRemoteDriver();
            default:
                throw null;
        }
    }

    private static WebDriver getEdgeDriver() {

        return new EdgeDriver();
    }

    private static WebDriver getFFDriver() {
        return new FirefoxDriver();
    }

    private static WebDriver getChromeDriver() {
        String pathToDriver = DriverManager.class.getClassLoader().getResource("webdriver/chromedriver").getPath();
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("--igone-certificate-errors");

        return new ChromeDriver(options);
    }

    private static WebDriver getRemoteDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        RemoteWebDriver webDriver = new RemoteWebDriver(new URL("https://localhost:4444/wd/hub"), options);
        return webDriver;
    }

}
