package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static other.BookingUtils.generateDateXpath;


public class BookingMainPage extends BookingAbstractPage {

    protected Actions builder;

    @FindBy(xpath = "//*[@id='ss']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='frm']//descendant::div[@class='xp__dates xp__group']")
    private WebElement checkInList;

    @FindBy(xpath = "//*[@id='xp__guests__toggle']")
    private WebElement adultsList;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group__field-adults']" +
            "//descendant::span[@class='bui-stepper__display']")
    private WebElement adultsQuantity;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group__field-adults']/" +
            "/descendant::button[@class='bui-button bui-button--secondary bui-stepper__add-button ']")
    private WebElement plusButtonAdults;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group-children ']//descendant::span[@class='bui-stepper__display']")
    private WebElement childrenQuantity;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group-children ']" +
            "//descendant::button[@class='bui-button bui-button--secondary bui-stepper__add-button ']")
    private WebElement plusButtonChildren;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group__field-rooms']//" +
            "descendant::span[@class='bui-stepper__display']")
    private WebElement roomsQuantity;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group__field-rooms']//" +
            "descendant::button[@class='bui-button bui-button--secondary bui-stepper__add-button ']")
    private WebElement plusButtonRooms;

    @FindBy(xpath = "//*[@id='frm']//descendant::button[@data-sb-id='main']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='b_tt_holder_3']//descendant::div[contains(., 'Register')]")
    private WebElement registration;

    @FindBy(xpath = "//*[@id='current_account']//span")
    private WebElement signIn;

    @FindBy(xpath = "//*[@id='b_tt_holder_1']")
    private WebElement notifications;

    @FindBy(xpath = "//*[@class='uc-notification__text'][contains(., 't been activated')]")
    private WebElement notConfirmed;

    public BookingMainPage(WebDriver driver) {
        super(driver);
        this.builder = new Actions(driver);
    }

    public void openMainPage(String baseUrl) {
        driver.get(baseUrl);
    }

    public void goToSearchResultsWebDriver(String city, int rooms, int children, int adults,
                                           int startFrom, int endingDate) {
        searchField.click();
        searchField.sendKeys(city);
        checkInList.click();
        WebElement startDate = driver.findElement(By.xpath(generateDateXpath(startFrom)));
        startDate.click();
        WebElement endDate = driver.findElement(By.xpath(generateDateXpath(endingDate)));
        endDate.click();
        adultsList.click();
        String quan;
        for (quan = adultsQuantity.getText(); !quan.equals(String.valueOf(adults)); quan = adultsQuantity.getText()) {
            plusButtonAdults.click();
        }
        for (quan = childrenQuantity.getText(); !quan.equals(String.valueOf(children)); quan = childrenQuantity.getText()) {
            plusButtonChildren.click();
        }
        for (quan = roomsQuantity.getText(); !quan.equals(String.valueOf(rooms)); quan = roomsQuantity.getText()) {
            plusButtonRooms.click();
        }
        searchButton.click();
    }

    public void goToElementBuilder(String city, int rooms, int children, int adults,
                                   int startFrom, int endingDate) {
        WebElement startDate = driver.findElement(By.xpath(generateDateXpath(startFrom)));
        WebElement endDate = driver.findElement(By.xpath(generateDateXpath(endingDate)));
        builder.moveToElement(searchField)
                .click()
                .sendKeys(city)
                .moveToElement(checkInList)
                .click()
                .moveToElement(startDate)
                .click()
                .moveToElement(endDate)
                .click()
                .moveToElement(adultsList)
                .click()
                .build()
                .perform();
        String quan;
        for (quan = adultsQuantity.getText(); !quan.equals(String.valueOf(adults)); quan = adultsQuantity.getText()) {
            builder.moveToElement(plusButtonAdults).click().build().perform();
        }
        for (quan = childrenQuantity.getText(); !quan.equals(String.valueOf(children)); quan = childrenQuantity.getText()) {
            builder.moveToElement(plusButtonChildren).click().build().perform();
        }
        for (quan = roomsQuantity.getText(); !quan.equals(String.valueOf(rooms)); quan = roomsQuantity.getText()) {
            builder.moveToElement(plusButtonRooms).click().build().perform();
        }
        builder.moveToElement(searchButton).click().build().perform();
    }

    public void goToRegistration(){
        registration.click();
    }

    public void openNotification(){
        notifications.click();
    }

    public void goToSignIn() {
        signIn.click();
    }
}

