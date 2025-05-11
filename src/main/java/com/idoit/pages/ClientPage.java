package com.idoit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object for the Client page in the Hardware section.
 */
public class ClientPage extends BasePage {
    // Locators
    private static final By OVERLAY_MENU = By.xpath("//*[@id=\"new_overlay\"]");
    private static final By OVERLAY_MENU_DROPDOWN = By.xpath("//*[@id=\"navbar_item_C__NAVMODE__NEW_ADD\"]");
    private static final By NEW_OBJECT_OPTION = By.xpath(".//span[contains(text(), \"New object\")]");
    
    /**
     * Constructor for the ClientPage.
     *
     * @param driver WebDriver instance
     */
    public ClientPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click on the "New object" option in the overlay menu.
     *
     * @return NewObjectPage instance for fluent interface
     */
    public NewObjectPage clickNewObject() {
        try {
            // Check if overlay menu is displayed
            if (isElementDisplayed(OVERLAY_MENU)) {
                logger.info("Overlay menu is displayed.");
            } else {
                logger.info("Overlay menu is not displayed. Clicking the dropdown to expand it.");
                clickElement(OVERLAY_MENU_DROPDOWN);
            }
            
            // Click on the "New object" option
            WebElement newObjectElement = waitForElementVisible(OVERLAY_MENU).findElement(NEW_OBJECT_OPTION);
            newObjectElement.click();
            logger.info("Clicked on 'New object' option.");
        } catch (Exception e) {
            logger.error("Error clicking on 'New object': {}", e.getMessage());
            throw new RuntimeException("Failed to click on 'New object'", e);
        }
        
        return new NewObjectPage(driver);
    }
} 