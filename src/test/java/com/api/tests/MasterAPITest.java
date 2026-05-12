package com.api.tests;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.MasterService;

public class MasterAPITest {
	
	private MasterService masterService;

	@BeforeMethod(description = "Instantiating the Master Service Object")
	public void setup() {
		masterService = new MasterService();
	}
	@Test(description= "Verify of the Master API is shown correctly", groups= {"smoke","api","regression"})
	public void masterAPITest() {
		masterService.master(FD)
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
		masterService.masterWithoutAuth()
		.then()
		.spec(responseSpec_TEXT(401));
	}
}
