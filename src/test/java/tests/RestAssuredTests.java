package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;

@Tag("reqresTest")
public class RestAssuredTests {
    /*
    Разработайте 5 автотестов на запросы из https://reqres.in/
    Дайте код на ревью коллегам из вашего потока.
    После ревью они должны поставить вашему репозиторию звездочку. К сдаче принимается дз с минимум 2-мя звездами.
     */
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in/";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.getVideoUrl();
        Attach.addVideo();
    }

    @DisplayName("Проверка счётчика пользователей")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkUserCount() {
        step("Проверяем ручку /api/users?page=2", () -> {
            given()
                .log().uri()
                .get("/api/users?page=2")
                .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("total", Matchers.notNullValue());
        });
    }
}
