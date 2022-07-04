package stepDefinition;

import Pages.HomePage;
import Utility.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.Set;

public class checkoutSteps extends BaseClass {



    @Given("User opens {string} browser")
    public void user_opens_chrome_browser(String browser) {
        launchBrowser(browser);
    }
    @And("launch {string} URL")
    public void launch_https_booking_com_url(String url) {
        launchUrl(url);
    }
    @Then("User should see HomePage")
    public void user_should_see_home_page() {
       // homePage.verifyHomePage();
    }
    @When("click on the maximum property location")
    public void click_on_the_maximum_property_location() {
        WebElement property = homePage.getMaximumProperyLocation();
        javascriptScrollToElement(property);
        javascriptClick(property);
    }
    @And("select checkin date and checkout date for {int} days")
    public void select_checkin_date_and_checkout_date_for_days(Integer days) {
        homePage.selectCheckinCheckoutDates(days);
    }
    @And("add {int} adult")
    public void add_adult(Integer adultCount) {
        homePage.addAdult(adultCount);
    }
    @And("add 1 child with {int} years age")
    public void add_child_with_years_age(Integer age) {
        homePage.addChild(1);
    }
    @When("click on search button")
    public void click_on_search_button() {
        WebElement searchButton=homePage.getSearchButton();
        searchButton.click();
    }
    @Then("User should see the property result page")
    public void user_should_see_the_property_result_page() {
       boolean isDisplayed= searchResultPage.getSearchResultBreadcrum().isDisplayed();
        Assert.assertTrue(isDisplayed);
    }
    @And("User apply 5 star rating filter and click maximum rated property")
    public void user_apply_star_rating_filter_and_click_maximum_rated_property() throws InterruptedException {
        searchResultPage.applyFiveStarRatingFilter();
        Thread.sleep(5000);
        WebElement maxRatedProperty=searchResultPage.getMaxRatedProperty();
        BaseClass.javascriptClick(driver,maxRatedProperty);
    }
    @Then("new window should open")
    public void new_window_should_open() {
        String parent=driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        Assert.assertEquals(windows.size(),2);
        for(String window:windows){
            if(!window.equals(parent)){
                driver.switchTo().window(window);
            }
        }
    }
    @And("user should select the amount")
    public void user_should_select_the_amount() {
        WebElement amountDropdown = driver.findElement(By.xpath("//select[contains(@name,'nr_rooms')]"));
        javascriptScrollToElement(amountDropdown);
        Select select= new Select(amountDropdown);
        select.selectByValue("1");

    }
    @And("take the screenshot and close browser")
    public void take_the_screenshot() throws IOException {
        screenshotOfPage("checkout");
        tearDown();
    }

    @And("add {int} rooms")
    public void addRooms(int rooms) {
        homePage.addRoom(rooms);
    }
}
