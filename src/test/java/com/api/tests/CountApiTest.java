package com.api.tests;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static com.api.constant.Role.FD;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.DashboardService;

public class CountApiTest {
	private DashboardService dashboardService;
	
	@BeforeMethod(description="Initializing the Dashboard Service")
	public void setup() {
		dashboardService= new DashboardService();
	}

	@Test(description="Verify of the Count API is shown correctly", groups= {"smoke","api","regression"})
	public void verifyCountAPIResponse() {
		dashboardService.count(FD)
		.then()
		.spec(responseSpec_OK())
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"))
		.body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment") );	
	}
	@Test(description="Verify of the Count API is giving correct ststus code for the invalid token", groups= {"smoke","api","negative","regression"})
	public void countAPITest_MissingAuthToken() {
		dashboardService.countMissingAuthToken(FD)
		.then()
		.spec(responseSpec_TEXT(401));
		
		
	}
}
