package tests.trashmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TrashMailNewUser {
    private static String trashmailPath = "src\\test\\java\\properties\\trashMail.properties";
    private static boolean firstTime = true;
    private static String bookingPath = "src\\test\\java\\properties\\booking.properties";

    public static void trashMailGetNewMail(WebDriver driver) throws InterruptedException, IOException {
        Properties prop = Steps.getProperties(trashmailPath);
        driver.get("https://trashmail.com/");
        if (firstTime)
            Steps.findElementSendKeys(driver, "//*[@id=\"fe-mob-forward\"]", prop.getProperty("EMAIL"));
        getNewMail(driver);
        Steps.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]");
        Steps.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]/option[contains(text(), \"1\")]");
        Steps.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]");
        Steps.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]/option[contains(text(), \"1 day\")]");
        Steps.findElementClick(driver, "//*[@id=\"fe-mob-submit\"]");
        TimeUnit.SECONDS.sleep(2);
        if (driver.findElements(By.xpath("//*[contains(text(), \"address is not registered\")]")).size() > 0) {
            firstTime = false;
            trashmailRegistration(driver);
            trashMailGetNewMail(driver);
        }
        TimeUnit.SECONDS.sleep(3);
        String trashMail = Steps.findElementGetText(driver, "//*[contains(text(), \"@trashmail.com\")]");
    }

    private static void getNewMail(WebDriver driver) throws IOException {
        String newMail = Steps.findElementGetAttribute(driver, "//*[@id=\"fe-mob-name\"]", "value");
        newMail = newMail.concat("@trashmail.com");
        MailSteps.putEmailInProperty(newMail, bookingPath);
    }

    private static void trashmailRegistration(WebDriver driver) throws InterruptedException, IOException {
        Properties prop = Steps.getProperties(trashmailPath);
        Steps.findElementClick(driver, "//*[contains(@href, \"mob-register\")]");
        TimeUnit.SECONDS.sleep(1);
        Steps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[1]/input", prop.getProperty("LOGIN"));
        Steps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[2]/input", prop.getProperty("PASSWORD"));
        Steps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[3]/input", prop.getProperty("PASSWORD"));
        Steps.findElementClick(driver, "//*[@id=\"tab-mob-register\"]/form/div[6]/button");
        TimeUnit.SECONDS.sleep(7);
        MailSteps.confirmLinkOnYandexMail("TrashMail", driver);
        Steps.findElementClick(driver, "//*[contains(@href, \"trashmail\")]");
        TimeUnit.SECONDS.sleep(7);
    }

    // @After
    //public void postCondition() {
    // BaseSteps.destroyDriver(driver);
    //}
}
