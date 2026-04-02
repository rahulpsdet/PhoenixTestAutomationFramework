package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAdress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

public class CreateJobAPITest {
	
	
	
	@Test
	public void createJobAPITest() {
		// Creating the CreateJobPayload object
		
		Customer customer = new Customer("Rahul", "Prajapati", "807632944", "", "rahulp@123", "");
		CustomerAdress customerAdrss = new CustomerAdress("c304", "RG Luxury", "MG Road", "Bangur Nagar", "Greater Noida Extension", "211138", "India", "Uttar Pradesh");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "35265928749736", "35265928749736", "35265928749736", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problem = new Problems(1, "Battery Issue");
		Problems[] problemArray= new Problems[1];
		problemArray[0]=problem;
		CreateJobPayload createJobPayload =new CreateJobPayload(0, 2, 1, 1, customer, customerAdrss, customerProduct, problemArray);
		
		 given()
		 .spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		 .when()
		 .post("/job/create")
		 .then()
		 .spec(SpecUtil.responseSpec_OK());
	}

}
