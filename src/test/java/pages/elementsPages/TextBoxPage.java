package pages.elementsPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {
    private final SelenideElement fullNameInput = $("#userName");
    private final SelenideElement nameOutput = $("#output #name");
    private final SelenideElement emailOutput = $("#output #email");
    private final SelenideElement currentAddressOutput = $("#output #currentAddress");
    private final SelenideElement permanentAddressOutput = $("#output #permanentAddress");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitBtn = $("#submit");

    @Step("Заполнить поле Full Name значением '{name}'")
    public TextBoxPage fillNameForm(String name) {
        fullNameInput.click();
        fullNameInput.setValue(name);

        return this;
    }

    @Step("Заполнить поле Email значением '{email}'")
    public TextBoxPage fillEmailForm(String email) {
        emailInput.click();
        emailInput.setValue(email);

        return this;
    }

    @Step("Заполнить поле Current Address значением '{currentAddress}'")
    public TextBoxPage fillcurrentAddressForm(String currentAddress) {
        currentAddressInput.click();
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    @Step("Заполнить поле Permanent Address значением '{permanentAddress}'")
    public TextBoxPage fillpermanentAddressForm(String permanentAddress) {
        permanentAddressInput.click();
        permanentAddressInput.setValue(permanentAddress);

        return this;
    }

    @Step("Нажать кнопку Submit")
    public TextBoxPage clickSubmit() {
        submitBtn.click();

        return this;
    }

    @Step("Проверить текст Name в выводе: '{name}'")
    public TextBoxPage checkOutputNameText(String name) {
        nameOutput.scrollTo().shouldHave(text("Name:" + name));

        return this;
    }

    @Step("Проверить текст Email в выводе: '{email}'")
    public TextBoxPage checkOutputEmailText(String email) {
        emailOutput.scrollTo().shouldHave(text("Email:" + email));

        return this;
    }

    @Step("Проверить текст Current Address в выводе: '{currentAddress}'")
    public TextBoxPage checkOutputCurrentAddressText(String currentAddress) {
        currentAddressOutput.scrollTo().shouldHave(text("Current Address :" + currentAddress));

        return this;
    }

    @Step("Проверить текст Permanent Address в выводе: '{permanentAddress}'")
    public TextBoxPage checkOutputPermananetAddressText(String permanentAddress) {
        permanentAddressOutput.scrollTo().shouldHave(text("Permananet Address :" + permanentAddress));

        return this;
    }
}