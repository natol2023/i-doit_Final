package com.idoit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.idoit.utils.RandomUtils;

/**
 * Page object for the New Object page.
 */
public class NewObjectPage extends BasePage {
    // Locators
    private static final By TITLE_FIELD = By.xpath("//*[@id=\"C__CATG__GLOBAL_TITLE\"]");
    private static final By SAVE_BUTTON = By.xpath("//*[@id=\"navbar_item_C__NAVMODE__SAVE\"]");
    private static final By MANUFACTURER_SELECT = By.xpath("//*[@id=\"C__CATG__MODEL_MANUFACTURER\"]");
    private static final By MODEL_TITLE_SELECT = By.xpath("//*[@id=\"C__CATG__MODEL_TITLE_ID\"]");
    private static final By PRODUCT_ID_FIELD = By.xpath("//*[@id=\"C__CATG__MODEL_PRODUCTID\"]");
    private static final By SERVICE_TAG_FIELD = By.xpath("//*[@id=\"C__CATG__MODEL_SERVICE_TAG\"]");
    private static final By SERIAL_FIELD = By.xpath("//*[@id=\"C__CATG__MODEL_SERIAL\"]");
    private static final By FIRMWARE_FIELD = By.xpath("//*[@id=\"C__CATG__MODEL_FIRMWARE\"]");
    private static final By COMMENT_FIELD = By.xpath("//*[@id=\"C__CMDB__CAT__COMMENTARY_02\"]");
    private static final By LINK_TO_THIS_PAGE = By.xpath("//a[@title='Link to this page']");
    
    /**
     * Constructor for the NewObjectPage.
     *
     * @param driver WebDriver instance
     */
    public NewObjectPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enter a title for the new object.
     *
     * @param title The title to enter
     * @return NewObjectPage instance for fluent interface
     */
    public NewObjectPage enterTitle(String title) {
        enterText(TITLE_FIELD, title);
        return this;
    }
    
    /**
     * Click the save button.
     *
     * @return NewObjectPage instance for fluent interface
     */
    public NewObjectPage clickSave() {
        clickElement(SAVE_BUTTON);
        return this;
    }
    
    /**
     * Select a random manufacturer from the dropdown, excluding "-".
     *
     * @return NewObjectPage instance for fluent interface
     */
    public NewObjectPage selectRandomManufacturer() {
        selectOption(MANUFACTURER_SELECT);
        return this;
    }
    
    /**
     * Select a random model title from the dropdown, falling back to "-" if needed.
     *
     * @return NewObjectPage instance for fluent interface
     */
    public NewObjectPage selectRandomModelTitle() {
        String selected = selectOption(MODEL_TITLE_SELECT);
        
        // If no valid option was selected (returned null), select "-" as fallback
        if (selected == null) {
            WebElement selectElement = waitForElementVisible(MODEL_TITLE_SELECT);
            Select select = new Select(selectElement);
            select.selectByVisibleText("-");
            logger.info("No valid options found. Selected '-' as fallback.");
        }
        
        return this;
    }
    
    /**
     * Fill in all the remaining fields with random values.
     *
     * @return NewObjectPage instance for fluent interface
     */
    public NewObjectPage fillRemainingFields() {
        enterText(PRODUCT_ID_FIELD, RandomUtils.generateRandomString(12));
        enterText(SERVICE_TAG_FIELD, RandomUtils.generateRandomString(8));
        enterText(SERIAL_FIELD, RandomUtils.generateRandomString(15));
        enterText(FIRMWARE_FIELD, RandomUtils.generateRandomString(10));
        enterText(COMMENT_FIELD, RandomUtils.generateRandomString(20));
        
        logger.info("Filled all remaining fields with random values.");
        return this;
    }
    
    /**
     * Complete the entire form with default/random values and save.
     *
     * @param title The title to use for the object
     * @return NewObjectPage instance for fluent interface
     */
    public NewObjectPage createNewObject(String title) {
        enterTitle(title);
        clickSave();
        
        // Wait for manufacturer select to appear
        waitForElementVisible(MANUFACTURER_SELECT);
        selectRandomManufacturer();
        fillRemainingFields();
        selectRandomModelTitle(); // Placed before final save to ensure that model list is loaded and populated with data.
        clickSave();
        clickElement(LINK_TO_THIS_PAGE);


        return this;
    }
} 