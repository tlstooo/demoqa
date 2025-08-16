package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public final String mainPageURL = "/";
    private final ElementsCollection
            bullets = $$(".swiper-pagination-bullet:not(.swiper-pagination-bullet-active)"),
            slides = $$(".j-big-banners-block .swiper-wrapper");

    public MainPage openMainPage() {
        open(mainPageURL);
        return this;
    }

    public MainPage checkEqualBulletsCount() {

        ElementsCollection bullets = $$(".swiper-pagination-bullet:not(.swiper-pagination-bullet-active)");
        int bulletsCount = bullets.size();
        ElementsCollection slides = $$(".j-big-banners-block .swiper-wrapper");
        slides.shouldHave(CollectionCondition.size(bulletsCount));

        return this;
    }

    public MainPage checkSingleSlideMinimumCount(int slidesCount) {
        ElementsCollection singleItemsSlider = $(".j-single-banner").$$(".swiper-slide-duplicate");
        singleItemsSlider.shouldHave(CollectionCondition.size(slidesCount));
        return this;
    }
}
