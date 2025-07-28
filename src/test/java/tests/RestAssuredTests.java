package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import models.lombok.LoginBodyModel;
import models.lombok.LoginResponseModel;
import org.junit.jupiter.api.*;


import static io.qameta.allure.Allure.step;
import com.github.javafaker.Faker;
import java.util.Locale;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.LoginSpec.*;

@Tag("reqresTest")
public class RestAssuredTests {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @DisplayName("Проверка получения данных пользователя")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkUserData() {
        LoginResponseModel response =
            step("Make request", () -> {
            return given(usersDataRequestSpec)
                   .get()
                   .then()
                   .spec(usersDataResponseSpec)
                   .extract().as(LoginResponseModel.class);
                });

        step("Check response", () -> {
            assertNotNull(response.getData().getAvatar());
            assertNotNull(response.getData().getLast_name());
            assertNotNull(response.getData().getFirst_name());
            assertNotNull(response.getData().getEmail());
            assertNotNull(response.getData().getId());
        });


    }

    @DisplayName("Позитивная проверка авторизации (lombok)")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkPositiveUserCredentialsWithLombok() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response =
                given(loginRequestSpec)
                .body(authData)
                .when()
                    .post()
                .then()
                    .spec(loginResponseSpec)
                    .extract().body().as(LoginResponseModel.class);


        step("Check response", () -> {
        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());});
    }


    @DisplayName("Проверка ошибки при отсутствии пароля")
    @Feature("Проверка API")
    @Owner("safrolov")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "reqres", url = "https://reqres.in")
    @Test
    void checkErrorTextWithMissingPassword() {
        String authData = "{\"email\": \"eve.holt@reqres.in\"}";
        LoginResponseModel response = given(loginRequestSpec)
                .body(authData)
                .when()
                    .post()
                .then()
                    .spec(missingPasswordResponse)
                    .extract().body().as(LoginResponseModel.class);

        step("Check error text", () -> {
            assertEquals("Missing password", response.getError());});
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
        LoginResponseModel response = given(usersRequestSpec)
                    .body(userData)
                .when()
                    .post()
                .then()
                .spec(usersResponseSpec)
                .extract().body().as(LoginResponseModel.class);

        step("Check name", () -> {
            assertEquals(name, response.getName());});

        step("Check job", () -> {
            assertEquals(job, response.getJob());});

        step("Check id is not null", () -> {
            assertNotNull(response.getId());});

        step("Check created at date is not null", () -> {
            assertNotNull(response.getCreatedAt());});
    }

}
