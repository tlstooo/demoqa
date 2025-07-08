package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Tag("parametrizeTest")
public class ParametrizeTest {

    @BeforeAll
    public static void setup() {
        Configuration.pageLoadStrategy = "eager";

    }

    @BeforeEach
    public void before() {
        open("https://market.yandex.ru");
    }

    @ParameterizedTest(name = "Поиск товаров по имени {0}")
    @CsvSource({
            "Python, Python в",
            "Java, Java в",
            "Горе от ума, Горе от ума в"
    })
    public void testSearchFunctionality(String searchQuery, String expectedResult) {
        $("#header-search").setValue(searchQuery).pressEnter();
        $("[data-auto='title']").shouldHave(innerText(expectedResult));
        $$("[data-zone-name='searchPage']").shouldBe(sizeGreaterThan(0));
    }


    @ParameterizedTest(name = "Поиск товаров по имени {0}")
    @ValueSource(strings = {
            "Кирпич", "Солнцезащитные очки", "Манго"
    })
    public void testSearchFunctionality(String searchQuery) {
        $("#header-search").setValue(searchQuery).pressEnter();
        $("[data-baobab-name='title']").shouldHave(innerText(searchQuery));
    }

    static Stream<Arguments> testSearchFunctionality() {
        return Stream.of(
                Arguments.of(
                List.of("Ultima","Сплит","Любимая категория","Из-за рубежа","Одежда", "Дом", "Ремонт", "Детям"))
        );
    }
    
    @MethodSource
    @ParameterizedTest(name = "Проверка видимости кнопок {0}")
    public void testSearchFunctionality(List<String> buttons)
    {
        $$("[data-baobab-name='department']").filter(visible).shouldHave(texts(buttons));
    }

    // Очистка после теста
    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
