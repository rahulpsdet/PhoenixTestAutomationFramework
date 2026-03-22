package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountApiTest {

	@Test
	public void verifyCountAPIResponse() {
		given().baseUri(ConfigManager.getProprty("BASE_URI"))
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"))
		.body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment") )
		.time(lessThan(1000L))
		.log().all();
		
	}
	@Test
	public void countAPITest_MissingAuthToken() {
		given().baseUri(ConfigManager.getProprty("BASE_URI"))
//		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
		
		
	}
}
