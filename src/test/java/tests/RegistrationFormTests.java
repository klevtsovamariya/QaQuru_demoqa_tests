package tests;

import org.junit.jupiter.api.Test;
import pages.components.HeaderComponent;
import pages.elementsPages.PracticeFormPage;

import static com.codeborne.selenide.Condition.text;
import static testData.RegistrationFormData.*;

public class RegistrationFormTests extends BaseTest {

    private final PracticeFormPage form = new PracticeFormPage();
    private final HeaderComponent header = new HeaderComponent();

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

    @Test
    void fillAllField() {
        form.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .selectGender(sex2)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobbies(hobbies1)
                .uploadPicture(picture)
                .setCurrentAddress(currentAddress)
                .setStateAndCity(state, city)
                .clickSubmit()
                .checkModal(title)
                .checkTableResponsiveText("Student Name", firstName + " " + lastName)
                .checkTableResponsiveText("Student Email", userEmail)
                .checkTableResponsiveText("Gender", sex2)
                .checkTableResponsiveText("Mobile", userNumber)
                .checkTableResponsiveText("Date of Birth", dateOfBirth)
                .checkTableResponsiveText("Subjects", subject)
                .checkTableResponsiveText("Hobbies", hobbies1)
                .checkTableResponsiveText("Picture", picture)
                .checkTableResponsiveText("Address", currentAddress)
                .checkTableResponsiveText("State and City", state + " " + city);
    }

    @Test
    void onlyRequiredFields() {
        form.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .selectGender(sex1)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .setHobbies(hobbies2)
                .setCurrentAddress(currentAddress)
                .clickSubmit()
                .checkModal(title)
                .checkTableResponsiveText("Student Name", firstName + " " + lastName)
                .checkTableResponsiveText("Student Email", userEmail)
                .checkTableResponsiveText("Gender", sex1)
                .checkTableResponsiveText("Mobile", userNumber)
                .checkTableResponsiveText("Date of Birth", dateOfBirth)
                .checkTableResponsiveText("Hobbies", hobbies2)
                .checkTableResponsiveText("Address", currentAddress)
                .checkTableResponsiveEmptyValue("Subjects")
                .checkTableResponsiveEmptyValue("Picture")
                .checkTableResponsiveEmptyValue("State and City");
    }

    @Test
    void wrongPhoneNumber() {
        form.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .selectGender(sex2)
                .setUserNumber("123")
                .setDateOfBirth(day, month, year)
                .setHobbies(hobbies3)
                .setCurrentAddress(currentAddress)
                .clickSubmit()
                .checkBacklight(form.getUserNumberInput(), "border-color", redBorder)
                .checkBacklight(form.getFirstNameInput(), "border-color", greenBorder)
                .checkBacklight(form.getLastNameInput(), "border-color", greenBorder)
                .checkBacklight(form.getUserEmailInput(), "border-color", greenBorder)
                .checkBacklight(form.getDateOfBirthInput(), "border-color", greenBorder)
                .checkBacklight(form.getCurrentAddressTextArea(), "border-color", greenBorder)
                .getCheckLabelByIndex(1).shouldHave(text(sex2));
        form.checkBacklight(form.getCheckLabelByIndex(1), "border-color", greenBorder)
                .getCheckLabelByIndex(5).shouldHave(text(hobbies3));
        form.checkBacklight(form.getCheckLabelByIndex(5), "border-color", greenBorder);
    }

    @Test
    void skipOneRequiredOnes() {
        form.openPage()
                .setLastName(lastName)
                .setEmail(userEmail)
                .selectGender(sex2)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .setHobbies(hobbies1)
                .setCurrentAddress(currentAddress)
                .clickSubmit()
                .checkModalNotVisible()
                .checkBacklight(form.getFirstNameInput(), "border-color", redBorder)
                .checkBacklight(form.getUserNumberInput(), "border-color", greenBorder)
                .checkBacklight(form.getLastNameInput(), "border-color", greenBorder)
                .checkBacklight(form.getUserEmailInput(), "border-color", greenBorder)
                .checkBacklight(form.getDateOfBirthInput(), "border-color", greenBorder)
                .checkBacklight(form.getCurrentAddressTextArea(), "border-color", greenBorder)
                .getCheckLabelByIndex(1).shouldHave(text(sex2));
        form.checkBacklight(form.getCheckLabelByIndex(1), "border-color", greenBorder)
                .getCheckLabelByIndex(3).shouldHave(text(hobbies1));
        form.checkBacklight(form.getCheckLabelByIndex(0), "border-color", greenBorder);
    }

    @Test
    void checkingRequiredFieldsHighlighting() {
        form.openPage()
                .clickSubmit()
                .checkBacklight(form.getUserNumberInput(), "border-color", redBorder)
                .checkBacklight(form.getFirstNameInput(), "border-color", redBorder)
                .checkBacklight(form.getLastNameInput(), "border-color", redBorder)
                .checkBacklight(form.getUserEmailInput(), "border-color", redBorder)
                .checkBacklight(form.getDateOfBirthInput(), "border-color", redBorder)
                .checkBacklight(form.getCurrentAddressTextArea(), "border-color", redBorder)
                .getCheckLabelByIndex(0).shouldHave(text(sex1));
        form.checkBacklight(form.getCheckLabelByIndex(0), "border-color", redBorder)
                .getCheckLabelByIndex(1).shouldHave(text(sex2));
        form.checkBacklight(form.getCheckLabelByIndex(1), "border-color", redBorder)
                .getCheckLabelByIndex(2).shouldHave(text(sex3));
        form.checkBacklight(form.getCheckLabelByIndex(2), "border-color", redBorder)
                .getCheckLabelByIndex(3).shouldHave(text(hobbies1));
        form.checkBacklight(form.getCheckLabelByIndex(3), "border-color", redBorder)
                .getCheckLabelByIndex(4).shouldHave(text(hobbies2));
        form.checkBacklight(form.getCheckLabelByIndex(4), "border-color", redBorder)
                .getCheckLabelByIndex(5).shouldHave(text(hobbies3));
        form.checkBacklight(form.getCheckLabelByIndex(5), "border-color", redBorder);
    }
}