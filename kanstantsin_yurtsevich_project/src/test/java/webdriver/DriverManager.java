package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    static WebDriver driver;

    public static WebDriver getChromeDriver(){
        String pathToDriver = DriverManager.class.getClassLoader().getResource("webdriver/chromedriver").getPath();
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver();
        return driver;
    }
}
