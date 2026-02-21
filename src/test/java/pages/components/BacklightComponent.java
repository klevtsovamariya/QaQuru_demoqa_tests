package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;

public class BacklightComponent {

    public BacklightComponent checkBacklight(SelenideElement element, String property, String value) {
        element.shouldHave(cssValue(property, value));

        return this;
    }
}
