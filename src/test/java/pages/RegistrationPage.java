package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    //=======VARIABLES==========//
    private final String registrationPageURL = "/automation-practice-form";

    //======TEST LOCATORS=======//
    private final SelenideElement firstNameLocator = $("#firstName"),
    lastNameLocator = $("#lastName"),
    emailLocator = $("#userEmail"),
    genderLocator = $("#genterWrapper"),
    phoneNumberLocator = $("#userNumber"),
    calendarInputLocator = $("#dateOfBirthInput"),
    subjectsInputLocator = $("#subjectsInput"),
    hobbiesInputLocator = $("#hobbiesWrapper"),
    uploadPicLocator = $("#uploadPicture"),
    currentAddressLocator = $("#currentAddress"),
    stateLocator = $("#state"),
    stateInputLocator = $("#stateCity-wrapper"),
    cityLocator = $("#city"),
    cityInputLocator =  $("#stateCity-wrapper"),
    submitButtonLocator = $("#submit");

    //======RESULT LOCATORS======//
    private final SelenideElement tableCellLocator = $(".table-responsive");


    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open(registrationPageURL);
        $(".practice-form-wrapper").shouldHave(text(("Student Registration")));

        return this;
    }

    public RegistrationPage removeBanners(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameLocator.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameLocator.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailLocator.setValue(email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderLocator.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setPhoneNumber(String phoneNumber) {
        phoneNumberLocator.setValue(phoneNumber);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInputLocator.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectsInputLocator.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            hobbiesInputLocator.$(byText(hobby)).click();
        }
        return this;
    }

    public RegistrationPage setUploadPic(String uploadPic) {
        uploadPicLocator.uploadFromClasspath(uploadPic);
        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress) {
        currentAddressLocator.setValue(currentAddress);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateLocator.click();
        stateInputLocator.$(byText(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        cityLocator.click();
        cityInputLocator.$(byText(city)).click();
        return this;
    }

    public RegistrationPage clickSubmitButton() {
        submitButtonLocator.click();
        return this;
    }

    public RegistrationPage checkResult (String key, String value) {
        tableCellLocator.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

}