# DemoQA Tests

Домашние задания по курсу QA.Guru 40.

Автор: **Чумичева Мария**

Проект представляет собой набор автоматизированных UI-тестов для веб-формы на сайте [DemoQA](https://demoqa.com).

## Стек технологий

| Инструмент | Версия | Назначение |
|---|---|---|
| Java | 17+ | Язык программирования |
| Gradle | 8+ | Сборка проекта |
| JUnit 5 | 5.14.1 | Тестовый фреймворк |
| Selenide | 7.13.0 | Управление браузером |
| Allure | 2.32.0 | Отчётность |
| DataFaker | 2.5.4 | Генерация тестовых данных |
| Selenoid | - | Удалённый запуск браузеров |

## Структура проекта

```
src/test/java/
├── pages/
│   ├── MainPage.java                  # Главная страница demoqa.com
│   ├── components/
│   │   ├── Attach.java                # Аттачменты для Allure (скриншот, видео, логи)
│   │   ├── BacklightComponent.java    # Проверка цвета подсветки полей
│   │   ├── CalendarComponent.java     # Компонент выбора даты
│   │   ├── HeaderComponent.java       # Заголовки страниц и модальных окон
│   │   └── ResultsTableComponent.java # Таблица результатов регистрации
│   └── elementsPages/
│       ├── ElementsPage.java
│       ├── FormsPage.java
│       ├── PracticeFormPage.java      # Форма регистрации студента
│       └── TextBoxPage.java           # Страница Text Box
├── testData/
│   ├── RegistrationFormData.java      # Тестовые данные для формы регистрации
│   └── TextBoxData.java               # Тестовые данные для Text Box
├── tests/
│   ├── BaseTest.java                  # Базовая конфигурация и setup/teardown
│   ├── RegistrationFormTests.java     # Тесты формы регистрации
│   └── TextBoxTests.java              # Тесты Text Box
└── utils/
    └── RandomUtils.java               # Утилиты для генерации случайных данных
```

## Тест-кейсы

### TextBoxTests
- Заполнение всех полей формы (имя, email, адреса)
- Заполнение формы без адресных полей

### RegistrationFormTests
- Проверка заголовков страницы формы регистрации
- Подсветка обязательных полей при пустой форме
- Заполнение всех полей формы
- Регистрация с разными значениями Gender (Male/Female/Other)
- Регистрация с разными именами из CSV-файла
- Регистрация с разными Hobbies (Sports/Reading/Music)
- Проверка некорректного номера телефона
- Пропуск обязательного поля firstName

## Запуск тестов

### Все тесты
```bash
gradlew clean test
```

### Только SMOKE-тесты
```bash
gradlew clean test -Dgroups=SMOKE
```

### Только REGRESS-тесты
```bash
gradlew clean test -Dgroups=REGRESS
```

### Открыть Allure-отчёт
```bash
allure serve build/allure-results
```

## Паттерны и подходы

- **Page Object Model** — логика взаимодействия с UI вынесена в page-классы
- **Fluent Interface** — методы страниц возвращают `this` для chain-вызовов
- **Component Pattern** — переиспользуемые компоненты (Header, Calendar, ResultsTable)
- **@Step (AspectJ)** — аннотированные шаги в page object'ах отображаются в Allure-отчёте
- **Lambda Steps** — шаги через `Allure.step()` в тестах для структурирования сценариев
- **Параметризованные тесты** — `@ValueSource`, `@CsvSource`, `@CsvFileSource`
- **Случайные тестовые данные** — DataFaker генерирует реалистичные данные при каждом запуске
- **Selenoid** — удалённый запуск с записью видео и VNC
