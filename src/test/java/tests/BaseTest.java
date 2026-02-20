package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.MainPage;
import pages.elementsPages.PracticeFormPage;
import pages.elementsPages.TextBoxPage;

public class BaseTest {
    MainPage mainPage = new MainPage();
    PracticeFormPage registrationPage = new PracticeFormPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;

        Selenide.open("");
    }

    @BeforeEach
    void openWebsite() {
        Selenide.open("");
    }

    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }
}