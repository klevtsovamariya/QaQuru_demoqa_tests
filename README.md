# DemoQA Tests

Домашние задания по курсу QA.Guru 40.

Автор: **Чумичева Мария**

Проект представляет собой набор автоматизированных UI-тестов для веб-формы на сайте [DemoQA](https://demoqa.com).

## Стек технологий

| Инструмент | Версия | Назначение |
|---|---|---|
| Java | 21 | Язык программирования |
| Gradle | 8+ | Сборка проекта |
| JUnit 5 | 5.14.1 | Тестовый фреймворк |
| Selenide | 7.13.0 | Управление браузером |
| Allure | 2.32.0 | Отчётность |
| DataFaker | 2.5.4 | Генерация тестовых данных |
| Selenoid | - | Удалённый запуск браузеров |
| Jenkins | - | CI/CD |

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

| Тест | Тег |
|---|---|
| Проверка заголовков страницы формы регистрации | — |
| Подсветка обязательных полей при пустой форме и клике Submit | — |
| Заполнение всех полей формы | REGRESS |
| Регистрация с разными значениями Gender (Male/Female/Other) | SMOKE |
| Регистрация с разными именами из CSV-файла | REGRESS |
| Регистрация с разными Hobbies (Sports/Reading/Music) | SMOKE |
| Проверка некорректного номера телефона | — |
| Пропуск обязательного поля firstName | — |

## Параметры запуска

| Параметр | Описание | Значение по умолчанию |
|---|---|---|
| `browser` | Браузер | `chrome` |
| `browserVersion` | Версия браузера | `127` |
| `browserSize` | Размер окна | `1920x1080` |
| `baseUrl` | URL тестируемого сайта | — |
| `headless` | Безголовый режим | `false` |
| `urlSelenoid` | Адрес Selenoid (без протокола) | — |
| `loginSelenoid` | Логин для Selenoid | — |
| `passwordSelenoid` | Пароль для Selenoid | — |

## Запуск тестов

### Все тесты (локально)
```bash
./gradlew clean test \
  -Dbrowser=chrome \
  -DbrowserVersion=127 \
  -DbrowserSize=1920x1080 \
  -DbaseUrl=https://demoqa.com \
  -Dheadless=false
```

### Все тесты (через Selenoid)
```bash
./gradlew clean test \
  -Dbrowser=chrome \
  -DbrowserVersion=127 \
  -DbrowserSize=1920x1080 \
  -DbaseUrl=https://demoqa.com \
  -Dheadless=false \
  -DurlSelenoid=<selenoid-host>/wd/hub \
  -DloginSelenoid=<login> \
  -DpasswordSelenoid=<password>
```

### Только SMOKE-тесты
```bash
./gradlew clean test -Dgroups=SMOKE
```

### Только REGRESS-тесты
```bash
./gradlew clean test -Dgroups=REGRESS
```

### Открыть Allure-отчёт
```bash
allure serve build/allure-results
```

## Запуск в Jenkins

В Jenkins-задаче настроены параметры:

| Параметр Jenkins | Gradle-параметр |
|---|---|
| `BROWSER` | `-Dbrowser` |
| `BROWSER_VERSION` | `-DbrowserVersion` |
| `BROWSER_SIZE` | `-DbrowserSize` |
| `BASE_URL` | `-DbaseUrl` |

Пример команды в Jenkins:
```
clean test
  -Dbrowser=${BROWSER}
  -DbrowserVersion=${BROWSER_VERSION}
  -DbrowserSize=${BROWSER_SIZE}
  -DbaseUrl=${BASE_URL}
  -Dheadless=false
  -DurlSelenoid=<selenoid-host>/wd/hub
  -DloginSelenoid=<login>
  -DpasswordSelenoid=<password>
```

## Уведомления в Telegram

После завершения сборки Jenkins автоматически отправляет уведомление в Telegram-чат с результатами тестов через [allure-notifications](https://github.com/qa-guru/allure-notifications).

### Как это работает

В Post Build Task Jenkins выполняется скрипт:

```bash
# Скачать jar (если ещё не скачан)
FILE=allure-notifications-4.11.0.jar
if [ ! -f "$FILE" ]; then
  wget https://github.com/qa-guru/allure-notifications/releases/download/4.11.0/allure-notifications-4.11.0.jar
fi

# Отправить уведомление
java -DconfigFile=notification/config.json -jar ../allure-notifications-4.11.0.jar
```

### Конфигурация (notification/config.json)

Файл `notification/config.json` создаётся в Jenkins через шаг **Config File Provider**:

```json
{
  "base": {
    "logo": "",
    "project": "DemoQA Tests",
    "comment": "Результаты прогона",
    "nameTest": "UI Tests",
    "allureFolder": "allure-report/",
    "enableChart": true
  },
  "telegram": {
    "token": "<BOT_TOKEN>",
    "chat": "<CHAT_ID>"
  }
}
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
