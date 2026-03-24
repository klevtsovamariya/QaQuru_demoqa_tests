package pages.elementsPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class FormsPage {
    private final SelenideElement practiceFormButton = $$(".router-link").find(exactText("Practice Form"));

    @Step("Открыть страницу Practice Form")
    public PracticeFormPage openPracticeFormPage() {
        practiceFormButton.scrollTo().click();

        return new PracticeFormPage();
    }
}