package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.BookingMainPage;
import pages.BookingResultsPage;
import webdriver.Driver;

import static org.junit.Assert.assertEquals;

public class BookingSteps {

    protected WebDriver driver;
    private static final String BOOKING_URL = "https://www.booking.com/";
    BookingMainPage bookingMainPage;
    BookingResultsPage bookingResultPage;

    @Before
    public void startBrowser() {
        driver = Driver.getWebdriver();
        Driver.setTimeout();
        Driver.maximize();
        bookingMainPage = new BookingMainPage(driver);
        bookingResultPage = new BookingResultsPage(driver);
    }

    @Test
    public void parisTest() {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSearchResultsWebDriver("Paris", 2, 0, 4, 3, 10);
        int expected = bookingResultPage.getMaxFilterPrice();
        int actual = bookingResultPage.getActualMinSortingPrice(7);
        Assert.assertTrue(expected < actual || expected == actual);
    }

    @Test
    public void moscowTest() {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToElementBuilder("Moscow", 2, 0, 4, 10, 15);
        int expected = bookingResultPage.getMinFilterPrice();
        int actual = bookingResultPage.getActualTopElementPrice(5);
        Assert.assertTrue(expected > actual);
    }

    @Test
    public void osloTest() throws InterruptedException {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSearchResultsWebDriver("Oslo", 1, 2, 2, 1, 2);
        bookingResultPage.filterHotelsByStars();
        String expected = "rgba(255, 0, 0, 1)";
        assertEquals("Color isn't red", expected, bookingResultPage.getColorOfAddress());
    }

    @After
    public void stopBrowser() {
        Driver.closeDriver();
    }
}
