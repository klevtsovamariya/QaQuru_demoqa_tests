package pages.elementsPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PracticeFormPage {

    private final CalendarComponent calendar = new CalendarComponent();

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
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement userForm = $("#userForm");

    @Step("Получить поле User Number")
    public SelenideElement getUserNumberInput() {

        return userNumberInput;
    }

    @Step("Получить поле First Name")
    public SelenideElement getFirstNameInput() {

        return firstNameInput;
    }

    @Step("Получить поле Last Name")
    public SelenideElement getLastNameInput() {

        return lastNameInput;
    }

    @Step("Получить поле User Email")
    public SelenideElement getUserEmailInput() {

        return userEmailInput;
    }

    @Step("Получить поле Date of Birth")
    public SelenideElement getDateOfBirthInput() {

        return dateOfBirthInput;
    }

    @Step("Получить поле Current Address")
    public SelenideElement getCurrentAddressTextArea() {

        return currentAddressTextArea;
    }

    @Step("Получить label чекбокса по индексу {index}")
    public SelenideElement getCheckLabelByIndex(int index) {

        return checkLabel.get(index);
    }

    @Step("Открыть страницу Practice Form")
    public PracticeFormPage openPage() {
        new MainPage().openFormsPage().openPracticeFormPage();

        return this;
    }

    @Step("Ввести First Name: '{value}'")
    public PracticeFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Ввести Last Name: '{value}'")
    public PracticeFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Ввести Email: '{value}'")
    public PracticeFormPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Нажать кнопку Submit")
    public PracticeFormPage clickSubmit() {
        submitButton.scrollTo().click();

        return this;
    }

    @Step("Выбрать пол: '{gender}'")
    public PracticeFormPage selectGender(String gender) {
        genderWrapper.$(byText(gender)).click();

        return this;
    }

    @Step("Ввести номер телефона: '{value}'")
    public PracticeFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Установить дату рождения: {day}.{month}.{year}")
    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);

        return this;
    }

    @Step("Ввести предмет: '{value}'")
    public PracticeFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Выбрать хобби: '{hobbies}'")
    public PracticeFormPage setHobbies(String hobbies) {
        hobbiesWrapper.$(byText(hobbies)).scrollTo().click();

        return this;
    }

    @Step("Загрузить фото: '{file}'")
    public PracticeFormPage uploadPicture(String file) {
        uploadPictureInput.scrollTo().uploadFromClasspath("photo/" + file);

        return this;
    }

    @Step("Ввести текущий адрес: '{value}'")
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

    @Step("Выбрать штат '{state}' и город '{city}'")
    public PracticeFormPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }

    @Step("Проверить видимость формы")
    public PracticeFormPage verifyUserFormVisible() {
        userForm.shouldBe(visible);

        return this;
    }
}