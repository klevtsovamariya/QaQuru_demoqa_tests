package tests;

import org.junit.jupiter.api.Test;

import static testData.TextBoxData.*;

public class TextBoxTests extends BaseTest {

    @Test
    void successfulFillFormTest() {
        mainPage.openElementsPage()
                .openTextBoxPage()
                .fillEmailForm(userEmail)
                .fillNameForm(userName)
                .fillcurrentAddressForm(currentAddress)
                .fillpermanentAddressForm(permanentAddress)
                .clickSubmit()
                .checkOutputNameText(userName)
                .checkOutputEmailText(userEmail)
                .checkOutputCurrentAddressText(currentAddress)
                .checkOutputPermananetAddressText(permanentAddress);
    }

    @Test
    void successfulFillFormWithoutAddressTest() {
        mainPage.openElementsPage()
                .openTextBoxPage()
                .fillEmailForm(userEmail)
                .fillNameForm(userName)
                .clickSubmit()
                .checkOutputNameText(userName)
                .checkOutputEmailText(userEmail);
    }
}