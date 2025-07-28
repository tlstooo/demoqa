package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.lombok.LoginResponseModel;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class LoginSpec {
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .contentType(JSON)
            .basePath("/api/login")
            .header("x-api-key", "reqres-free-v1");

    public static RequestSpecification usersRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .contentType(JSON)
            .basePath("/api/users")
            .header("x-api-key", "reqres-free-v1");

    public static RequestSpecification usersDataRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .contentType(JSON)
            .basePath("/api/users/2")
            .header("x-api-key", "reqres-free-v1");

    public static ResponseSpecification usersResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(201)
            .log(BODY)
            .log(HEADERS)
            .build();

    public static ResponseSpecification usersDataResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(200)
            .log(BODY)
            .log(HEADERS)
            .build();



    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification missingPasswordResponse = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();
}
