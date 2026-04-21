package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;

public class CreateJobAPIDataDrivenTest {
		
	@Test(description="Verify if the crrate job API is able to create the Inwarranty job", groups= {"datadriven","api","regression"}
	,dataProviderClass=com.dataproviders.DataProviderUtils.class
			,dataProvider="CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		// Creating the CreateJobPayload object
		
		
		 given()
		 .spec(requestSpecWithAuth(Role.FD, createJobPayload))
		 .when()
		 .post("/job/create")
		 .then()
		 .spec(responseSpec_OK())
		 .body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIresponseSchema.json"))
		 .body("message", Matchers.equalTo("Job created successfully. "))
		 .body("data.id", Matchers.notNullValue())
		 .body("data.mst_service_location_id", Matchers.equalTo(1))
		 .body("data.job_number", Matchers.startsWith("JOB"))
		 ;
		 
	}

}
