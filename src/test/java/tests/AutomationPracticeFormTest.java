package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import utils.RandomizeData;
import java.util.Map;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

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
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    @DisplayName("Проверка всех полей на странице регистрации")
    @Feature("Валидация полей регистрации")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "DemoQA", url = "https://demoqa.com")
    public void successfulRegistrationTest() {
        step("Открываем тестируемую страницу", () -> { registrationPage.openPage(); });
        step("Удаляем рекламные баннеры", () -> { registrationPage.removeBanners(); });
        step("Заполняем имя", () -> { registrationPage.setFirstName(randomizeData.firstName); });
        step("Заполняем фамилию", () -> { registrationPage.setLastName(randomizeData.lastName); });
        step("Заполняем email", () -> { registrationPage.setEmail(randomizeData.email); });
        step("Заполняем пол", () -> { registrationPage.setGender(randomizeData.gender); });
        step("Заполняем телефонный номер", () -> { registrationPage.setPhoneNumber(randomizeData.phone); });
        step("Заполняем дату рождения", () -> { registrationPage.setDateOfBirth(randomizeData.day, randomizeData.month, randomizeData.year); });
        step("Заполняем область", () -> { registrationPage.setSubjects(randomizeData.subject); });
        step("Заполняем хобби", () -> { registrationPage.setHobbies(randomizeData.hobbies); });
        step("Загружаем изображение", () -> { registrationPage.setUploadPic(randomizeData.pics); });
        step("Заполняем актуальный адрес", () -> { registrationPage.setCurrentAddress(randomizeData.currentAddress); });
        step("Заполняем штат", () -> { registrationPage.setState(randomizeData.state); });
        step("Заполняем город", () -> { registrationPage.setCity(randomizeData.city); });
        step("Нажимаем кнопку", () -> { registrationPage.clickSubmitButton(); });

        step("Проверяем имя и фамилию", () -> {registrationPage.checkResult("Student Name", randomizeData.firstName + " " + randomizeData.lastName);});
        step("Проверяем email", () -> {registrationPage.checkResult("Student Email", randomizeData.email);});
        step("Проверяем пол", () -> {registrationPage.checkResult("Gender", randomizeData.gender);});
        step("Проверяем номер телефона", () -> {registrationPage.checkResult("Phone Number", randomizeData.phone);});
        step("Проверяем дату рождения", () -> {registrationPage.checkResult("Date of Birth", randomizeData.day + " " + months[Integer.parseInt(randomizeData.month)-1] + "," + randomizeData.year);});
        step("Проверяем область", () -> {registrationPage.checkResult("Subjects", randomizeData.subject);});
        step("Проверяем хобби", () -> {registrationPage.checkResult("Hobbies", randomizeData.hobbies);});
        step("Проверяем изображение", () -> {registrationPage.checkResult("Picture", randomizeData.pics);});
        step("Проверяем адрес", () -> {registrationPage.checkResult("Address", randomizeData.currentAddress);});
        step("Проверяем штат и город", () -> {registrationPage .checkResult("State and City", randomizeData.state + " " + randomizeData.city);});
    }


    @Test
    @DisplayName("Проверка номера недопустимой длины")
    @Feature("Валидация полей регистрации")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "DemoQA", url = "https://demoqa.com")
    public void negativeInputRegistrationTest() {
        step("Открываем тестируемую страницу", () -> { registrationPage.openPage();});
        step("Удаляем рекламные баннеры", () -> { registrationPage.removeBanners(); });
        step("Заполняем телефонный номер", () -> { registrationPage.setPhoneNumber(randomizeData.elevenDigitsPhoneNumber); });
        step("Нажимаем кнопку", () -> { registrationPage.clickSubmitButton(); });
        step("Проверяем телефонный номер", () -> { registrationPage
                .checkNegativeInputResult("Phone Number", randomizeData.elevenDigitsPhoneNumber);});

    }

    @Test
    @DisplayName("Проверка заполнения только обязательных полей")
    @Feature("Валидация полей регистрации")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "DemoQA", url = "https://demoqa.com")
    public void obligatoryFieldsTest()
    {
        step("Открываем тестируемую страницу", () -> { registrationPage.openPage(); });
        step("Удаляем рекламные баннеры", () -> { registrationPage.removeBanners(); });
        step("Заполняем имя", () -> { registrationPage.setFirstName(randomizeData.firstName); });
        step("Заполняем фамилию", () -> { registrationPage.setLastName(randomizeData.lastName); });
        step("Заполняем пол", () -> { registrationPage.setGender(randomizeData.gender); });
        step("Заполняем телефонный номер", () -> { registrationPage.setPhoneNumber(randomizeData.phone); });
        step("Нажимаем кнопку", () -> { registrationPage.clickSubmitButton(); });

        step("Проверяем выбраный пол на радиокнопке", () -> {registrationPage.checkRadioColor("Gender","green");});
        step("Проверяем валидацию имени", () -> {registrationPage.checkBorderColor("First Name","green");});
        step("Проверяем валидацию фамилии", () -> {registrationPage.checkBorderColor("Last Name","green");});
        step("Проверяем имя и фамилию", () -> {registrationPage.checkResult("Student Name", randomizeData.firstName + " " + randomizeData.lastName);});
        step("Проверяем пол", () -> {registrationPage.checkResult("Gender", randomizeData.gender);});
        step("Проверяем телефонный номер", () -> {registrationPage.checkResult("Mobile", randomizeData.phone);});
    }
}
