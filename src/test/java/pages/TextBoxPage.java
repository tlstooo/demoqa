package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
    //=====HELPER VARIABLES=====//
    private final String
            textboxPageURL = "/text-box",
            cssBorderColor = "border",
            redColor = "1px solid rgb(255, 0, 0)",
            pageText = "Text Box";


    //======TEST LOCATORS=======//
    private final SelenideElement fullNameLocator = $("#userName"),
            emailLocator = $("#userEmail"),
            currentAddressLocator = $("#currentAddress"),
            permanentAddressLocator = $("#permanentAddress"),
            submitButtonLocator = $("#submit");

    //======RESULT LOCATORS======//
    private final SelenideElement outputLocator = $("#output");


    public TextBoxPage openPage() {
        open(textboxPageURL);
        $(".text-center").shouldHave(text(pageText));
        return this;
    }

    public TextBoxPage removeBanners(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public TextBoxPage setFullName(String firstName, String lastName) {
        fullNameLocator.setValue(firstName + " " + lastName);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        emailLocator.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressLocator.setValue(value);
        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressLocator.setValue(value);
        return this;
    }

    public TextBoxPage clickSubmitButton() {
        submitButtonLocator.click();
        return this;
    }

    public TextBoxPage checkOutput(String key, String value) {
        outputLocator.shouldHave(text(key),text(":"+value));
        return this;
    }

    public TextBoxPage checkEmailInput() {
        emailLocator.shouldHave(cssValue(cssBorderColor, redColor));
        return this;
    }


}

