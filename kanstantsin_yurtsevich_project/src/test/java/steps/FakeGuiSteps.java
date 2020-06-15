package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import webdriver.Driver;

import java.net.MalformedURLException;

public class FakeGuiSteps {
    @Given(value = "I go to tut.by")
    public void checkHeaderTest() throws MalformedURLException {
        Driver.getWebDriver().get("https://tut.by");
    }

    @When("I start waiting")
    public void checkFooterTest() {
    }

    @Then("I verify")
    public void verify() {
        Assert.assertTrue(true);
    }
}