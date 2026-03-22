package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
	public static void masterAPITest() {
		
		given()
		.baseUri(getProprty("BASE_URI"))
		.header("Authorization",getToken(FD))
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("/master")
		.then()
		.statusCode(200)
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
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"))
		.log().all();
		
	}
	
	@Test
	public void invalidMasterAPITest() {
		given()
		.baseUri(getProprty("BASE_URI"))
		.header("Authorization","")
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("/master")
		.then()
		.statusCode(401);
	}
}
