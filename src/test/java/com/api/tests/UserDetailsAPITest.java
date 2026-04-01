package com.api.tests;

import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProprty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constant.Role.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
 public void UserDetailsAPITest() {
		
given()
	 .spec(SpecUtil.requestSpecWithAuth(FD))
.when()
		.get("userdetails")
.then()
.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	 
 }

}
