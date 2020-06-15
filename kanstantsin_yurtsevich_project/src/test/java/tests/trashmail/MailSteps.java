package tests.trashmail;

import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailSteps {

    public static String YANDEX_PATH = "src\\test\\java\\properties\\yandexMail.properties";

    public static void confirmLinkOnYandexMail(String sender, WebDriver driver) throws InterruptedException, IOException {
        driver.get("https://mail.yandex.ru/");
        Properties prop = Steps.getProperties(YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        Steps.findElementClick(driver, "//*[contains(@class, \"HeadBanner-Button-Enter\")]");
        Steps.findElementSendKeys(driver, "//*[@id= \"passp-field-login\"]", prop.getProperty("EMAIL"));
        Steps.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(2);
        Steps.findElementSendKeys(driver, "//*[@id= \"passp-field-passwd\"]", prop.getProperty("PASSWORD"));
        Steps.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(5);
        Steps.findElementClick(driver, String.format("//*[contains(text(), \"%s\")]", sender));
        TimeUnit.SECONDS.sleep(2);
    }

    public static void putEmailInProperty(String newMail, String propertyPath) throws IOException {
        Properties prop = Steps.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
}
