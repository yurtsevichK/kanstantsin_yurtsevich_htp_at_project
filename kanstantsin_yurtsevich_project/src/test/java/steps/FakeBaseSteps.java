package steps;


import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import webdriver.Config;
import webdriver.Driver;

import java.net.MalformedURLException;


public class FakeBaseSteps {
    private static final Logger LOGGER = LogManager.getLogger(FakeBaseSteps.class);

    @Before
    public void beforeTest() throws MalformedURLException {
        LOGGER.info("Initializing WebDriver");
        Driver.initDriver();
    }

    @After
    public void afterTest() {
        LOGGER.info("Killing WebDriver");
 //       Driver.destroy;
    }
}