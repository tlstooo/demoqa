package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SearchJUnit5InSelenideRepoTest {

    String targetRepo = "Selenide";
    String targetText = "selenide/";
    String targetElement = "SoftAssertions";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy ="eager";
    }

    @Test
    public void searchSelenideTest() {
        open("/");
        $(".header-search-button").click();
        $("#query-builder-test").setValue(targetRepo).pressEnter();
        $("[data-testid=\"results-list\"]").$(byText(targetText)).click();
        $("#wiki-tab").click();
        $("#wiki-body a[href$='SoftAssertions']").click();
        $$(".markdown-heading")
                .findBy(text("Using JUnit5"))
                .sibling(0)
                .$("pre")
                .shouldHave(
                        text(
                        "@ExtendWith({SoftAssertsExtension.class})\n" +
                        "        class Tests {\n" +
                        "        @Test\n" +
                        "        void test() {\n" +
                        "        Configuration.assertionMode = SOFT;\n" +
                        "        open(\"page.html\");\n" +
                        "        \n" +
                        "        $(\"#first\").should(visible).click();\n" +
                        "        $(\"#second\").should(visible).click();\n" +
                        "        }\n" +
                        "        }")
                );
    }
}
