package pages.elementsPages;

import com.codeborne.selenide.SelenideElement;

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

    public TextBoxPage fillNameForm(String name) {
        fullNameInput.click();
        fullNameInput.setValue(name);

        return this;
    }

    public TextBoxPage fillEmailForm(String email) {
        emailInput.click();
        emailInput.setValue(email);

        return this;
    }

    public TextBoxPage fillcurrentAddressForm(String currentAddress) {
        currentAddressInput.click();
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    public TextBoxPage fillpermanentAddressForm(String permanentAddress) {
        permanentAddressInput.click();
        permanentAddressInput.setValue(permanentAddress);

        return this;
    }

    public TextBoxPage clickSubmit() {
        submitBtn.click();

        return this;
    }

    public TextBoxPage checkOutputNameText(String name) {
        nameOutput.scrollTo().shouldHave(text("Name:" + name));

        return this;
    }

    public TextBoxPage checkOutputEmailText(String email) {
        emailOutput.scrollTo().shouldHave(text("Email:" + email));

        return this;
    }

    public TextBoxPage checkOutputCurrentAddressText(String currentAddress) {
        currentAddressOutput.scrollTo().shouldHave(text("Current Address :" + currentAddress));

        return this;
    }

    public TextBoxPage checkOutputPermananetAddressText(String permanentAddress) {
        permanentAddressOutput.scrollTo().shouldHave(text("Permananet Address :" + permanentAddress));

        return this;
    }
}