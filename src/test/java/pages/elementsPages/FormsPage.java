package pages.elementsPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class FormsPage {
    private final SelenideElement practiceFormButton = $$(".router-link").find(exactText("Practice Form"));

    public PracticeFormPage openPracticeFormPage() {
        practiceFormButton.scrollTo().click();

        return new PracticeFormPage();
    }
}