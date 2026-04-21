package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {
	public static void main(String[] args) {
		
		Faker faker = new Faker(new Locale("en-IND"));
		String firstName=faker.name().firstName();
		String lastName	=faker.name().lastName();
		String emailAdress=faker.internet().emailAddress();
		String number=faker.numerify("971########");
		System.out.println(firstName);
		System.out.println(number);
	}

}
