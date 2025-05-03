package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomizeData;

import java.util.Calendar;
import java.util.Locale;

public class AutomationPracticeFormTest {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker(new Locale("en"));
    RandomizeData randomizeData = new RandomizeData();

    public String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    //====NEGATIVE VARIABLES====//

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
                .setFirstName(randomizeData.firstName)
                .setLastName(randomizeData.lastName)
                .setEmail(randomizeData.email)
                .setGender(randomizeData.gender)
                .setPhoneNumber(randomizeData.phone)
                .setDateOfBirth(randomizeData.day, randomizeData.month, randomizeData.year)
                .setSubjects(randomizeData.subject)
                .setHobbies(randomizeData.hobbies)
                .setUploadPic(randomizeData.pics)
                .setCurrentAddress(randomizeData.currentAddress)
                .setState(randomizeData.state)
                .setCity(randomizeData.city)
                .clickSubmitButton();

        registrationPage
                .checkResult("Student Name", randomizeData.firstName + " " + randomizeData.lastName)
                .checkResult("Student Email", randomizeData.email)
                .checkResult("Gender", randomizeData.gender)
                .checkResult("Mobile", randomizeData.phone)
                .checkResult("Date of Birth", randomizeData.day + " " + months[Integer.parseInt(randomizeData.month)-1] + "," + randomizeData.year)
                .checkResult("Subjects", randomizeData.subject)
                .checkResult("Hobbies", randomizeData.hobbies)
                .checkResult("Picture", randomizeData.pics)
                .checkResult("Address", randomizeData.currentAddress)
                .checkResult("State and City", randomizeData.state + " " + randomizeData.city);
    }
    //====ПРОВЕРКА ВВОДА НОМЕРА НЕДОПУСТИМОЙ ДЛИНЫ====//
    @Test
    public void negativeInputRegistrationTest() {
        registrationPage
                .openPage()
                .removeBanners()
                .setPhoneNumber(randomizeData.elevenDigitsPhoneNumber)
                .clickSubmitButton();


        registrationPage
                .checkNegativeInputResult("Phone Number", randomizeData.elevenDigitsPhoneNumber);
    }


    @Test
    public void obligatoryFieldsTest()
    {
        registrationPage
                .openPage()
                .removeBanners()
                .setFirstName(randomizeData.firstName)
                .setLastName(randomizeData.lastName)
                .setGender(randomizeData.gender)
                .setPhoneNumber(randomizeData.phone)
                .clickSubmitButton();

        registrationPage
                .checkRadioColor("Gender","green")
                .checkBorderColor("First Name","green")
                .checkBorderColor("Last Name","green")
                .checkResult("Student Name", randomizeData.firstName + " " + randomizeData.lastName)
                .checkResult("Gender", randomizeData.gender)
                .checkResult("Mobile", randomizeData.phone);
    }
}
