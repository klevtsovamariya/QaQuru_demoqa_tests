package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private final SelenideElement tableResponsive = $(".table-responsive");
    private final SelenideElement modalWindow = $(".modal-content");


    @Step("Проверить в таблице строку '{key}' со значением '{value}'")
    public ResultsTableComponent checkTableResponsiveText(String key, String value) {
        tableResponsive.$(byText(key)).parent().shouldHave(text(value));

        return this;
    }

    @Step("Проверить пустое значение в таблице для строки '{key}'")
    public ResultsTableComponent checkTableResponsiveEmptyValue(String key) {
        tableResponsive.$(byText(key))
                .parent()
                .$("td", 1)
                .shouldHave(exactText(""));

        return this;
    }

    @Step("Проверить что модальное окно отображается")
    public ResultsTableComponent checkModalVisible() {
        modalWindow.shouldBe(visible);

        return this;
    }

    @Step("Проверить что модальное окно не отображается")
    public ResultsTableComponent checkModalNotVisible() {
        modalWindow.shouldNotBe(visible);

        return this;
    }
}