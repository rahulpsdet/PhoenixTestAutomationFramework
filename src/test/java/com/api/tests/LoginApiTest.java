package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;

@Listeners(com.listeners.APITestListener.class)
public class LoginApiTest {
	private UserCredentials userCredentail;
	private AuthService authService;

	@BeforeMethod(description = "Create the Payload for the login API test")
	public void setup() {
		userCredentail = new UserCredentials("iamfd", "password");
		authService = new AuthService();
	}

	@Test(description = "Verify if login API is working for FD useer", groups = { "api", "regression", "smoke" })

	public void loginAPITest() throws IOException {
		authService.login(userCredentail)
		.then().spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.and()
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}

}
