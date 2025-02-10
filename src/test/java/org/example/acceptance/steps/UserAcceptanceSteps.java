package org.example.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.dto.FollowUserRequestDto;
import org.springframework.http.MediaType;

public class UserAcceptanceSteps {

    public static ExtractableResponse<Response> createUser(CreateUserRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/user")
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> followUser(FollowUserRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/relation/follow")
                .then()
                .extract();
    }
}
