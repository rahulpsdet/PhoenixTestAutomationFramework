package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.Detail;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

public class DetailsAPITest {
	private DashboardService dashboardService;
	private Detail detailPayload;
	
	@BeforeMethod(description="Instatitaing the DashboardService")
	public void setup() {
		dashboardService= new DashboardService();
		detailPayload= new Detail("created_today");
	}
	
	@Test(description="Check deatails API is working", groups= {"api","smoke"})
	
	public void detailAPItest() {
		dashboardService.details(Role.FD, detailPayload).then().spec(SpecUtil.responseSpec_OK()).body("message",equalTo("Success"));
	}
}
