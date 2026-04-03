package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	
	
	@Test
	public void createJobAPITest() {
		// Creating the CreateJobPayload object
		
		Customer customer = new Customer("Rahul", "Prajapati", "807632944", "", "rahulp@123", "");
		CustomerAdress customerAdrss = new CustomerAdress("c304", "RG Luxury", "MG Road", "Bangur Nagar", "Greater Noida Extension", "211138", "India", "Uttar Pradesh");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "34265927759749", "34265927759749", "34265927759749", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problem = new Problems(1, "Battery Issue");
		List<Problems> problemList= new ArrayList<Problems>();
		problemList.add(problem);
		CreateJobPayload createJobPayload =new CreateJobPayload(0, 2, 1, 1, customer, customerAdrss, customerProduct, problemList);
		
		 given()
		 .spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		 .when()
		 .post("/job/create")
		 .then()
		 .spec(SpecUtil.responseSpec_OK())
		 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIresponseSchema.json"))
		 .body("message", Matchers.equalTo("Job created successfully. "))
		 .body("data.id", Matchers.notNullValue())
		 .body("data.mst_service_location_id", Matchers.equalTo(1))
		 .body("data.job_number", Matchers.startsWith("JOB"))
		 ;
		 
	}

}
