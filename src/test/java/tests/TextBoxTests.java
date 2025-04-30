package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import pages.TextBoxPage;



public class TextBoxTests {

    TextBoxPage textBoxPage = new TextBoxPage();

    String myName = "Sergey";
    String myLastName = "Frolov";
    String myEmail = "myEmail@email.com";
    String myBrokenEmail = "myEmail";
    String myCurrentAddress = "Uttar Pradesh";
    String myPermanentAddress = "Lucknow Nek Rech";


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void successfulTextBoxTest() {
        textBoxPage
                .openPage()
                .removeBanners()
                .setFullName(myName, myLastName)
                .setEmail(myEmail)
                .setCurrentAddress(myCurrentAddress)
                .setPermanentAddress(myPermanentAddress)
                .clickSubmitButton();

        textBoxPage
                .checkOutput("Name",myName + " " + myLastName)
                .checkOutput("Email",myEmail)
                .checkOutput("Current Address",myCurrentAddress)
                .checkOutput("Permananet Address",myPermanentAddress);
    }

    @Test
    public void emailInputValidationTest() {
        textBoxPage
                .openPage()
                .removeBanners()
                .setEmail(myBrokenEmail)
                .clickSubmitButton();

        textBoxPage
                .checkEmailInput();
    }
}

