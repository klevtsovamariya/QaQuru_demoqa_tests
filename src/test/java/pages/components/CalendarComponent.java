package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public final SelenideElement monthSelect = $(".react-datepicker__month-select");
    public final SelenideElement yearSelect = $(".react-datepicker__year-select");

    @Step("Установить дату в календаре: {day}.{month}.{year}")
    public void setDate(String day, String month, String year) {
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click();
    }
}