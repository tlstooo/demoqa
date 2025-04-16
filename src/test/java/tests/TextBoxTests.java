package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

import java.lang.reflect.Array;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class TextBoxTests {

    String MyName = "Sergey";
    String MyLastName = "Frolov";
    String MyEmail = "MyEmail@email.com";
    String MyNumber = "9999999999";
    String MyBirthdayMonth = "11";
    String MyBirthdayYear = "1994";
    String MyBirthdayDay = "4";
    String MyGender = "Male";
    String[] Month = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String MySubject = "Computer Science";
    String[] MyHobbies = new String[] {"Reading", "Music"};
    String MyPic = "pic.png";
    String MyCurrentAddress = "Nizhniy Novgorod";


    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;

    }

    @Test
    public void fillFormTest() {

        open("/automation-practice-form");
        $("#firstName").setValue(MyName);
        $("#lastName").setValue(MyLastName);
        $("#userEmail").setValue(MyEmail);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(MyNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue(String.valueOf(Integer.parseInt(MyBirthdayMonth)-1));
        $(".react-datepicker__year-select").selectOptionByValue(MyBirthdayYear);
        $$(".react-datepicker__day").findBy(text(MyBirthdayDay)).click();
        $("#subjectsInput").setValue(MySubject).pressEnter();
        $$(".custom-control-label").findBy(text(MyHobbies[0])).click();
        $$(".custom-control-label").findBy(text(MyHobbies[1])).click();
        $("#uploadPicture" +
                "").uploadFromClasspath(MyPic);
        $("#currentAddress").setValue(MyCurrentAddress);
        $("#state").scrollIntoView(true).click();
        $("#react-select-3-option-1").click();
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();



        $$("table tr").findBy(text("Student Name")).shouldHave(text(MyName + " " + MyLastName));
        $$("table tr").findBy(text("Student Email")).shouldHave(text(MyEmail));
        $$("table tr").findBy(text("Gender")).shouldHave(text(MyGender));
        $$("table tr").findBy(text("Mobile")).shouldHave(text(MyNumber));
        $$("table tr").findBy(text("Date of Birth")).shouldHave(text(MyBirthdayDay + " " + Month[Integer.parseInt(MyBirthdayMonth)-1]+ "," + MyBirthdayYear));
        $$("table tr").findBy(text("Subjects")).shouldHave(text(MySubject));
        $$("table tr").findBy(text("Hobbies")).shouldHave(text(MyHobbies[0]+", "+MyHobbies[1]));
        $$("table tr").findBy(text("Picture")).shouldHave(text(MyPic));
        $$("table tr").findBy(text("Address")).shouldHave(text(MyCurrentAddress));
        $$("table tr").findBy(text("State and City")).shouldHave(text("Uttar Pradesh Agra"));
    }
}
