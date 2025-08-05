package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@Tag("WBAPITests")
public class WBAPITests {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://track.wildberries.ru";
    }

    @DisplayName("Получение цены и сроков доставки")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "wbtrack", url = "https://track.wildberries.ru")
    @Test
    void checkPostData() {
        given()
                .body("{\"from_office_id\":50034215,\"to_office_id\":\"50034215\",\"parcel\":{\"category_ids\":[\"1\"],\"nm_id\":\"268417598\",\"declared_price_kop\":\"100000\"}}\n")
                .log().uri()
                .post("/api/v1/parcel")
                .then()
                .log().status()
                .statusCode(200)
                .log().body();
    }

    @DisplayName("Ошибка получения цены и сроков доставки")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "wbtrack", url = "https://track.wildberries.ru")
    @Test
    void checkErrorPostData() {
        given()
                .body("{\"from_office_id\":306754,\"to_office_id\":\"50034215\",\"parcel\":{\"category_ids\":[\"1\"],\"nm_id\":\"268417598\",\"declared_price_kop\":\"100000\"}}\n")
                .log().uri()
                .post("/api/v1/parcel")
                .then()
                .log().status()
                .statusCode(200)
                .log().body();
    }
}
