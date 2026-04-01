package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountApiTest {

	@Test
	public void verifyCountAPIResponse() {
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"))
		.body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment") );	
	}
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
		
		
	}
}
