package pages.elementsPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class ElementsPage {
    private final SelenideElement textBoxButton = $$(".router-link").find(exactText("Text Box"));

    public TextBoxPage openTextBoxPage() {
        textBoxButton.click();

        return new TextBoxPage();
    }
}
