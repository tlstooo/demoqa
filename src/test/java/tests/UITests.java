package tests;

import com.codeborne.selenide.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.*;

@Tag("uiTests")
public class UITests {
   static String
            browser = System.getProperty("browserName"),
            browserSize = System.getProperty("browserSize"),
            remote = System.getProperty("selenoidUrl"),
            browserVersion = System.getProperty("browserVersion");

    @BeforeAll
    public static void setup() {
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = remote;
        Configuration.browserVersion = browserVersion;

    }

    @BeforeEach
    @Owner("safrolov")
    public void before() {
        open("https://www.wildberries.ru/");
    }

    @DisplayName("Проверка соответствия количества баннеров количеству точек")
    @Feature("Баннеры")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "mainpage", url = "https://www.wildberries.ru/")
    @Test
    public void checkBannerCount() {
        ElementsCollection bullets = $$(".swiper-pagination-bullet:not(.swiper-pagination-bullet-active)");
        int bulletsCount = bullets.size();
        ElementsCollection slides = $$(".j-big-banners-block .swiper-wrapper");
        slides.shouldHave(CollectionCondition.size(bulletsCount));
    }



}
