# Проект автоматизации тестирования i-doit на Java

Этот проект представляет собой фреймворк автоматизированного тестирования для приложения i-doit, разработанный с использованием Selenium WebDriver и Java. Проект построен с применением паттерна проектирования Page Object Model (POM) для улучшения поддерживаемости и читаемости кода.

## Обзор проекта

Фреймворк автоматизации выполняет следующие операции:
1. Вход в приложение i-doit с использованием учетных данных
2. Навигация через разделы "Hardware" и "Client"
3. Создание нового объекта клиента со случайно сгенерированными данными
4. Сохранение скриншота результата

## Технологический стек
- Selenium WebDriver 4.15.0
- WebDriverManager 5.5.3
- Maven
- SLF4J (Simple Logging Facade for Java)
- JUnit 5

## Структура проекта

src/main/java/com/idoit/
├── config/         # Настройки конфигурации
│   └── Config.java # URL, учетные данные и таймауты
├── pages/          # Объекты страниц
│   ├── BasePage.java         # Базовая функциональность
│   ├── ClientPage.java       # Операции страницы клиента
│   ├── DashboardPage.java    # Операции страницы панели управления
│   ├── HardwarePage.java     # Операции страницы оборудования
│   ├── LoginPage.java        # Операции страницы входа
│   └── NewObjectPage.java    # Создание нового объекта
├── utils/          # Утилитарные классы
│   └── RandomUtils.java      # Генерация случайных строк
└── IdoitTest.java  # Основное выполнение теста
```

## Ключевые особенности

- **Паттерн Page Object Model**: Разделяет тестовую логику от операций, специфичных для страниц
- **Явные ожидания (Explicit Waits)**: Использует явные ожидания Selenium для повышения надежности тестов
- **Текучий интерфейс (Fluent Interface)**: Цепочка методов для улучшения читаемости кода
- **Захват скриншотов**: Сохраняет скриншоты для проверки результатов тестов
- **Логирование**: Комплексное логирование на протяжении всего выполнения теста
- **Рандомизированные тестовые данные**: Генерирует уникальные данные для каждого запуска теста

## Настройка и конфигурация

1. Клонируйте репозиторий:
   ```
   git clone <url-репозитория>
   cd i-doit_java
   ```

2. Обновите конфигурацию в файле `src/main/java/com/idoit/config/Config.java` своими учетными данными i-doit:
   public static final String URL = "ваш-url-i-doit";
   public static final String USERNAME = "ваш-логин";
   public static final String PASSWORD = "ваш-пароль";
   ```

## Запуск тестов

## Скриншоты

Скриншоты автоматически сохраняются в директории `screenshots` с меткой времени в имени файла.

## Принципы работы с веб-элементами

В проекте используются следующие подходы для работы с веб-элементами:

### Локаторы элементов

Локаторы определены в классах страниц как константы:

private static final By USERNAME_FIELD = By.xpath("//*[@id=\"login_username\"]");
private static final By PASSWORD_FIELD = By.xpath("//*[@id=\"login_password\"]");
```

### Методы ожидания элементов

Базовые методы ожидания определены в классе `BasePage.java`:

protected WebElement waitForElementVisible(By by) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
}

protected WebElement waitForElementClickable(By by) {
    return wait.until(ExpectedConditions.elementToBeClickable(by));
}
```

### Взаимодействие с элементами

Взаимодействие с элементами осуществляется через обертки, которые включают ожидание:

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