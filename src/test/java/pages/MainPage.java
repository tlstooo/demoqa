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
            slides = $$(".j-big-banners-block .swiper-wrapper"),
            singleItemsSlider = $$(".banners-catalog-custom__container");


    public MainPage openMainPage() {
        open(mainPageURL);
        return this;
    }

    public MainPage checkEqualBulletsCount() {

        int bulletsCount = bullets.size();
        slides.shouldHave(CollectionCondition.size(bulletsCount));

        return this;
    }

    public MainPage checkSingleSlideCount(int slidesCount) {
        singleItemsSlider.shouldHave(CollectionCondition.sizeGreaterThan(slidesCount));
        return this;
    }
}
