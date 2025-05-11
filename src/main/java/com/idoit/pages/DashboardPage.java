package com.idoit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

/**
 * Page object for the dashboard page after successful login.
 */
public class DashboardPage extends BasePage {
    // Locators
    private static final By TOP_MENU = By.xpath("//*[@id=\"top\"]");
    private static final By DROPDOWN_MENU = By.xpath("//*[@id=\"menuItem_object-type-group\"]/a");
    private static final By DROPDOWN_MENU_EXPANDED = By.xpath("//*[@id=\"object-type-group-dropdown\"]");
    private static final By HARDWARE_IN_DROPDOWN = By.xpath(".//*[contains(text(), \"Hardware\")]");
    private static final By HARDWARE_IN_TOP_MENU = By.xpath(".//*[contains(text(), \"Hardware\")]");
    
    /**
     * Constructor for the DashboardPage.
     *
     * @param driver WebDriver instance
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Verify if the dashboard page is loaded by checking if the top menu is displayed.
     *
     * @return true if the dashboard is loaded, false otherwise
     */
    public boolean isDashboardLoaded() {
        return isElementDisplayed(TOP_MENU);
    }
    
    /**
     * Navigate to the Hardware section by finding and clicking the Hardware element.
     *
     * @return HardwarePage instance for fluent interface
     */
    public HardwarePage navigateToHardware() {
        WebElement hardwareElement = null;
        
        try {
            // Check if dropdown menu exists and is displayed
            if (isElementDisplayed(DROPDOWN_MENU)) {
                logger.info("Dropdown menu is displayed. Clicking it to find 'Hardware'.");
                clickElement(DROPDOWN_MENU);
                
                // Wait for dropdown to expand
                WebElement expandedDropdown = waitForElementVisible(DROPDOWN_MENU_EXPANDED);
                hardwareElement = expandedDropdown.findElement(HARDWARE_IN_DROPDOWN);
                logger.info("Hardware element found in the dropdown menu.");
            }
        } catch (Exception e) {
            logger.info("Dropdown menu does not exist or error occurred. Searching for 'Hardware' in the top menu.");
        }
        
        // If dropdown approach fails, search in the top menu
        if (hardwareElement == null) {
            try {
                WebElement topMenuElement = waitForElementVisible(TOP_MENU);
                hardwareElement = topMenuElement.findElement(HARDWARE_IN_TOP_MENU);
                logger.info("Hardware element found in the top menu.");
            } catch (NoSuchElementException e) {
                throw new RuntimeException("'Hardware' element not found in the top menu or dropdown menu.", e);
            }
        }
        
        // Click the Hardware element
        hardwareElement.click();
        logger.info("Clicked on Hardware element");
        
        return new HardwarePage(driver);
    }
} 