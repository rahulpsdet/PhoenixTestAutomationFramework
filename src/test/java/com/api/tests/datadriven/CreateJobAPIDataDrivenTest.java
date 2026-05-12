package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.services.JobService;

public class CreateJobAPIDataDrivenTest {
	JobService jobService;
	
	@BeforeMethod(description="Initializing the Job Service")
	public void setup() {
		jobService= new JobService();
	}
		
	@Test(description="Verify if the crrate job API is able to create the Inwarranty job", groups= {"datadriven","api","regression","csv"}
	,dataProviderClass=com.dataproviders.DataProviderUtils.class
			,dataProvider="CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		// Creating the CreateJobPayload object
		
		
		jobService.createJob(Role.FD, createJobPayload)
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
