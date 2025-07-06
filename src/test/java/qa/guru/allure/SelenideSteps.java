package qa.guru.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideSteps {
    @Step("Открываем тестируемую страницу")
    public void openMainPage() {
        open("/");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchRepo(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("Клик по результату поиска")
    public void clickOnRepoLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Клик по табу Issue")
    public void clickOnIssueTab()
    {
        $("#issues-tab").click();
    }

    @Step("Проверка наличия Issue с номером {issueNum}")
    public void checkIssue(int issueNum)
    {
        $(withText("#"+ issueNum)).should(Condition.exist);
    }
}
