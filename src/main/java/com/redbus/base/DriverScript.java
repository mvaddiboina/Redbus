package com.redbus.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class DriverScript {

	public static String sIsMaven = "false";

	public static void main(String[] args) throws Exception {

		sIsMaven = System.getProperty("sMaven");

		if (sIsMaven==null || sIsMaven.equalsIgnoreCase("false")) {
			sIsMaven = "false";
			String sCurrDirPath = System.getProperty("user.dir");
			File directory = new File(sCurrDirPath).getAbsoluteFile();

			// String sParentPath = directory.getParent();
			// String sParentPath = directory.getAbsolutePath();

			TestNG testng = new TestNG();
			TestListenerAdapter tla = new TestListenerAdapter();

			List<String> suites = Lists.newArrayList();

			suites.add(directory + "/src/com/aveva/isv/config/COWBatch.xml");

			testng.setTestSuites(suites);
			testng.addListener(tla);
			testng.run();
		}
		else if(sIsMaven.equalsIgnoreCase("true"))
			new DriverScript().readTestNGXML();
	}

	public void readTestNGXML() throws IOException {

		InputStreamReader fileReader = null;
		String s = null;

		/* Read the testng.xml file present in the jar file */
		StringBuffer stringBuffer = new StringBuffer();
		InputStream is = this.getClass().getResourceAsStream("/config/COWBatch.xml");
		if (null != is) {
			fileReader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(fileReader);

			while ((s = br.readLine()) != null) {
				stringBuffer.append(s).append("\n");
			}
		}

		/* Create a temp xml file to load into the TestNG */
		File temp = File.createTempFile("TestNGXMLTempfile", ".xml"); 

		BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
		bw.write(stringBuffer.toString());
		bw.close();

		/* Initiate the testng suite */
		TestNG testng = new TestNG();
		TestListenerAdapter tla = new TestListenerAdapter();

		List<String> suites = Lists.newArrayList();

		suites.add(temp.getAbsolutePath());

		testng.setTestSuites(suites);
		testng.addListener(tla);
		testng.run();

		is.close();
		fileReader.close();
		temp.deleteOnExit();
	}

}
