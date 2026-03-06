package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.components.BacklightComponent;
import pages.components.HeaderComponent;
import pages.components.ResultsTableComponent;
import pages.elementsPages.PracticeFormPage;
import testData.RegistrationFormData;

import static com.codeborne.selenide.Condition.text;

public class RegistrationFormTests extends BaseTest {

    private final PracticeFormPage form = new PracticeFormPage();
    private final HeaderComponent header = new HeaderComponent();
    private final ResultsTableComponent resultsTable = new ResultsTableComponent();
    private final BacklightComponent backlight = new BacklightComponent();
    private final RegistrationFormData testData = new RegistrationFormData();

    @DisplayName("Проверка заголовков страницы формы регистрации")
    @Test
    void checkHeaders() {
        form.openPage()
                .verifyUserFormVisible();
        header.verifyMainTitle("Practice Form")
                .verifySubtitle("Student Registration Form")
                .verifyNameLabel("Name")
                .verifyEmailLabel("Email")
                .verifyGenderLabel("Gender")
                .verifyMobileLabel("Mobile(10 Digits)")
                .verifyDateOfBirthLabel("Date of Birth")
                .verifySubjectsLabel("Subjects")
                .verifyHobbiesLabel("Hobbies")
                .verifyPictureLabel("Picture")
                .verifyCurrentAddressLabel("Current Address")
                .verifyStateAndCityLabel("State and City");
    }

    @DisplayName("Проверка подсветки полей при пустой форме и клике на кнопку submit")
    @Test
    void checkingRequiredFieldsHighlighting() {
        form.openPage()
                .clickSubmit();
        backlight.checkBacklight(form.getUserNumberInput(), "border-color", testData.redBorder)
                .checkBacklight(form.getFirstNameInput(), "border-color", testData.redBorder)
                .checkBacklight(form.getLastNameInput(), "border-color", testData.redBorder)
                .checkBacklight(form.getUserEmailInput(), "border-color", testData.redBorder)
                .checkBacklight(form.getDateOfBirthInput(), "border-color", testData.redBorder)
                .checkBacklight(form.getCurrentAddressTextArea(), "border-color", testData.redBorder);
        form.getCheckLabelByIndex(0).shouldHave(text("Male"));
        backlight.checkBacklight(form.getCheckLabelByIndex(0), "border-color", testData.redBorder);
        form.getCheckLabelByIndex(1).shouldHave(text("Female"));
        backlight.checkBacklight(form.getCheckLabelByIndex(1), "border-color", testData.redBorder);
        form.getCheckLabelByIndex(2).shouldHave(text("Other"));
        backlight.checkBacklight(form.getCheckLabelByIndex(2), "border-color", testData.redBorder);
        form.getCheckLabelByIndex(3).shouldHave(text("Sports"));
        backlight.checkBacklight(form.getCheckLabelByIndex(3), "border-color", testData.redBorder);
        form.getCheckLabelByIndex(4).shouldHave(text("Reading"));
        backlight.checkBacklight(form.getCheckLabelByIndex(4), "border-color", testData.redBorder);
        form.getCheckLabelByIndex(5).shouldHave(text("Music"));
        backlight.checkBacklight(form.getCheckLabelByIndex(5), "border-color", testData.redBorder);
    }

    @DisplayName("Заполнение всех полей формы")
    @Tag("REGRESS")
    @Test
    void fillAllField() {
        form.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .selectGender(testData.sex)
                .setUserNumber(testData.userNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setSubject(testData.subject)
                .setHobbies(testData.hobbies)
                .uploadPicture(testData.picture)
                .setCurrentAddress(testData.currentAddress)
                .setStateAndCity(testData.state, testData.city)
                .clickSubmit();
        resultsTable.checkModalVisible();
        header.verifyModalTitle(testData.title);
        resultsTable.checkTableResponsiveText("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableResponsiveText("Student Email", testData.userEmail)
                .checkTableResponsiveText("Gender", testData.sex)
                .checkTableResponsiveText("Mobile", testData.userNumber)
                .checkTableResponsiveText("Date of Birth", testData.dateOfBirthText)
                .checkTableResponsiveText("Subjects", testData.subject)
                .checkTableResponsiveText("Hobbies", testData.hobbies)
                .checkTableResponsiveText("Picture", testData.picture)
                .checkTableResponsiveText("Address", testData.currentAddress)
                .checkTableResponsiveText("State and City", testData.state + " " + testData.city);
    }

    @ParameterizedTest(name = "Регистрация с заполнением обязательных полей с полом {0}")
    @ValueSource(strings = {
            "Male", "Female", "Other"
    })
    @Tag("SMOKE")
    void onlyRequiredFieldsWithDifGender(String gender) {
        form.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .selectGender(gender)
                .setUserNumber(testData.userNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setHobbies("Reading")
                .setCurrentAddress(testData.currentAddress)
                .clickSubmit();
        resultsTable.checkModalVisible();
        header.verifyModalTitle(testData.title);
        resultsTable.checkTableResponsiveText("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableResponsiveText("Student Email", testData.userEmail)
                .checkTableResponsiveText("Gender", gender)
                .checkTableResponsiveText("Mobile", testData.userNumber)
                .checkTableResponsiveText("Date of Birth", testData.dateOfBirthText)
                .checkTableResponsiveText("Hobbies", "Reading")
                .checkTableResponsiveText("Address", testData.currentAddress)
                .checkTableResponsiveEmptyValue("Subjects")
                .checkTableResponsiveEmptyValue("Picture")
                .checkTableResponsiveEmptyValue("State and City");
    }

    @ParameterizedTest(name = "Регистрация с заполнением обязательных полей с разными именами")
    @CsvFileSource(resources = "/dataForParameterizedTest/onlyRequiredFieldsWithDifNames.csv")
    @Tag("REGRESS")
    void onlyRequiredFieldsWithDifNames(String firstname, String lastname) {
        form.openPage()
                .setFirstName(firstname)
                .setLastName(lastname)
                .setEmail(testData.userEmail)
                .selectGender(testData.sex)
                .setUserNumber(testData.userNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setHobbies("Reading")
                .setCurrentAddress(testData.currentAddress)
                .clickSubmit();
        resultsTable.checkModalVisible();
        header.verifyModalTitle(testData.title);
        resultsTable.checkTableResponsiveText("Student Name", firstname + " " + lastname)
                .checkTableResponsiveText("Student Email", testData.userEmail)
                .checkTableResponsiveText("Gender", testData.sex)
                .checkTableResponsiveText("Mobile", testData.userNumber)
                .checkTableResponsiveText("Date of Birth", testData.dateOfBirthText)
                .checkTableResponsiveText("Hobbies", "Reading")
                .checkTableResponsiveText("Address", testData.currentAddress)
                .checkTableResponsiveEmptyValue("Subjects")
                .checkTableResponsiveEmptyValue("Picture")
                .checkTableResponsiveEmptyValue("State and City");
    }

    @ParameterizedTest(name = "Регистрация с заполнением обязательных полей с hobbies {0} и gender {1}")
    @CsvSource(value = {
            "Sports, Male",
            "Reading, Female",
            "Music, Other"
    })
    @Tag("SMOKE")
    void onlyRequiredFieldsWithHobbies(String hobbies, String gender) {
        form.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .selectGender(gender)
                .setUserNumber(testData.userNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setHobbies(hobbies)
                .setCurrentAddress(testData.currentAddress)
                .clickSubmit();
        resultsTable.checkModalVisible();
        header.verifyModalTitle(testData.title);
        resultsTable.checkTableResponsiveText("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableResponsiveText("Student Email", testData.userEmail)
                .checkTableResponsiveText("Gender", gender)
                .checkTableResponsiveText("Mobile", testData.userNumber)
                .checkTableResponsiveText("Date of Birth", testData.dateOfBirthText)
                .checkTableResponsiveText("Hobbies", hobbies)
                .checkTableResponsiveText("Address", testData.currentAddress)
                .checkTableResponsiveEmptyValue("Subjects")
                .checkTableResponsiveEmptyValue("Picture")
                .checkTableResponsiveEmptyValue("State and City");
    }

    @ParameterizedTest(name ="Проверка регистрации с некорректным номером телефона")
    @ValueSource(strings = {
            "123", "абы", "123456789", "+-"
    })
    void wrongPhoneNumber( String number) {
        form.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .selectGender("Female")
                .setUserNumber(number)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setHobbies("Music")
                .setCurrentAddress(testData.currentAddress)
                .clickSubmit();
        backlight.checkBacklight(form.getUserNumberInput(), "border-color", testData.redBorder)
                .checkBacklight(form.getFirstNameInput(), "border-color", testData.greenBorder)
                .checkBacklight(form.getLastNameInput(), "border-color", testData.greenBorder)
                .checkBacklight(form.getUserEmailInput(), "border-color", testData.greenBorder)
                .checkBacklight(form.getDateOfBirthInput(), "border-color", testData.greenBorder)
                .checkBacklight(form.getCurrentAddressTextArea(), "border-color", testData.greenBorder);
        form.getCheckLabelByIndex(1).shouldHave(text("Female"));
        backlight.checkBacklight(form.getCheckLabelByIndex(1), "border-color", testData.greenBorder);
        form.getCheckLabelByIndex(5).shouldHave(text("Music"));
        backlight.checkBacklight(form.getCheckLabelByIndex(5), "border-color", testData.greenBorder);
    }

    @DisplayName(("Пропуск обязательного поля firstName при регистрации"))
    @Test
    void skipOneRequiredOnes() {
        form.openPage()
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .selectGender("Female")
                .setUserNumber(testData.userNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .setHobbies("Sports")
                .setCurrentAddress(testData.currentAddress)
                .clickSubmit();
        resultsTable.checkModalNotVisible();
        backlight.checkBacklight(form.getFirstNameInput(), "border-color", testData.redBorder)
                .checkBacklight(form.getUserNumberInput(), "border-color", testData.greenBorder)
                .checkBacklight(form.getLastNameInput(), "border-color", testData.greenBorder)
                .checkBacklight(form.getUserEmailInput(), "border-color", testData.greenBorder)
                .checkBacklight(form.getDateOfBirthInput(), "border-color", testData.greenBorder)
                .checkBacklight(form.getCurrentAddressTextArea(), "border-color", testData.greenBorder);
        form.getCheckLabelByIndex(1).shouldHave(text("Female"));
        backlight.checkBacklight(form.getCheckLabelByIndex(1), "border-color", testData.greenBorder);
        form.getCheckLabelByIndex(3).shouldHave(text("Sports"));
        backlight.checkBacklight(form.getCheckLabelByIndex(0), "border-color", testData.greenBorder);
    }
}