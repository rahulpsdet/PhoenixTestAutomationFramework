package com.api.utils;

import static com.api.utils.ConfigManager.getProprty;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
// get
	public static RequestSpecification requestSpec() {
		// To take care of the common request sections (methods)
		
 RequestSpecification request = new RequestSpecBuilder()
 .setBaseUri(getProprty("BASE_URI"))
 .setContentType(ContentType.JSON)
 .setAccept(ContentType.JSON)
 .log(LogDetail.URI)
 .log(LogDetail.METHOD)
 .log(LogDetail.BODY)
 .build();
 return request;
		
		}
	// put - patch- post
		public static RequestSpecification requestSpec(Object payload) {
			// To take care of the common request sections (methods)
			
	 RequestSpecification requestSpecification = new RequestSpecBuilder()
	 .setBaseUri(getProprty("BASE_URI"))
	 .setContentType(ContentType.JSON)
	 .setAccept(ContentType.JSON)
	 .setBody(payload)
	 .log(LogDetail.URI)
	 .log(LogDetail.METHOD)
	 .log(LogDetail.BODY)
	 .build();
	 return requestSpecification;
			
			}
		
		public static ResponseSpecification responseSpec_OK() {
			// To take care of the common request sections (methods)
			
	 ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			 .expectContentType(ContentType.JSON)
			 .expectStatusCode(200)
			 .expectResponseTime(Matchers.lessThan(2000L))
			 .log(LogDetail.ALL)
	 .build();
	 return responseSpecification;
			
			}
		public static RequestSpecification requestSpecWithAuth(Role role) {
			// To take care of the common request sections (methods)
			
	 RequestSpecification requestSpecification = new RequestSpecBuilder()
	 .setBaseUri(getProprty("BASE_URI"))
	 .setContentType(ContentType.JSON)
	 .setAccept(ContentType.JSON)
	 .addHeader("Authorization", AuthTokenProvider.getToken(role))
	 .log(LogDetail.URI)
	 .log(LogDetail.METHOD)
	 .log(LogDetail.BODY)
	 .build();
	 return requestSpecification;
			
			}
		public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
			// To take care of the common request sections (methods)
			
	 RequestSpecification requestSpecification = new RequestSpecBuilder()
	 .setBaseUri(getProprty("BASE_URI"))
	 .setContentType(ContentType.JSON)
	 .setAccept(ContentType.JSON)
	 .setBody(payload)
	 .addHeader("Authorization", AuthTokenProvider.getToken(role))
	 .log(LogDetail.URI)
	 .log(LogDetail.METHOD)
	 .log(LogDetail.BODY)
	 .build();
	 return requestSpecification;
			
			}
		
		public static ResponseSpecification responseSpec_JSON(int Statuscode) {
			// To take care of the common request sections (methods)
			
	 ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			 .expectContentType(ContentType.JSON)
			 .expectStatusCode(Statuscode)
			 .expectResponseTime(Matchers.lessThan(2000L))
			 .log(LogDetail.ALL)
	 .build();
	 return responseSpecification;
			
			}
		public static ResponseSpecification responseSpec_TEXT(int Statuscode) {
			// To take care of the common request sections (methods)
			
	 ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			 .expectStatusCode(Statuscode)
			 .expectResponseTime(Matchers.lessThan(2000L))
			 .log(LogDetail.ALL)
	 .build();
	 return responseSpecification;
			
			}
}
