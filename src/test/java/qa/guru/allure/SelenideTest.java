package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;


public class SelenideTest {

    private static final String
            repo = "tlstooo/demoqa";

    private static final int issueNum = 1;

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "GitHub", url = "https://github.com/"+repo)
    @DisplayName("Проверка наличия issue с номером " + issueNum + " в репозитории "+repo)
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("/");
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
        $(linkText(repo)).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);
    }

    @Test
    public void testLambdaStep() {

        step("Открываем главную страницу", () -> {
            open("/");
        });

        step("Ищем репозиторий" + repo, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(repo);
            $("#query-builder-test").submit();

        });

        step("Клик по ссылке репозитория" + repo, () -> {
            $(linkText(repo)).click();
        });

        step("Кликаем по табу Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером" + issueNum, () -> {
            $(withText("#" + issueNum)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideSteps steps = new SelenideSteps();

        steps.openMainPage();
        steps.searchRepo(repo);
        steps.clickOnRepoLink(repo);
        steps.clickOnIssueTab();
        steps.checkIssue(issueNum);
    }

}
