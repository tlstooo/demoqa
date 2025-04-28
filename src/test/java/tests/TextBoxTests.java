package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import pages.TextBoxPage;



public class TextBoxTests {

    TextBoxPage textBoxPage = new TextBoxPage();

    String MyName = "Sergey";
    String MyLastName = "Frolov";
    String MyEmail = "MyEmail@email.com";
    String MyBrokenEmail = "MyEmail";
    String MyCurrentAddress = "Uttar Pradesh";
    String MyPermanentAddress = "Lucknow Nek Rech";


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void successfulTextBoxTest() {
        textBoxPage
                .openPage()
                .removeBanners()
                .setFullName(MyName, MyLastName)
                .setEmail(MyEmail)
                .setCurrentAddress(MyCurrentAddress)
                .setPermanentAddress(MyPermanentAddress)
                .clickSubmitButton();

        textBoxPage
                .checkOutput("Name",MyName + " " + MyLastName)
                .checkOutput("Email",MyEmail)
                .checkOutput("Current Address",MyCurrentAddress)
                .checkOutput("Permananet Address",MyPermanentAddress);
    }

    @Test
    public void emailInputValidationTest() {
        textBoxPage
                .openPage()
                .removeBanners()
                .setEmail(MyBrokenEmail)
                .clickSubmitButton();

        textBoxPage
                .checkEmailInput();
    }
}

