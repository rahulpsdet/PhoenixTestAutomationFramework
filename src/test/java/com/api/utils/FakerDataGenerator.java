package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAdress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	private static Faker faker = new Faker(new Locale("en-IND"));
	private final static String COUNTRY = "India";
	private final static Random RANDOM = new Random();
	private final static int MST_SERVICE_lOCATION_ID = 0;;
	private final static int MST_PLATEFORM_ID = 2;
	private final static int MST_WARRANTY_STATUS_ID = 1;
	private final static int MST_OEM_ID = 1;
	private final static int PRODUCT_ID = 1;
	private final static int MST_MODEL_ID = 1;
	private final static int validProblemsID[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {
		Customer customer = generateFakeCustomerData();
		CustomerAdress customerAdress = generateFakeCustomerAdressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeProblemListData();

		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_lOCATION_ID, MST_PLATEFORM_ID,
				MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAdress, customerProduct, problemList);
		return payload;

	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for (int i = 1; i <= count; i++) {
			Customer customer = generateFakeCustomerData();
			CustomerAdress customerAdress = generateFakeCustomerAdressData();
			CustomerProduct customerProduct = generateFakeCustomerProductData();
			List<Problems> problemList = generateFakeProblemListData();
			CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_lOCATION_ID, MST_PLATEFORM_ID,
					MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAdress, customerProduct, problemList);
			payloadList.add(payload);
		}

		return payloadList.iterator();

	}

	private static Customer generateFakeCustomerData() {
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String mobileNumber = faker.numerify("70########");
		String alternateMobileNumber = faker.numerify("70########");
		String emailAdress = faker.internet().emailAddress();
		String alrernatEmailAdress = faker.internet().emailAddress();
		Customer customer = new Customer(firstName, lastName, mobileNumber, alternateMobileNumber, emailAdress,
				alrernatEmailAdress);
		return customer;
	}

	private static CustomerAdress generateFakeCustomerAdressData() {
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String areaName = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();
		CustomerAdress customerAdress = new CustomerAdress(flatNumber, apartmentName, streetName, landmark, areaName,
				pincode, COUNTRY, state);
		return customerAdress;
	}

	private static CustomerProduct generateFakeCustomerProductData() {
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String iemiSerialNumber = faker.numerify("################");
		String popUrl = faker.internet().url();
		CustomerProduct customerProduct = new CustomerProduct(dop, iemiSerialNumber, iemiSerialNumber, iemiSerialNumber,
				popUrl, PRODUCT_ID, MST_MODEL_ID);
		return customerProduct;
	}

	private static List<Problems> generateFakeProblemListData() {
		int count = RANDOM.nextInt(3)+1;
		int randomIndex;
		String remark;
		Problems problems;
		List<Problems> problemList = new ArrayList<Problems>();

		for (int i=1; i<= count; i++) {
		randomIndex = RANDOM.nextInt(validProblemsID.length);
		 remark = faker.lorem().sentence(5);
		 problems = new Problems(validProblemsID[randomIndex], remark);
		problemList.add(problems);
		}
		return problemList;

	}
}
