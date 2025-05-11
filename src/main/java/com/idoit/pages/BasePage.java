package com.idoit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import com.idoit.config.Config;

/**
 * Base page class with common methods for all page objects.
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected WebDriverWait longWait;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Random random = new Random();

    /**
     * Constructor for the BasePage.
     *
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.DEFAULT_TIMEOUT));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(Config.SHORT_TIMEOUT));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(Config.LONG_TIMEOUT));
    }

    /**
     * Wait for an element to be visible and return it.
     *
     * @param by By locator for the element
     * @return WebElement once it is visible
     */
    protected WebElement waitForElementVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Wait for an element to be clickable and return it.
     *
     * @param by By locator for the element
     * @return WebElement once it is clickable
     */
    protected WebElement waitForElementClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Wait for an element to be present and return it.
     *
     * @param by By locator for the element
     * @return WebElement once it is present in the DOM
     */
    protected WebElement waitForElementPresent(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Wait for an element to be visible and click it.
     *
     * @param by By locator for the element
     */
    protected void clickElement(By by) {
        waitForElementClickable(by).click();
        logger.info("Clicked element: {}", by);
    }

    /**
     * Wait for an element to be visible and enter text.
     *
     * @param by By locator for the element
     * @param text Text to enter
     */
    protected void enterText(By by, String text) {
        WebElement element = waitForElementVisible(by);
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' into element: {}", text, by);
    }

    /**
     * Check if an element is displayed.
     *
     * @param by By locator for the element
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Select a random option from a dropdown, excluding the specified value.
     *
     * @param by By locator for the select element
     * @param excludeValue Value to exclude from selection
     * @return The text of the selected option, or null if no valid option was selected
     */
    protected String selectOption(By by) {
        WebElement selectElement = waitForElementVisible(by);
        Select select = new Select(selectElement);     
        List<WebElement> options = select.getOptions();

        if (options.isEmpty()) {
            logger.warn("No options found in dropdown {}", by);
            return null;
        }
        
        WebElement optionToSelect;
        if (options.size() == 1) {
            optionToSelect = options.get(0);
            logger.info("Only one option available in dropdown {}", by);
        } else {
            optionToSelect = options.get(random.nextInt(options.size()));
            logger.info("Selected random option from {} available choices in dropdown {}", options.size(), by);
        }
        
        String optionText = optionToSelect.getText(); // Get the text of the target option to select it
        select.selectByVisibleText(optionText);
        logger.info("Selected option '{}' from dropdown {}", optionText, by);
        return optionText;
    }
} 