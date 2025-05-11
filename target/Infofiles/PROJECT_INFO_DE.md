# i-doit Java-Testautomatisierungsprojekt

Dieses Projekt bietet automatisierte Tests für die i-doit-Anwendung unter Verwendung von Selenium WebDriver mit Java. Es wurde nach dem Page Object Model-Entwurfsmuster erstellt, um die Wartbarkeit und Lesbarkeit zu verbessern.


## Projektübersicht

Das Automatisierungsframework führt die folgenden Operationen aus:
1. Anmeldung an der i-doit-Anwendung
2. Navigation durch die Hardware- und Client-Bereiche
3. Erstellung eines neuen Client-Objekts mit zufällig generierten Daten
4. Navigation durch "Link to this page"
5. Aufnahme eines Screenshots des Ergebnisses

## Technologie-Stack
- Selenium WebDriver 4.15.0
- WebDriverManager 5.5.3
- Maven
- SLF4J (Simple Logging Facade for Java)
- JUnit 5

## Projektstruktur

```
src/main/java/com/idoit/
├── config/         # Konfigurationseinstellungen
│   └── Config.java # URL, Anmeldedaten und Timeouts
├── pages/          # Seitenobjekte
│   ├── BasePage.java         # Basisfunktionalität
│   ├── ClientPage.java       # Client-Seitenoperationen
│   ├── DashboardPage.java    # Dashboard-Seitenoperationen
│   ├── HardwarePage.java     # Hardware-Seitenoperationen
│   ├── LoginPage.java        # Login-Seitenoperationen
│   └── NewObjectPage.java    # Erstellung neuer Objekte
├── utils/          # Hilfsklassen
│   └── RandomUtils.java      # Zufällige Zeichenkettengenerierung
└── IdoitTest.java  # Haupttestausführung
```

## Hauptmerkmale

- **Page Object Model**: Trennt Testlogik von seitenspezifischen Operationen
- **Explizites Warten**: Verwendet Seleniums explizites Warten, um die Testzuverlässigkeit zu verbessern
- **Fluent Interface**: Methodenverkettung für verbesserte Lesbarkeit (Chain-Methode)
- **Screenshot-Erfassung**: Nimmt Screenshots zur Überprüfung der Testergebnisse auf
- **Protokollierung**: Umfassende Protokollierung während der gesamten Testausführung
- **Randomisierte Testdaten**: Generiert einzigartige Daten für jeden Testlauf



 //Aktualisieren Sie die Konfiguration in `src/main/java/com/idoit/config/Config.java` mit Ihren i-doit-Anmeldedaten:
   ```java
   public static final String URL = "ihre-idoit-url";
   public static final String USERNAME = "ihr-benutzername";
   public static final String PASSWORD = "ihr-passwort";
   ```

## Screenshots

Screenshots werden automatisch im Verzeichnis `screenshots` mit einem Zeitstempel im Dateinamen gespeichert.

## Arbeitsprinzipien mit Webelementen

Das Projekt verwendet die folgenden Ansätze für die Arbeit mit Webelementen:

### Element-Locators

Locators sind in den Seitenklassen als Konstanten definiert:

```java
private static final By USERNAME_FIELD = By.xpath("//*[@id=\"login_username\"]");
private static final By PASSWORD_FIELD = By.xpath("//*[@id=\"login_password\"]");
```

### Methoden zum Warten auf Elemente

Grundlegende Wartemethoden sind in der Klasse `BasePage.java` definiert:

```java
protected WebElement waitForElementVisible(By by) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
}

protected WebElement waitForElementClickable(By by) {
    return wait.until(ExpectedConditions.elementToBeClickable(by));
}
```

### Interaktion mit Elementen

Die Interaktion mit Elementen erfolgt über Wrapper, die das Warten beinhalten:

```java
protected void clickElement(By by) {
    waitForElementClickable(by).click();
    logger.info("Clicked element: {}", by);
}

protected void enterText(By by, String text) {
    WebElement element = waitForElementVisible(by);
    element.clear();
    element.sendKeys(text);
    logger.info("Entered text '{}' into element: {}", text, by);
}
```

## Implementierungsdetails

### Testausführungsablauf

Der Haupttestausführungsablauf wird in der Klasse `IdoitTest.java` definiert:

1. WebDriver-Initialisierung und Konfiguration
2. Navigation zur Login-Seite
3. Anmeldung mit Anmeldedaten
4. Navigation zum Hardware-Bereich
5. Navigation zum Client-Bereich
6. Erstellung eines neuen Client-Objekts
7. Aufnahme eines Screenshots des Ergebnisses
8. Bereinigung der Ressourcen
