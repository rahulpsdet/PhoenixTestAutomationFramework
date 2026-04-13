package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile_MapToPOJO {

	public static void main(String[] args) throws IOException, CsvException {

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader isr = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(isr);

		CsvToBean<UserBean> csvBean = new CsvToBeanBuilder(csvReader).withType(UserBean.class).withIgnoreEmptyLine(true)
				.build();
		List<UserBean> dataList = csvBean.parse();
		System.out.println(dataList);
	}

}
