package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	private final static String COUNTRY="India";
	
	public static void main(String[] args) {
	
		//customer fake object
	Faker faker = new Faker(new Locale("en-IND"));
	
String firstName=	faker.name().firstName();
String lastName=	faker.name().lastName();
String mobileNumber=faker.numerify("70########");
String alternateMobileNumber=faker.numerify("70########");
String emailAdress=	faker.internet().emailAddress();
String alrernatEmailAdress=	faker.internet().emailAddress();
	

Customer customer =new Customer(firstName, lastName, mobileNumber, alternateMobileNumber, emailAdress, alrernatEmailAdress);
	System.out.println(customer);
	
	//CustomerAdress fake object
	String flatNumber=faker.numerify("###");
	String apartmentName=faker.address().streetName();
	String streetName=faker.address().streetName();
	String landmark=faker.address().streetName();
	String areaName=faker.address().streetName();
	String pincode=faker.numerify("#####");
	String state=faker.address().state();

	
	CustomerAdress customerAdress = new CustomerAdress(flatNumber, apartmentName, streetName, landmark, areaName, pincode, COUNTRY, state);
	
	System.out.println(customerAdress);
	// CustomerProduct fake object
	String dop=DateTimeUtil.getTimeWithDaysAgo(10);
	String iemiSerialNumber = faker.numerify("################");
String popUrl = faker.internet().url();
	
	CustomerProduct customerProduct = new CustomerProduct(dop, iemiSerialNumber, iemiSerialNumber, iemiSerialNumber, popUrl, 1, 1);
	
	System.out.println(customerProduct);
	
	// CustomerProduct fake object
	
	String remark=faker.lorem().sentence(5);
	
	Random random =new Random();
	int problemID = random.nextInt(26)+1;
	
	Problems problems = new Problems(problemID, remark);
	System.out.println(problems);
	List<Problems> problemList = new ArrayList<Problems>();
	
	problemList.add(problems);
	
	
	
	
	
	CreateJobPayload payload = new CreateJobPayload(0, 2, 1, 1, customer, customerAdress, customerProduct, problemList) ;
	
	System.out.println(payload);
	}

}
