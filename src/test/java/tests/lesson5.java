package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

import java.lang.reflect.Array;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class lesson5 {

    @Test
    public void HoverTest() {
        open("https://github.com");
        $(withText("Solutions")).hover();
        $(withText("Enterprises")).click();

        $$("#hero-section-brand-heading").shouldHave(itemWithText("The AI-powered\n" +
                "developer platform"));
    }

    @Test
    public void DndActionsTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        actions().clickAndHold().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
        sleep(5000);
    }

    @Test
    public void DndTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDrop(to($("#column-b")));
        sleep(5000);
    }
}
