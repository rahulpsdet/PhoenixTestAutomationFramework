package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {
	
	@Test(description="Verify of the userdetails API is shown correctly", groups= {"smoke","api","regression"})
 public void userDetailsAPITest() {
		
given()
	 .spec(requestSpecWithAuth(FD))
.when()
		.get("userdetails")
.then()
.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	 
 }

}
