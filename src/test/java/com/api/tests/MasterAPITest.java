package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {

	@Test(description="Verify of the Master API is shown correctly", groups= {"smoke","api","regression"})
	public static void masterAPITest() {
		
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.post("/master")
		.then()
		.spec(responseSpec_OK())
		.body("data",notNullValue())
		.body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("message",equalTo("Success"))
		.body("$",hasKey("message"))
		.body("$",hasKey("data"))
		.body("data.mst_oem.size()",greaterThan(0))
		.body("data.mst_model.size()",greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"))
		;
		
	}
	
	@Test(description="Verify of the Master API is giving correct ststus code for the invalid token", groups= {"smoke","api","negative","regression"})
	public void invalidMasterAPITest() {
		given()
		.spec(requestSpec())
		.when()
		.post("/master")
		.then()
		.spec(responseSpec_TEXT(401));
	}
}
