package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.Search;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

public class SearchAPITest {
JobService jobService;
private static final String JOB_NUMBEER="JOB_277903";
private Search searchPayload;
	
	@BeforeMethod(description="Initializing the Job Service")
	public void setup() {
		jobService= new JobService();
		searchPayload= new Search(JOB_NUMBEER);
	}
	
	@Test(description="Verify if the Search API is working fine ", groups= {"api","smoke"})
	public void searchAPITest() {
		jobService.search(Role.FD, searchPayload).then()
		.spec(SpecUtil.responseSpec_OK()).body("message", Matchers.equalTo("Success"));
	}
}
