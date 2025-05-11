# i-doit Java Test Automation

This project is a Java implementation of an automated test for the i-doit application. It uses Selenium WebDriver to interact with the web interface and follows the Page Object Model design pattern.

## Project Structure

```
src/main/java/com/idoit/
├── config/         # Configuration settings
├── pages/          # Page objects for each page in the application
├── utils/          # Utility classes
└── IdoitTest.java  # Main test execution class
```

## Features

- **Page Object Model**: The test is designed using the Page Object pattern for better maintainability and reusability.
- **Explicit Waits**: Instead of static delays, the test uses explicit waits to make the test more reliable and faster.
- **Random Data Generation**: The test can generate random values for input fields.
- **WebDriverManager**: Automatic WebDriver binary management and configuration.

## Running the Test
```bash
mvn clean compile exec:java -Dexec.mainClass="com.idoit.IdoitTest"
```

## Test Workflow

1. Open Chrome browser
2. Navigate to i-doit login page
3. Log in with the provided credentials
4. Navigate to the Hardware section
5. Navigate to the Client section
6. Create a new client object with random attribute values
7. Save the object
8. Navigate to the "Link to this page"
8. Close the browser

