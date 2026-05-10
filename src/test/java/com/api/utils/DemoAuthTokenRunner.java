package com.api.utils;

import java.time.Duration;

import com.api.constant.Role;

public class DemoAuthTokenRunner {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub


for(int i=0; i<=100; i++) {
	String token = AuthTokenProvider.getToken(Role.FD);
	
	System.out.println(token);
}
	}
	}

