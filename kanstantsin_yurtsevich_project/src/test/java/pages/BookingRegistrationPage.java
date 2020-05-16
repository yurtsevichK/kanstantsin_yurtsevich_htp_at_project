package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingRegistrationPage extends BookingAbstractPage {

    @FindBy(xpath = "//*[@id='login_name_register']")
    private WebElement emailFiled;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='confirmed_password']")
    private WebElement confirmedPassword;

    BookingRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void provideEmail(String email) {
        emailFiled.sendKeys(email);
        emailFiled.submit();
    }

    public void providePassword(String password) {
        passwordField.sendKeys(password);
        confirmedPassword.click();
        confirmedPassword.sendKeys(password);
        confirmedPassword.submit();
        driver.navigate().refresh();
    }
}
