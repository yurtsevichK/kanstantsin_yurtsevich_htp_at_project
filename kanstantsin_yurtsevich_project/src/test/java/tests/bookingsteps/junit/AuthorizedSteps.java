package tests.bookingsteps.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import pages.BookingMainPage;
import pages.BookingMyListPage;
import pages.BookingRegistrationPage;
import pages.BookingResultsPage;
import tests.bookingsteps.preconditions.Registration;
import tests.bookingsteps.preconditions.SignIn;
import tests.searchsteps.cucumber.CucumberSearchSteps;
import other.GenerateFakeAddress;
import webdriver.Driver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AuthorizedSteps {

    private static final String BOOKING_URL = "https://www.booking.com/";
    private static final String WISHLIST_URL = "https://www.booking.com/mywishlist.html";
    private static final String password = "123456QW";
    private static final Logger LOGGER = LogManager.getLogger(CucumberSearchSteps.class);
    BookingMainPage bookingMainPage;
    BookingResultsPage bookingResultPage;
    BookingRegistrationPage bookingRegistrationPage;
    BookingMyListPage myListPage;
    static String email;

    @BeforeClass
    public static void baseActions() throws InterruptedException {
        LOGGER.info("Booking authorized testing starts");
        email = GenerateFakeAddress.generateEmail();
        Registration.performRegistration(Driver.getWebDriver(), email, password, BOOKING_URL);
        LOGGER.info("Registration finished");
        Driver.clearCache();
    }

    @Before
    public void initializePages() {
        bookingMainPage = new BookingMainPage(Driver.getWebDriver());
        bookingResultPage = new BookingResultsPage(Driver.getWebDriver());
        bookingRegistrationPage = new BookingRegistrationPage(Driver.getWebDriver());
        myListPage = new BookingMyListPage(Driver.getWebDriver());
        LOGGER.info("Pages for testing initialized");
    }

    @Test
    public void registrationTest() {
        startBookingAndLogin();
        bookingMainPage.openNotification();
        String expected = "Your account hasn't been activated. That means you're missing out on member perks. " +
                "Would you like us to resend the activation email?";
        ArrayList<String> actual = bookingMainPage.listOfNotifications();
        Assert.assertFalse("User wasn't registered", actual.contains(expected));
    }

    @Test
    public void nextTripTest() {
        String expectedColor = "rgb(204, 0, 0)";
        startBookingAndLogin();
        bookingMainPage.goToSearchResultsWebDriver("Madrid", 1, 0, 2, 3, 5);
        String expectedFirstHotel = bookingResultPage.firstHotelName();
        bookingResultPage.saveFirstItem();
        Driver.waitUntilItemWillBeShown(bookingResultPage.getSavedPopUp());
        bookingResultPage.saveLastItem();
        Driver.waitUntilItemWillBeShown(bookingResultPage.getSavedPopUp());
        String expectedLastHotel = bookingResultPage.lastHotelName();
        String actualColor = bookingResultPage.getColorOfSave();
        bookingResultPage.goToMyListsByLink(WISHLIST_URL);
        Driver.waitUntilItemWillBeShown(myListPage.getViewProperty());
        ArrayList<String> actual = myListPage.getListOfHotels();
        assertEquals("Color isn't red", expectedColor, actualColor);
        Assert.assertTrue("First Hotel is in the List", actual.contains(expectedFirstHotel));
        Assert.assertTrue("Second Hotel is in the List", actual.contains(expectedLastHotel));
    }

    @Test
    public void headerItemsTest() {
        startBookingAndLogin();
        Driver.waitUntilItemWillBeShown(bookingMainPage.getProfileElement());
        assertEquals("Quantity of items isn't expected", 12,
                bookingMainPage.getQuantityOfItemsInHeader());
    }

    @After
    public void clearCache() throws InterruptedException {
        Driver.clearCache();
        LOGGER.info("Cache of browser cleared");
    }


    public void startBookingAndLogin() {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSignIn();
        SignIn.goThroughLogin(Driver.getWebDriver(), email, password);
    }
}
