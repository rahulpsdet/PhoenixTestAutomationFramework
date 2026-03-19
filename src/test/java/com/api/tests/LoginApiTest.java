package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	
	@Test
	
	public void loginAPITest() throws IOException {
	UserCredentials userCredentail = new UserCredentials("iamfd","password");
		given()
		.baseUri(getProprty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.and()
		.body(userCredentail)
		.log().uri()
		.log().method()
		.log().body()
	.when()
			.post("login")
    .then()
    .log().all()
    		.statusCode(200)
    		.time(lessThan(2500L))
    		.and()
    		.body("message", equalTo("Success"))
    		.and()
    		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
		
	}

}
