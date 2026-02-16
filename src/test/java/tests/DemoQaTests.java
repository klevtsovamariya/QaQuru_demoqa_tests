package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DemoQaTests extends BaseTest {

    @Test
    void checkHeaders() {
        Selenide.open("/automation-practice-form");
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
        Selenide.open("/automation-practice-form");
        $("[id = firstName]").setValue("Джек");
        $("[id = lastName]").setValue("Воробей");
        $("[id = userEmail]").setValue("capitanJack@gmail.com");
        $(byText("Female")).click();
        $("[id = userNumber]").setValue("89005623212");
        $("[id = dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $(".react-datepicker__month-select").selectOptionByValue("5");
        $(".react-datepicker__day.react-datepicker__day--015").click();
        $("[id = subjectsInput]").setValue("En");
        $(byText("English")).click();
        $(byText("Sports")).click();
        $("[id = uploadPicture]").uploadFromClasspath("photo/1.png");
        $("[id = currentAddress]").setValue("123");
        $("[id = state]").scrollTo().click();
        $(byText("NCR")).click();
        $("[id = city]").scrollTo().click();
        $(byText("Delhi")).click();
        $("[id = submit].btn").click();

        // Проверки
        $("[id = example-modal-sizes-title-lg]")
                .shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Джек Воробей"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("capitanJack@gmail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("89005623212"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("15 May 2000"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("English"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Sports"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("1.png"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("123"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));
    }

    @Test
    void onlyRequiredFields() {
        Selenide.open("/automation-practice-form");
        $("[id = firstName]").setValue("Джек");
        $("[id = lastName]").setValue("Воробей");
        $("[id = userEmail]").setValue("capitanJack@gmail.com");
        $(byText("Female")).click();
        $("[id = userNumber]").setValue("89005623212");
        $("[id = dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $(".react-datepicker__month-select").selectOptionByValue("5");
        $(".react-datepicker__day.react-datepicker__day--015").click();
        $(byText("Sports")).click();
        $("[id = currentAddress]").setValue("123");
        $("[id = submit].btn").scrollTo().click();
        $("[id = example-modal-sizes-title-lg]")
                .shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));
    }

    @Test
    void wrongPhoneNumber() {
        Selenide.open("/automation-practice-form");
        $("[id = firstName]").setValue("Джек");
        $("[id = lastName]").setValue("Воробей");
        $("[id = userEmail]").setValue("capitanJack@gmail.com");
        $(byText("Female")).click();
        $("[id = userNumber]").setValue("123");
        $("[id = dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $(".react-datepicker__month-select").selectOptionByValue("5");
        $(".react-datepicker__day.react-datepicker__day--015").click();
        $(byText("Sports")).click();
        $("[id = currentAddress]").setValue("123");
        $("[id = submit].btn").scrollTo().click();
        $("[id=userNumber]").scrollTo().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void skipOneRequiredOnes() {
        Selenide.open("/automation-practice-form");
        $("[id = firstName]").setValue("Джек");
        $("[id = lastName]").setValue("Воробей");
        $("[id = userEmail]").setValue("capitanJack@gmail.com");
        $(byText("Female")).click();
        $("[id = userNumber]").setValue("89005623212");
        $("[id = dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $(".react-datepicker__month-select").selectOptionByValue("5");
        $(".react-datepicker__day.react-datepicker__day--015").click();
        $(byText("Sports")).click();
        // $("[id = currentAddress]").setValue("123"); не заполняем обязательное поле
        $("[id = submit].btn").scrollTo().click();
        $("[id = example-modal-sizes-title-lg]").shouldNotBe(visible);
        $("[id=currentAddress]").scrollTo().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

    }

    @Test
    void checkingRequiredFieldsHighlighting() {
        Selenide.open("/automation-practice-form");
        $("[id = submit].btn").scrollTo().click();
        $("[id=firstName]").scrollTo().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=lastName]").scrollTo().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=userEmail]").scrollTo().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("label[for=gender-radio-1]").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("label[for=gender-radio-2]").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("label[for=gender-radio-3]").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("[id=userNumber]").scrollTo().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=dateOfBirthInput]").scrollTo().shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("label[for=hobbies-checkbox-1]").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("label[for=hobbies-checkbox-2]").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("label[for=hobbies-checkbox-3]").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("[id=currentAddress]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}