package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Plateform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.Service_Location;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.services.JobService;

public class CreateJobAPITest {
	CreateJobPayload createJobPayload;
	JobService jobService;
	@BeforeMethod(description="Creating payload for crerate job API test")
	public void setup()
	{
		jobService= new JobService();
		Customer customer = new Customer("Rahul", "Prajapati", "807632944", "", "rahulp@123", "");
		CustomerAdress customerAdrss = new CustomerAdress("c304", "RG Luxury", "MG Road", "Bangur Nagar", "Greater Noida Extension", "211138", "India", "Uttar Pradesh");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "34260227759411", "34260227759411", "34260227759411", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_Blue.getCode());
		Problems problem = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemList= new ArrayList<Problems>();
		problemList.add(problem);
	    createJobPayload =new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Plateform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAdrss, customerProduct, problemList);
		
	}	
	@Test(description="Verify if the crrate job API is able to create the Inwarranty job", groups= {"smoke","api","regression"})
	public void createJobAPITest() {
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
