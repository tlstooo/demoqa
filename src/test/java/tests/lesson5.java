package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

import java.lang.reflect.Array;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class lesson5 {

    @Test
    public void hoverTest() {
        open("https://github.com");
        $(withText("Solutions")).hover();
        $(withText("Enterprises")).click();

        $$("#hero-section-brand-heading").shouldHave(itemWithText("The AI-powered\n" +
                "developer platform"));
    }
}
