package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomizeData;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

@Tag("automation-practice-form")
public class AutomationPracticeFormTest {

    RegistrationPage registrationPage = new RegistrationPage();
    RandomizeData randomizeData = new RandomizeData();

    public String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @DisplayName("Проверка всех полей на странице регистрации")
    @Test
    public void successfulRegistrationTest() {
        open("/automation-practice-form");
        registrationPage
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

    @DisplayName("Проверка номера недопустимой длины")
    @Test
    public void negativeInputRegistrationTest() {
        registrationPage
                .removeBanners()
                .setPhoneNumber(randomizeData.elevenDigitsPhoneNumber)
                .clickSubmitButton();


        registrationPage
                .checkNegativeInputResult("Phone Number", randomizeData.elevenDigitsPhoneNumber);
    }

    @DisplayName("Проверка заполнения только обязательных полей")
    @Test
    public void obligatoryFieldsTest()
    {
        registrationPage
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
