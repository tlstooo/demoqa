package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class automationPracticeFormTest {

    RegistrationPage registrationPage = new RegistrationPage();

    //====POSITIVE VARIABLES====//
    String MyName = "Sergey",
            MyLastName = "Frolov",
            MyEmail = "SFrolov@test.ru",
     MyGender = "Male",
     MyPhoneNumber = "9999999999",
     MyBirthdayDay = "04",
     MyBirthdayMonth = "11",
     MyBirthdayYear = "1994",
     MySubject = "Computer Science",
     MyPic = "pic.png",
     MyCurrentAddress = "Huragda st",
     MyState = "Uttar Pradesh",
     MyCity = "Lucknow";

    String[] Month = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},
            MyHobbies = new String[] {"Reading", "Music"};

    //====NEGATIVE VARIABLES====//
    String ElevenDigitsPhoneNumber = "79999999999",
         UnknownCity = "Dzherpadesh";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void successfulRegistrationTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(MyName)
                .setLastName(MyLastName)
                .setEmail(MyEmail)
                .setGender(MyGender)
                .setPhoneNumber(MyPhoneNumber)
                .setDateOfBirth(MyBirthdayDay, MyBirthdayMonth, MyBirthdayYear)
                .setSubjects(MySubject)
                .setHobbies(MyHobbies)
                .setUploadPic(MyPic)
                .setCurrentAddress(MyCurrentAddress)
                .setState(MyState)
                .setCity(MyCity)
                .clickSubmitButton();

        registrationPage
                .checkResult("Student Name", MyName + " " + MyLastName)
                .checkResult("Student Email", MyEmail)
                .checkResult("Gender", MyGender)
                .checkResult("Mobile", MyPhoneNumber)
                .checkResult("Date of Birth", MyBirthdayDay + " " + Month[Integer.parseInt(MyBirthdayMonth) - 1] + "," + MyBirthdayYear)
                .checkResult("Subjects", MySubject)
                .checkResult("Hobbies", MyHobbies[0] + ", " + MyHobbies[1])
                .checkResult("Picture", MyPic)
                .checkResult("Address", MyCurrentAddress)
                .checkResult("State and City", MyState + " " + MyCity);
    }
}
