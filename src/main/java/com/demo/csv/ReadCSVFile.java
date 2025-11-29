package com.demo.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
    
		//Code to read the CSV file in java!!![Important Interview Question]
		
		//CSVReader have some instance as well so we can create object
		
		/*
		 * File csvFile= new File("C:\\Users\\Omprakash\\SDET_with_Jatin\\PheonixTestAutomationFramework\\src\\main\\resources\\testData\\LoginCreds.csv"); 
		 * FileReader fr=new FileReader(csvFile);
		 */
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader isr=new InputStreamReader(is);
		
		CSVReader csvReader=new CSVReader(isr);//CSV reader constructor requires a reader
		
		List<String[]>dataList =csvReader.readAll();
		
		for(String[] dataArray:dataList) {
			for(String data : dataArray) {
				System.out.print(data+" ");
					
			}
			System.out.println("");
		}
		
	}

}
