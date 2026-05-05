package com.database.dao;


import com.database.model.CustomerDBModel;

public class DemoDaoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		CustomerDBModel customerDBData=CustomerDao.getCustomerInfo(272436);
					System.out.println(customerDBData);

	}

}
