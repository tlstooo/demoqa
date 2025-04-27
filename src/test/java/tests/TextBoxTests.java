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
    String MyCurrentAddress = "Huragda st";
    String MyState = "Uttar Pradesh";
    String MyCity = "Lucknow";



    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

}
