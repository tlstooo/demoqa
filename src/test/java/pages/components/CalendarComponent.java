package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent
{

    private final SelenideElement
            monthCalendarLocator = $(".react-datepicker__month-select"),
            yearCalendarLocator = $(".react-datepicker__year-select");

    public void setDate(String day, String month, String year)
    {
        monthCalendarLocator.selectOptionByValue(String.valueOf(Integer.parseInt(month)-1));
        yearCalendarLocator.selectOptionByValue(year);
        $(".react-datepicker__day--0"+day+":not(.react-datepicker__day--outside-month)").click();
    }
}
