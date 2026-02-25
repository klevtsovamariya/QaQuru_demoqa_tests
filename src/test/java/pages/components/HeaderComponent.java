package pages.components;

import com.codeborne.selenide.SelenideElement;

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

    public HeaderComponent verifyMainTitle(String text) {
        mainTitle.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifySubtitle(String text) {
        subtitle.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyNameLabel(String text) {
        userNameLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyEmailLabel(String text) {
        userEmailLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyGenderLabel(String text) {
        genderLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyMobileLabel(String text) {
        userNumberLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyDateOfBirthLabel(String text) {
        dateOfBirthLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifySubjectsLabel(String text) {
        subjectsLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyHobbiesLabel(String text) {
        hobbiesLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyPictureLabel(String text) {
        pictureLabel.shouldHave(exactText(text));

        return this;
    }

    public HeaderComponent verifyCurrentAddressLabel(String text) {
        currentAddressLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyStateAndCityLabel(String text) {
        stateAndCityLabel.shouldHave(text(text));

        return this;
    }

    public HeaderComponent verifyModalTitle(String text) {
        modalTitle.shouldHave(text(text));

        return this;
    }

}