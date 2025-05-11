package com.idoit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.idoit.config.Config;

/**
 * Page object for the login page.
 */
public class LoginPage extends BasePage {
    // Locators
    private static final By USERNAME_FIELD = By.xpath("//*[@id=\"login_username\"]");
    private static final By PASSWORD_FIELD = By.xpath("//*[@id=\"login_password\"]");
    private static final By LOGIN_BUTTON = By.xpath("//*[@id=\"login_submit\"]");
    private static final By TOP_MENU = By.xpath("//*[@id=\"top\"]");

    /**
     * Constructor for the LoginPage.
     *
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to the login page.
     *
     * @return LoginPage instance for fluent interface
     */
    public LoginPage navigateTo() {
        driver.get(Config.URL);
        logger.info("Navigated to login page: {}", Config.URL);
        return this;
    }

    /**
     * Perform login with the provided credentials.
     *
     * @param username Username to use for login
     * @param password Password to use for login
     * @return DashboardPage instance if login is successful
     */
    public DashboardPage login(String username, String password) {
        enterText(USERNAME_FIELD, username);
        enterText(PASSWORD_FIELD, password);
        clickElement(LOGIN_BUTTON);
        
        // Wait for the top menu to be visible, indicating successful login
        waitForElementVisible(TOP_MENU);
        logger.info("Login successful with username: {}", username);
        
        return new DashboardPage(driver);
    }

    /**
     * Perform login with the default credentials from the Config class.
     *
     * @return DashboardPage instance if login is successful
     */
    public DashboardPage loginWithDefaultCredentials() {
        return login(Config.USERNAME, Config.PASSWORD);
    }

    /**
     * Check if login was successful by verifying the top menu is displayed.
     *
     * @return true if login was successful, false otherwise
     */
    public boolean isLoginSuccessful() {
        return isElementDisplayed(TOP_MENU);
    }
} 