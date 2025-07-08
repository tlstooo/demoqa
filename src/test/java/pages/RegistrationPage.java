package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    //=====HELPER VARIABLES=====//
    public final String registrationPageURL = "/automation-practice-form";
    private final String
            cssBorderColor = "border-color",
            cssRadioColor = "color",
            redColor = "rgb(220, 53, 69)",
            greyColor = "rgb(206, 212, 218)",
            greenColor = "rgb(40, 167, 69)",
            redColorAlpha = "rgba(220, 53, 69, 1)",
            greyColorAlpha = "rgba(220, 53, 69, 1)",
            greenColorAlpha = "rgba(40, 167, 69, 1)";

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

    public RegistrationPage setHobbies(String hobbies) {
            hobbiesInputLocator.$(byText(hobbies)).click();
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

    public RegistrationPage checkNegativeInputResult (String key, String value) {
        if (key == "Phone Number") {
            phoneNumberLocator.shouldNotHave(text(value));
        }
        return this;
    }

    public RegistrationPage checkBorderColor (String key, String value) {
        switch (value) {
            case "red":
                value = redColor;
                break;
            case "grey":
                value = greyColor;
                break;
            case "green":
                value = greenColor;
                break;
        }
        switch (key) {
            case "First Name":
                firstNameLocator.shouldHave(cssValue(cssBorderColor,value));
                break;
            case "Last Name":
                lastNameLocator.shouldHave(cssValue(cssBorderColor,value));
                break;
            case "Phone Number":
                phoneNumberLocator.shouldHave(cssValue(cssBorderColor,value));
                break;
        }
        return this;
    }

    public RegistrationPage checkRadioColor (String key, String value) {
        switch (value) {
            case "red":
                value = redColorAlpha;
                break;
            case "grey":
                value = greyColorAlpha;
                break;
            case "green":
                value = greenColorAlpha;
                break;
        }
        switch (key) {
            case "Gender":
                genderLocator.$(byText("Male")).shouldHave(cssValue(cssRadioColor,value));
                break;
        }
        return this;
    }
}