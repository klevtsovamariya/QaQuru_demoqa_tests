package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private final SelenideElement tableResponsive = $(".table-responsive");
    private final SelenideElement modalWindow = $(".modal-content");


    public ResultsTableComponent checkTableResponsiveText(String key, String value) {
        tableResponsive.$(byText(key)).parent().shouldHave(text(value));

        return this;
    }

    public ResultsTableComponent checkTableResponsiveEmptyValue(String key) {
        tableResponsive.$(byText(key))
                .parent()
                .$("td", 1)
                .shouldHave(exactText(""));

        return this;
    }

    public ResultsTableComponent checkModalVisible() {
        modalWindow.shouldBe(visible);

        return this;
    }

    public ResultsTableComponent checkModalNotVisible() {
        modalWindow.shouldNotBe(visible);

        return this;
    }
}