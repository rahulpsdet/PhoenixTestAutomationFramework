package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> LoginAPIDataProvider() {

		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIDataProvider() {

		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv",
				CreateJobBean.class);

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		CreateJobPayload tempPayload;
		CreateJobBean temBean;
		while (createJobBeanIterator.hasNext()) {
			temBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(temBean);
			payloadList.add(tempPayload);
		}
		return payloadList.iterator();

	}

	@DataProvider(name = "CreateJobAPIFakeDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIFakeDataProvider() {
		
		String fakerCount=System.getProperty("fakerCount","5");
		int fakerCountInt= Integer.parseInt(fakerCount);
		
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;

	}
	
	@DataProvider(name = "LoginAPIJSONDataProvider", parallel = true)
	public static Iterator<UserCredentials> LoginAPIJSONDataProvider() {

		return JsonReaderUtil.loadJSON("testData/loginAPITestData.json", UserCredentials[].class);

	}
	
	@DataProvider(name = "CreateJobAPIJSONDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIJSONDataProvider() {
		
		return JsonReaderUtil.loadJSON("testData/CreateJobAPIData.json", CreateJobPayload[].class);

	}
	
	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserBean> LoginAPIExcelDataProvider() {

		
		return ExcelReaderUtil.loadExcelTestData("LoginTestData", "testData/PhoenixTestData.xlsx", UserBean.class);
	}
	
	@DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIExcelDataProvider() {

		Iterator<CreateJobBean> createJobBeanIterator = ExcelReaderUtil.loadExcelTestData("CreateJobTestData", "testData/PhoenixTestData.xlsx", CreateJobBean.class);

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		CreateJobPayload tempPayload;
		CreateJobBean temBean;
		while (createJobBeanIterator.hasNext()) {
			temBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(temBean);
			payloadList.add(tempPayload);
		}
		return payloadList.iterator();

	}
}
