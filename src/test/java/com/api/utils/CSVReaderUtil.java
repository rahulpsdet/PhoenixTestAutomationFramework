package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	private CSVReaderUtil() {
	}

	public static void loadCSV(String pathOfCSVFile) {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader isr = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(isr);

		CsvToBean<UserBean> csvBean = new CsvToBeanBuilder(csvReader).withType(UserBean.class).withIgnoreEmptyLine(true)
				.build();
		List<UserBean> dataList = csvBean.parse();
		System.out.println(dataList);
	}

}
