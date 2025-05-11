package com.idoit.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idoit.pages.ClientPage;
import com.idoit.pages.DashboardPage;
import com.idoit.pages.HardwarePage;
import com.idoit.pages.LoginPage;
import com.idoit.pages.NewObjectPage;
import com.idoit.utils.RandomUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Main class to execute the i-doit test automation.
 */
public class IdoitTest {
    private static final Logger logger = LoggerFactory.getLogger(IdoitTest.class);

    public static void main(String[] args) {
        logger.info("Starting the test script...");
        WebDriver driver = null;
        
        try {
            // Setup WebDriver
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            
            // Initialize page objects and navigate through the workflow
            LoginPage loginPage = new LoginPage(driver);
            loginPage.navigateTo();
            
            // Login and verify success
            DashboardPage dashboardPage = loginPage.loginWithDefaultCredentials();
            Thread.sleep(2000); // Additional wait to ensure that elements are loaded.
            if (dashboardPage.isDashboardLoaded()) {
                logger.info("Login successful, dashboard is loaded.");
            } else {
                throw new RuntimeException("Login failed, dashboard is not loaded.");
            }
            
            // Navigate to Hardware section
            HardwarePage hardwarePage = dashboardPage.navigateToHardware();
            
            // Navigate to Client section
            Thread.sleep(2000); // Additional wait because after clicking Hardware, it take some time to load the Tree with elements.
            ClientPage clientPage = hardwarePage.navigateToClientSection();
            
            // Create new client object
            NewObjectPage newObjectPage = clientPage.clickNewObject();
            String randomTitle = "test_" + RandomUtils.generateRandomString(5);
            newObjectPage.createNewObject(randomTitle);
            
            logger.info("Test completed successfully!");
            
            // Take and save screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenshots/test_" + System.currentTimeMillis() + ".png");
            screenshot.renameTo(destFile);
            logger.info("Screenshot saved: {}", destFile.getPath());

            // Wait for a moment to see the final state
            Thread.sleep(10000);
            
        } catch (Exception e) {
            logger.error("An error occurred during test execution: {}", e.getMessage(), e);
        } finally {
            // Clean up resources
            if (driver != null) {
                driver.quit();
                logger.info("WebDriver closed.");
            }
        }
    }
} 