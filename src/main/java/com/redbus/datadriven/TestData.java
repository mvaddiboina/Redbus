package com.redbus.datadriven;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestData {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HSSFWorkbook  places = ReadExcelFile.loadExcel(new File("D:\\TestData\\Testdata.xls"));
		System.out.println(ReadExcelFile.getCellData(places, "Months", 1, 3));
		
	}

}
