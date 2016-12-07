package net.itarray.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import http.helpers.EnvironmentHelper;
import net.itarray.pages.HomePage;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.driver.WebDriverFactory;
import util.validator.ResponsiveUIValidator;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static environment.EnvironmentFactory.isChrome;
import static environment.EnvironmentFactory.isRemote;

public class HomePageSteps {
    private static WebDriver driver;
    private static HomePage page;
    private static ResponsiveUIValidator uiValidator;
    private WebDriverFactory driverFactory;

    private void initPage() {
        driver.manage().window().maximize();
        page = new HomePage(driver);
        uiValidator = new ResponsiveUIValidator(driver);
        uiValidator.setLinesColor(Color.BLACK);
        uiValidator.setColorForHighlightedElements(Color.GREEN);
        uiValidator.setColorForRootElement(Color.BLUE);
    }

    @Given("^new session of (.+) driver for desktop$")
    public void new_session_of_Chrome_Driver_for_desktop(String browser) {
        Map<String, String> sysProp = new HashMap<String, String>();
        sysProp.put("BROWSER", browser);
        sysProp.put("IS_LOCAL", "true");
        EnvironmentHelper.setEnv(sysProp);
        driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver();
    }

    @Given("^new session of Chrome driver for mobile device '(.+)'$")
    public void new_session_of_Chrome_Driver_for_mobile_device(String mobileDevice) throws IOException {
        Map<String, String> sysProp = new HashMap<String, String>();
        sysProp.put("BROWSER", "Chrome");
        sysProp.put("IS_REMOTE", "true");
        sysProp.put("EXECUTOR", "http://127.0.0.1:9515");
        sysProp.put("MOBILE_DEVICE_EMULATION", mobileDevice);
        EnvironmentHelper.setEnv(sysProp);
        if (isRemote() && isChrome()) {
            Runtime.getRuntime().exec("src/main/resources/drivers/chromedriver");
        }
        driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver();
    }

    @When("^user opens the home page$")
    public void user_opens_the_home_page() {
        initPage();
        driver.get("http://visual.itarray.net/");
    }

    @Then("^top slider element has correct alignment and width between (\\d+) and (\\d+) px$")
    public void top_slider_element_has_correct_alignment(int minWidth, int maxWidth) {
        boolean success = uiValidator.init("Validation of Top Slider Element")
                .findElement(page.topSlider(), "Top Slider")
                .sameOffsetLeftAs(page.gridContainer(), "Grid Container")
                .sameOffsetBottomAs(page.topTextBlock(), "Text Block")
                .widthBetween(minWidth, maxWidth)
                .drawMap()
                .validate();

        Assert.assertTrue("Visual validation of Top Slider is failed", success);
    }

    @Then("^top text block element has correct alignment$")
    public void top_text_block_element_has_correct_alignment() {
        boolean success = uiValidator.init("Validation of Top Text block")
                .findElement(page.topTextBlock(), "Top Text block")
                .sameOffsetRightAs(page.gridContainer(), "Grid Container")
                .sameOffsetTopAs(page.topSlider(), "Top Slider")
                .drawMap()
                .validate();

        Assert.assertTrue("Visual validation of Top Text block is failed", success);
    }

    @Then("^then grid elements have the same sizes, not overlapped and aligned in a grid view (\\d+) x (\\d+)$")
    public void then_grid_elements_have_the_same_sizes_not_overlapped_and_aligned_in_a_grid_view(int columns, int rows) {
        boolean success = uiValidator.init("Validation of a grid view")
                .findElements(page.gridElements())
                .alignedAsGrid(columns, rows)
                .withSameSize()
                .areNotOverlappedWithEachOther()
                .drawMap()
                .validate();

        Assert.assertTrue("Visual validation of elements grid view is failed", success);
    }

    @Then("^every element of grid view have correct background color '(.+)'$")
    public void every_element_of_grid_view_have_correct_background_color(String hexColor) {
        SoftAssertions softly = new SoftAssertions();
        for (WebElement card : page.gridElements()) {
            boolean success = uiValidator.init("Validation of style for each of cards in a grid view")
                    .findElement(card.findElement(By.className("project-details")), "Project details block")
                    .withCssValue("background", hexColor)
                    .withCssValue("color", "#6f6f6f")
                    .notOverlapWith(card.findElement(By.className("gallery-hover-4col")), "Image Container")
                    .sameWidthAs(card.findElement(By.className("gallery-hover-4col")), "Image Container")
                    .drawMap()
                    .validate();
            softly.assertThat(success);

        }
        softly.assertAll();
    }

    @Then("^html report is going to be generated$")
    public void html_report_is_going_to_be_generated() {
        uiValidator.generateReport("Home Page");
    }

    @Then("^web driver is going to be closed$")
    public void web_driver_is_going_to_be_closed() {
        if (driver != null) {
            driver.quit();
        }
    }
}
