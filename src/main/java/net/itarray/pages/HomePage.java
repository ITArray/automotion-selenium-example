package net.itarray.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.BaseWebMobileElement;

import java.util.List;

public class HomePage extends BaseWebMobileElement {
    public HomePage(WebDriver driver) {
        super(driver, 10);
    }

    public WebElement mainContainer() {
        return getWebElement(By.className("container"));
    }

    public WebElement topSlider() {
        return getWebElement(By.className("flexslider"));
    }

    public WebElement topTextBlock() {
        return getWebElement(By.className("span4"));
    }

    public WebElement gridContainer() {
        return getWebElement(By.className("clearfix"));
    }

    public List<WebElement> gridElements() {
        return getWebElements(By.className("gallery-item"));
    }
}
