package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import testData.TextBoxData;

@Story("Text box")
public class TextBoxTests extends BaseTest {
    private final TextBoxData testData = new TextBoxData();

    @Test
    void successfulFillFormTest() {
        mainPage.openElementsPage()
                .openTextBoxPage()
                .fillEmailForm(testData.userEmail)
                .fillNameForm(testData.userName)
                .fillcurrentAddressForm(testData.currentAddress)
                .fillpermanentAddressForm(testData.permanentAddress)
                .clickSubmit()
                .checkOutputNameText(testData.userName)
                .checkOutputEmailText(testData.userEmail)
                .checkOutputCurrentAddressText(testData.currentAddress)
                .checkOutputPermananetAddressText(testData.permanentAddress);
    }

    @Test
    void successfulFillFormWithoutAddressTest() {
        mainPage.openElementsPage()
                .openTextBoxPage()
                .fillEmailForm(testData.userEmail)
                .fillNameForm(testData.userName)
                .clickSubmit()
                .checkOutputNameText(testData.userName)
                .checkOutputEmailText(testData.userEmail);
    }
}