package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static testData.TestData.*;

public class DemoQaTests extends BaseTest {

    @Test
    void checkHeaders() {
        Selenide.open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();
        $("[id = userForm]").shouldBe(visible);
        $("h1.text-center").shouldHave(text("Practice Form"));
        $(".practice-form-wrapper h5").shouldHave(text("Student Registration Form"));
        $("[id = userName-label]").shouldHave(text("Name"));
        $("[id = userEmail-label]").shouldHave(text("Email"));
        $("[id = genterWrapper]").shouldHave(text("Gender"));
        $("[id = userNumber-label]").shouldHave(text("Mobile(10 Digits)"));
        $("[id = dateOfBirth-label]").shouldHave(text("Date of Birth"));
        $("[id=subjectsWrapper] [id = subjects-label]").shouldHave(text("Subjects"));
        $("[id=hobbiesWrapper] [id = subjects-label]").shouldHave(text("Hobbies"));
        $$(".form-label#subjects-label").get(2).shouldHave(Condition.exactText("Picture"));
        $("[id = currentAddress-label]").shouldHave(text("Current Address"));
        $("[id = stateCity-label]").shouldHave(text("State and City"));
    }

    @Test
    void fillAllField() {
        Selenide.open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();
        $("[id = firstName]").setValue(firstName);
        $("[id = lastName]").setValue(lastName);
        $("[id = userEmail]").setValue(userEmail);
        $(byText(sex)).click();
        $("[id = userNumber]").setValue(userNumber);
        $("[id = dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__month-select").selectOptionByValue(month);
        $(".react-datepicker__day.react-datepicker__day--015").click();
        $("[id = subjectsInput]").setValue("En");
        $(byText(subject)).click();
        $(byText(hobbies)).click();
        $("[id = uploadPicture]").uploadFromClasspath("photo/1.png");
        $("[id = currentAddress]").setValue(currentAddress);
        $("[id = state]").scrollTo().click();
        $(byText(state)).click();
        $("[id = city]").scrollTo().click();
        $(byText(city)).click();
        $("[id = submit].btn").click();
        // Проверки
        $("[id = example-modal-sizes-title-lg]").shouldBe(visible).shouldHave(text(title));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(sex));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(dateOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbies));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("1.png"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));
    }

    @Test
    void onlyRequiredFields() {
        Selenide.open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();
        $("[id = firstName]").setValue(firstName);
        $("[id = lastName]").setValue(lastName);
        $("[id = userEmail]").setValue(userEmail);
        $(byText(sex)).click();
        $("[id = userNumber]").setValue(userNumber);
        $("[id = dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__month-select").selectOptionByValue(month);
        $(".react-datepicker__day.react-datepicker__day--015").click();
        $(byText(hobbies)).click();
        $("[id = currentAddress]").setValue(currentAddress);
        $("[id = submit].btn").scrollTo().click();
        // Проверки
        $("[id = example-modal-sizes-title-lg]").shouldBe(visible).shouldHave(text(title));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(sex));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(dateOfBirth));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbies));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
    }

    @Test
    void wrongPhoneNumber() {
        Selenide.open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();
        $("[id = firstName]").setValue(firstName);
        $("[id = lastName]").setValue(lastName);
        $("[id = userEmail]").setValue(userEmail);
        $(byText(sex)).click();
        $("[id = userNumber]").setValue("123");
        $("[id = dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__month-select").selectOptionByValue(month);
        $(".react-datepicker__day.react-datepicker__day--015").click();
        $(byText(hobbies)).click();
        $("[id = currentAddress]").setValue(currentAddress);
        $("[id = submit].btn").scrollTo().click();
        //Проверки
        $("[id=userNumber]").scrollTo().shouldHave(cssValue("border-color", redBorder));
        $("[id=firstName]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
        $("[id=lastName]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
        $("[id=userEmail]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
        $("[id=dateOfBirthInput]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
        $("label[for=gender-radio-1]").shouldHave(cssValue("color", greenColor));
        $("label[for=gender-radio-2]").shouldHave(cssValue("color", greenColor));
        $("label[for=gender-radio-3]").shouldHave(cssValue("color", greenColor));
        $("label[for=hobbies-checkbox-1]").shouldHave(cssValue("color", greenColor));
        $("label[for=hobbies-checkbox-2]").shouldHave(cssValue("color", greenColor));
        $("label[for=hobbies-checkbox-3]").shouldHave(cssValue("color", greenColor));
        $("[id=currentAddress]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
    }

    @Test
    void skipOneRequiredOnes() {
        Selenide.open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();
        $("[id = firstName]").setValue(firstName);
        $("[id = lastName]").setValue(lastName);
        $("[id = userEmail]").setValue(userEmail);
        $(byText(sex)).click();
        $("[id = userNumber]").setValue(userNumber);
        $("[id = dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__month-select").selectOptionByValue(month);
        $(".react-datepicker__day.react-datepicker__day--015").click();
        $(byText(hobbies)).click();
        // $("[id = currentAddress]").setValue("123"); не заполняем обязательное поле
        $("[id = submit].btn").scrollTo().click();
        $("[id = example-modal-sizes-title-lg]").shouldNotBe(visible);
        //Проверки
        $("[id=currentAddress]").scrollTo().shouldHave(cssValue("border-color", redBorder));
        $("[id=firstName]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
        $("[id=lastName]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
        $("[id=userEmail]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
        $("[id=dateOfBirthInput]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
        $("label[for=gender-radio-1]").shouldHave(cssValue("color", greenColor));
        $("label[for=gender-radio-2]").shouldHave(cssValue("color", greenColor));
        $("label[for=gender-radio-3]").shouldHave(cssValue("color", greenColor));
        $("label[for=hobbies-checkbox-1]").shouldHave(cssValue("color", greenColor));
        $("label[for=hobbies-checkbox-2]").shouldHave(cssValue("color", greenColor));
        $("label[for=hobbies-checkbox-3]").shouldHave(cssValue("color", greenColor));
        $("[id=userNumber]").scrollTo().shouldHave(cssValue("border-color", greenBorder));
    }

    @Test
    void checkingRequiredFieldsHighlighting() {
        Selenide.open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();
        $("[id = submit].btn").scrollTo().click();
        $("[id=firstName]").scrollTo().shouldHave(cssValue("border-color", redBorder));
        $("[id=lastName]").scrollTo().shouldHave(cssValue("border-color", redBorder));
        $("[id=userEmail]").scrollTo().shouldHave(cssValue("border-color", redBorder));
        $("label[for=gender-radio-1]").shouldHave(cssValue("color", redColor));
        $("label[for=gender-radio-2]").shouldHave(cssValue("color", redColor));
        $("label[for=gender-radio-3]").shouldHave(cssValue("color", redColor));
        $("[id=userNumber]").scrollTo().shouldHave(cssValue("border-color", redBorder));
        $("[id=dateOfBirthInput]").scrollTo().shouldHave(cssValue("border-color", redBorder));
        $("label[for=hobbies-checkbox-1]").shouldHave(cssValue("color", redColor));
        $("label[for=hobbies-checkbox-2]").shouldHave(cssValue("color", redColor));
        $("label[for=hobbies-checkbox-3]").shouldHave(cssValue("color", redColor));
        $("[id=currentAddress]").shouldHave(cssValue("border-color", redBorder));
    }
}