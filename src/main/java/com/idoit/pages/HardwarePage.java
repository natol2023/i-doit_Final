package com.idoit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object for the Hardware page.
 */
public class HardwarePage extends BasePage {
    // Locators
    private static final By MENU_TREE = By.xpath("//*[@id=\"menu_tree\"]");
    private static final By CLIENT_NODE = By.xpath(".//span[contains(text(), \"Client\")]");
    private static final By OVERLAY_MENU = By.xpath("//*[@id=\"new_overlay\"]");
    private static final By OVERLAY_MENU_DROPDOWN = By.xpath("//*[@id=\"navbar_item_C__NAVMODE__NEW_ADD\"]");
    private static final By NEW_OBJECT_OPTION = By.xpath(".//span[contains(text(), \"New object\")]");
    
    /**
     * Constructor for the HardwarePage.
     *
     * @param driver WebDriver instance
     */
    public HardwarePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to the Client section by clicking on the Client node in the menu tree.
     *
     * @return ClientPage instance for fluent interface
     */
    public ClientPage navigateToClientSection() {
        // Wait for the menu tree to be present and visible
        WebElement menuTree = waitForElementVisible(MENU_TREE);
        logger.info("Menu tree is displayed.");
        
        // Find and click the client node inside the menu tree
        WebElement clientNode = menuTree.findElement(CLIENT_NODE);
        clientNode.click();
        logger.info("Clicked on Client node in the menu tree.");
        
        return new ClientPage(driver);
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