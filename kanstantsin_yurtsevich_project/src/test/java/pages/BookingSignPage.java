package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingSignPage extends BookingAbstractPage {

    @FindBy(xpath = "///*[@id='username']")
    private WebElement emailFiled;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    BookingSignPage(WebDriver driver) {
        super(driver);
    }

    public void provideEmail(String email) {
        emailFiled.sendKeys(email);
        emailFiled.submit();
    }

    public void providePassword(String password) {
        passwordField.sendKeys(password);
        passwordField.submit();
    }
}
