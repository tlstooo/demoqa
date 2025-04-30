package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class AutomationPracticeFormTest {

    RegistrationPage registrationPage = new RegistrationPage();

    //====POSITIVE VARIABLES====//
    String myName = "Sergey",
            myLastName = "Frolov",
            myEmail = "SFrolov@test.ru",
            myGender = "Male",
            myPhoneNumber = "9999999999",
            myBirthdayDay = "04",
            myBirthdayMonth = "11",
            myBirthdayYear = "1994",
            mySubject = "Computer Science",
            myPic = "pic.png",
            myCurrentAddress = "Huragda st",
            myState = "Uttar Pradesh",
            myCity = "Lucknow";

    String[] Month = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},
            myHobbies = new String[] {"Reading", "Music"};

    //====NEGATIVE VARIABLES====//
    String elevenDigitsPhoneNumber = "79999999999";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void successfulRegistrationTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName(myName)
                .setLastName(myLastName)
                .setEmail(myEmail)
                .setGender(myGender)
                .setPhoneNumber(myPhoneNumber)
                .setDateOfBirth(myBirthdayDay, myBirthdayMonth, myBirthdayYear)
                .setSubjects(mySubject)
                .setHobbies(myHobbies)
                .setUploadPic(myPic)
                .setCurrentAddress(myCurrentAddress)
                .setState(myState)
                .setCity(myCity)
                .clickSubmitButton();

        registrationPage
                .checkResult("Student Name", myName + " " + myLastName)
                .checkResult("Student Email", myEmail)
                .checkResult("Gender", myGender)
                .checkResult("Mobile", myPhoneNumber)
                .checkResult("Date of Birth", myBirthdayDay + " " + Month[Integer.parseInt(myBirthdayMonth) - 1] + "," + myBirthdayYear)
                .checkResult("Subjects", mySubject)
                .checkResult("Hobbies", myHobbies[0] + ", " + myHobbies[1])
                .checkResult("Picture", myPic)
                .checkResult("Address", myCurrentAddress)
                .checkResult("State and City", myState + " " + myCity);
    }
    //====ПРОВЕРКА ВВОДА НОМЕРА НЕДОПУСТИМОЙ ДЛИНЫ====//
    @Test
    public void negativeInputRegistrationTest() {
        registrationPage
                .openPage()
                .removeBanners()
                .setPhoneNumber(elevenDigitsPhoneNumber)
                .clickSubmitButton();


        registrationPage
                .checkNegativeInputResult("Phone Number", elevenDigitsPhoneNumber);
    }


    @Test
    public void obligatoryFieldsTest()
    {
        registrationPage
                .openPage()
                .removeBanners()
                .setFirstName(myName)
                .setLastName(myLastName)
                .setGender(myGender)
                .setPhoneNumber(myPhoneNumber)
                .clickSubmitButton();

        registrationPage
                .checkRadioColor("Gender","green")
                .checkBorderColor("First Name","green")
                .checkBorderColor("Last Name","green")
                .checkResult("Student Name", myName + " " + myLastName)
                .checkResult("Gender", myGender)
                .checkResult("Mobile", myPhoneNumber);
    }
}
