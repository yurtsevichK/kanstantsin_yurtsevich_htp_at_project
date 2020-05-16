package other;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmRegistration {

    protected WebDriver driver;

    private static final String YOPMAIL_URL = "http://www.yopmail.com/en/";

    @FindBy(xpath = "//*[@id='login']")
    private WebElement yopmailEmail;

    @FindBy(xpath = "//*[@id='mailmillieu']//descendant::table//descendant::td[@class='button-inner']")
    private WebElement confirmButton;

    ConfirmRegistration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void confirmRegistration(String email) {
        driver.get(YOPMAIL_URL);
        yopmailEmail.click();
        yopmailEmail.sendKeys(email);
        yopmailEmail.submit();
        confirmButton.click();
    }

}
