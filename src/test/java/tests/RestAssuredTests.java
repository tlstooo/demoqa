package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import com.github.javafaker.Faker;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

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
    }

    @DisplayName("Проверка счётчика пользователей")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkUserCount() {
            given()
                .log().uri()
                .get("/api/users?page=2")
                .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("total", Matchers.notNullValue());
    }

    @DisplayName("Проверка получения данных пользователя")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkUserData() {
        given()
                .log().uri()
                .get("/api/users/2")
                .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("data.id", is(2))
                .body("data.email", Matchers.notNullValue())
                .body("data.first_name", Matchers.notNullValue())
                .body("data.last_name", Matchers.notNullValue())
                .body("data.avatar", Matchers.notNullValue());
    }

    @DisplayName("Позитивная проверка авторизации")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkPositiveUserCredentials() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given()
                .body(authData)
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .statusCode(200)
                .log().body()
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @DisplayName("Проверка ошибки при отсутствии пароля")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkErrorTextWithMissingPassword() {
        String authData = "{\"email\": \"eve.holt@reqres.in\"}";
        given()
                .body(authData)
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .statusCode(400)
                .log().body()
                .body("error", is("Missing password"));
    }

    @DisplayName("Проверка создания пользователя")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkCreateNewUser() {
        Faker faker = new Faker(new Locale("en"));
        String name = faker.name().firstName();
        String job = faker.job().title();
        String userData = "{\"name\": \"" + name + "\"," + "\"job\": \""+ job +"\"}";
        given()
                .body(userData)
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .statusCode(201)
                .log().body()
                .body("name", is(name))
                .body("job", is(job))
                .body("id", is(Matchers.notNullValue()))
                .body("createdAt", is(Matchers.notNullValue()));
    }



}
