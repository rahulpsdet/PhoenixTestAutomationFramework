package com.api.tests;

import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProprty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
 public void UserDetailsAPITest() {
	 Header authHeader = new Header("Authorization", getToken(ENG));
given()
	  .baseUri(getProprty("BASE_URI"))
	 .and()
	 .header(authHeader)
	 .accept(ContentType.JSON)
	 .log().uri()
	 .log().headers()
	 .log().body()
.when()
		.get("userdetails")
.then()
.log().all()
		.statusCode(200)
		.and()
		.time(lessThan(2500L))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	 
 }

}
