package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public final String mainPageURL = "https://www.wildberries.ru/";
    private final ElementsCollection
            bullets = $$(".swiper-pagination-bullet:not(.swiper-pagination-bullet-active)"),
            slides = $$(".j-big-banners-block .swiper-wrapper"),
            productCard = $$(".j-open-full-product-card"),
            singleItemsSlider = $$(".banners-catalog-custom__container");



    private final SelenideElement
            swiperContainer = $(".j-single-banner"),
            article = $("#productNmId");




    public MainPage openMainPage() {
        open(mainPageURL);
        return this;
    }

    public MainPage scrollUntilExists(String css, int stepPx, int maxSteps) {
        for (int i = 0; i < maxSteps; i++) {
            if ($(css).exists()) return this;
            executeJavaScript("window.scrollBy(0, arguments[0]);", stepPx);
            sleep(300);
        }
        $(css).should(exist);
        return this;
    }

    public MainPage checkEqualBulletsCount() {

        int bulletsCount = bullets.size();
        slides.shouldHave(CollectionCondition.size(bulletsCount));

        return this;
    }

    public MainPage scrollToSwiperContainer() {
        swiperContainer.should(exist).scrollTo();
        return this;
    }

    public MainPage checkSingleSlideCount(int slidesCount) {
        singleItemsSlider.shouldHave(CollectionCondition.sizeGreaterThan(slidesCount));
        return this;
    }

    public MainPage firstProductClick() {
        productCard.first().click();
        return this;
    }

    public MainPage checkArticleIsNotNull() {
        article.shouldNotBe(text(""));
        return this;
    }

}
