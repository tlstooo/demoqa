package tests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("uiTests")
public class UITests {
   static String
            browser = System.getProperty("browserName"),
            browserSize = System.getProperty("browserSize"),
            remoteHost = System.getProperty("remoteHost"),
            browserVersion = System.getProperty("browserVersion");

   MainPage
           mainPage = new MainPage();

    static int
           slidesCount = 6;//Integer.parseInt(System.getProperty("singleSlidesCount"));

    @BeforeAll
    public static void setup() {
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = remoteHost;
        Configuration.browserVersion = browserVersion;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }


    @BeforeEach
    @Owner("safrolov")
    public void before() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        step("Открываем сайт", () ->
        {open("https://www.wildberries.ru/");});
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.getVideoUrl();
        Attach.addVideo();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @DisplayName("Проверка соответствия количества баннеров количеству точек")
    @Feature("Слайдеры")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "mainpage", url = "https://www.wildberries.ru/")
    @Test
    public void checkBannerCount() {
        step("Открываем главную страницу", () ->
        { mainPage.openMainPage();});
        step("Получаем количество кнопок карусели", () ->
        {
            mainPage.checkEqualBulletsCount();});
        }

    @DisplayName("Проверка наличия карточек в слайдере на главной странице")
    @Feature("Слайдеры")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "mainpage", url = "https://www.wildberries.ru/")
    @Test
    public void checkSingleSlideCount() {
        step("Открываем главную страницу", () ->
        {   mainPage.openMainPage();});
        step("Получаем количество объектов в слайдере", () ->
        {   mainPage.checkSingleSlideCount(6);});
    }
}

