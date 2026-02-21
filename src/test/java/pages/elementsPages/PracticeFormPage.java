package pages.elementsPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.MainPage;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PracticeFormPage {

    private final ElementsCollection checkLabel = $$(".form-check-label");

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement currentAddressTextArea = $("#currentAddress");
    private final SelenideElement state = $("#state");
    private final SelenideElement city = $("#city");
    private final SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement modalWindow = $("#example-modal-sizes-title-lg");
    private final SelenideElement tableResponsive = $(".table-responsive");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement userForm = $("#userForm");

    public SelenideElement getUserNumberInput() {

        return userNumberInput;
    }

    public SelenideElement getFirstNameInput() {

        return firstNameInput;
    }

    public SelenideElement getLastNameInput() {

        return lastNameInput;
    }

    public SelenideElement getUserEmailInput() {

        return userEmailInput;
    }

    public SelenideElement getDateOfBirthInput() {

        return dateOfBirthInput;
    }

    public SelenideElement getCurrentAddressTextArea() {

        return currentAddressTextArea;
    }

    public SelenideElement getCheckLabelByIndex(int index) {

        return checkLabel.get(index);
    }

    public PracticeFormPage openPage() {
        new MainPage().openFormsPage().openPracticeFormPage();

        return this;
    }

    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormPage clickSubmit() {
        submitButton.scrollTo().click();

        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        genderWrapper.$(byText(gender)).click();

        return this;
    }

    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        CalendarComponent.setDate(day, month, year);

        return this;
    }

    public PracticeFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public PracticeFormPage setHobbies(String hobbies) {
        hobbiesWrapper.$(byText(hobbies)).scrollTo().click();

        return this;
    }

    public PracticeFormPage uploadPicture(String file) {
        uploadPictureInput.scrollTo().uploadFromClasspath("photo/" + file);

        return this;
    }

    public PracticeFormPage setCurrentAddress(String value) {
        currentAddressTextArea.setValue(value);

        return this;
    }

    public void setState(String value) {
        state.click();
        stateCityContainer.$(byText(value)).click();

    }

    public void setCity(String value) {
        city.click();
        stateCityContainer.$(byText(value)).click();

    }

    public PracticeFormPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }

    public PracticeFormPage checkModal(String text) {
        modalWindow.shouldBe(visible)
                .shouldHave(text(text));

        return this;
    }


    public PracticeFormPage checkModalNotVisible() {
        modalWindow.shouldNotBe(visible);

        return this;
    }

    public PracticeFormPage checkTableResponsiveText(String key, String value) {
        tableResponsive.$(byText(key)).parent().shouldHave(text(value));

        return this;
    }

    public PracticeFormPage checkTableResponsiveEmptyValue(String key) {
        tableResponsive.$(byText(key))
                .parent()
                .$("td", 1)
                .shouldHave(exactText(""));

        return this;
    }

    public PracticeFormPage checkBacklight(SelenideElement element, String property, String value) {
        element.shouldHave(cssValue(property, value));

        return this;
    }

    public PracticeFormPage verifyUserFormVisible() {
        userForm.shouldBe(visible);

        return this;
    }
}