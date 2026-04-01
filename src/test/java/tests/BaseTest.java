package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;
import pages.components.Attach;

import java.util.Map;

public class BaseTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "127.0");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        String loginSelenoid = System.getProperty("loginSelenoid");
        String passwordSelenoid = System.getProperty("passwordSelenoid");
        String urlSelenoid = System.getProperty("urlSelenoid");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://" + loginSelenoid + ":" + passwordSelenoid + "@" + urlSelenoid;
    }

    @BeforeEach
    void openWebsite() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Selenide.open("");
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
 }