package com.eteration.demo.user;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class UserResourceTest {

    @Test
    public void testListUsersEndpoint() {
        given()
          .when().get("/users")
          .then()
             .statusCode(200);
    }

}