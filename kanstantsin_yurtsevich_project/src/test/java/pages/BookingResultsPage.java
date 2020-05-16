package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static other.BookingUtils.getPriceForNight;

public class BookingResultsPage extends BookingAbstractPage {

    WebDriverWait wait = new WebDriverWait(driver, 10);

    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

    @FindBy(xpath = "//*[@id='filter_price']//descendant::a[@data-id='pri-5']//span[@class!='filter_count']")
    private WebElement maxPrice;

    @FindBy(xpath = "//*[@id='filter_price']//descendant::a[@data-id='pri-1']//span[@class!='filter_count']")
    private WebElement minPrice;

    @FindBy(xpath = "//*[@id='sort_by']//descendant::a[contains(., 'owest')]")
    private WebElement sortPrice;

    @FindBy(xpath = "(//*[@id='hotellist_inner']//descendant::" +
            "div[@class='bui-price-display__value prco-text-nowrap-helper prco-inline-block-maker-helper'])[1]")
    private WebElement topElement;

    @FindBy(xpath = "(//*[@id='hotellist_inner']//div[@class='sr_rooms_table_block clearfix '])" +
            "[10]//descendant::div[@class='bui-price-display__value " +
            "prco-text-nowrap-helper prco-inline-block-maker-helper'']")
    private WebElement tenElement;

    @FindBy(xpath = "//*[@id='b2searchresultsPage']/div[20]")
    private WebElement overlay;

    @FindBy(xpath = "//*[@id='filter_class']//descendant::span[contains(., '3 stars')]")
    private WebElement threeStars;

    @FindBy(xpath = "//*[@id='filter_class']//descendant::span[contains(., '4 stars')]")
    private WebElement fourStars;

    @FindBy(xpath = "(//*[@id='hotellist_inner']//a[@class='hotel_name_link url'])[10]//span[@data-et-click]")
    private WebElement address;

    public BookingResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getMaxFilterPrice() {
        maxPrice.click();
        return getPriceForNight(maxPrice, 1);
    }

    public int getMinFilterPrice() {
        minPrice.click();
        return getPriceForNight(minPrice, 1);
    }

    public void filterHotelsByStars() throws InterruptedException {
        threeStars.click();
        fourStars.click();
        wait.until(ExpectedConditions.invisibilityOf(overlay));
        java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        javascriptFunction();
    }

    void javascriptFunction() {
        scrollPageDown(address);
        hoverHotelName(address);
        changeHotelBackground();
        changeHotelNameColor();
    }

    void scrollPageDown(WebElement element) {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();" +
                "window.scrollBy(0,-100);", element);
    }

    void changeHotelBackground() {
        javascriptExecutor.executeScript("" +
                "document.querySelector('#hotellist_inner > div:nth-child(11)').style.backgroundColor = 'green';");
    }

    void hoverHotelName(WebElement element) {
        String strJavaScript = "var element = arguments[0];"
                + "var mouseEventObj = document.createEvent('MouseEvents');"
                + "mouseEventObj.initEvent( 'mouseover', true, true );"
                + "element.dispatchEvent(mouseEventObj);";
        javascriptExecutor.executeScript(strJavaScript, element);
    }

    void changeHotelNameColor() {
        javascriptExecutor.executeScript(
                "document.querySelector('#hotellist_inner > div:nth-child(11) > " +
                        "div.sr_item_content.sr_item_content_slider_wrapper > div.sr_property_block_main_row > " +
                        "div.sr_item_main_block > div.sr-hotel__title-wrap > h3 > a > " +
                        "span.sr-hotel__name').style.color  = 'red';");
    }

    public int getActualMinSortingPrice(int interval) {
        sortPrice.click();
        wait.until(ExpectedConditions.invisibilityOf(overlay));
        return getPriceForNight(topElement, interval);
    }

    public int getActualTopElementPrice(int interval) {
        wait.until(ExpectedConditions.invisibilityOf(overlay));
        return getPriceForNight(topElement, interval);
    }

    public String getColorOfAddress() {
        return address.getCssValue("color");
    }
}

