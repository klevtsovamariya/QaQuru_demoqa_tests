package pages;

import com.codeborne.selenide.SelenideElement;
import pages.elementsPages.ElementsPage;
import pages.elementsPages.FormsPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private final SelenideElement formsPageButton = $$(".card-body").find(exactText("Forms"));
    private final SelenideElement elementsButton = $$(".card-body").find(exactText("Elements"));

    public FormsPage openFormsPage() {
        formsPageButton.scrollTo().click();

        return new FormsPage();
    }

    public ElementsPage openElementsPage() {
        elementsButton.scrollTo().click();

        return new ElementsPage();
    }
}