package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HeaderComponent {

    public final SelenideElement userNameLabel = $("#userName-label");
    public final SelenideElement userEmailLabel = $("#userEmail-label");
    public final SelenideElement genderLabel = $("#genterWrapper");
    public final SelenideElement userNumberLabel = $("#userNumber-label");
    public final SelenideElement dateOfBirthLabel = $("#dateOfBirth-label");
    public final SelenideElement subjectsLabel = $("#subjectsWrapper [id=subjects-label]");
    public final SelenideElement hobbiesLabel = $("#hobbiesWrapper [id=subjects-label]");
    public final SelenideElement pictureLabel = $$(".form-label#subjects-label").get(2);
    public final SelenideElement currentAddressLabel = $("#currentAddress-label");
    public final SelenideElement stateAndCityLabel = $("#stateCity-label");
    public final SelenideElement mainTitle = $(".practice-form-wrapper h1");
    public final SelenideElement subtitle = $(".practice-form-wrapper h5");
    public final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");

    @Step("Проверить заголовок страницы: '{text}'")
    public HeaderComponent verifyMainTitle(String text) {
        mainTitle.shouldHave(text(text));

        return this;
    }

    @Step("Проверить подзаголовок: '{text}'")
    public HeaderComponent verifySubtitle(String text) {
        subtitle.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label Full Name: '{text}'")
    public HeaderComponent verifyNameLabel(String text) {
        userNameLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label Email: '{text}'")
    public HeaderComponent verifyEmailLabel(String text) {
        userEmailLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label Gender: '{text}'")
    public HeaderComponent verifyGenderLabel(String text) {
        genderLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label Mobile: '{text}'")
    public HeaderComponent verifyMobileLabel(String text) {
        userNumberLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label Date of Birth: '{text}'")
    public HeaderComponent verifyDateOfBirthLabel(String text) {
        dateOfBirthLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label Subjects: '{text}'")
    public HeaderComponent verifySubjectsLabel(String text) {
        subjectsLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label Hobbies: '{text}'")
    public HeaderComponent verifyHobbiesLabel(String text) {
        hobbiesLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label Picture: '{text}'")
    public HeaderComponent verifyPictureLabel(String text) {
        pictureLabel.shouldHave(exactText(text));

        return this;
    }

    @Step("Проверить label Current Address: '{text}'")
    public HeaderComponent verifyCurrentAddressLabel(String text) {
        currentAddressLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить label State and City: '{text}'")
    public HeaderComponent verifyStateAndCityLabel(String text) {
        stateAndCityLabel.shouldHave(text(text));

        return this;
    }

    @Step("Проверить заголовок модального окна: '{text}'")
    public HeaderComponent verifyModalTitle(String text) {
        modalTitle.shouldHave(text(text));

        return this;
    }

}