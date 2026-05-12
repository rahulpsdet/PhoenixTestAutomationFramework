package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;

public class LoginAPIJSONDataDrivenTest {
	private AuthService authService;

	@BeforeMethod(description = "Initializing the Auth Services")
	public void setup() {
		authService = new AuthService();
	}

	@Test(description = "Verify if login API is working for FD useer", groups = { "api", "regression",
			"dataprovider" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIJSONDataProvider")

	public void loginAPITest(UserCredentials userCredentials) {
		authService.login(userCredentials).then().spec(responseSpec_OK()).body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}

}
