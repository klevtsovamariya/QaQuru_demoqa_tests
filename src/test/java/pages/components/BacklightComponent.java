package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssValue;

public class BacklightComponent {

    @Step("Проверить CSS свойство '{property}' равно '{value}'")
    public BacklightComponent checkBacklight(SelenideElement element, String property, String value) {
        element.shouldHave(cssValue(property, value));

        return this;
    }
}
