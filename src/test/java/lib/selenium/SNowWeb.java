package lib.selenium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import util.ExcelDataProvider;

public class SNowWeb extends ReusableFunctions {
	public Browser browserName;
	public String dataSheetName;


	@BeforeSuite
	public void beforeSuite(){
		System.out.println("****The test suite started sucessfully****");
	}

	/**
	 * Opens up given browser before the test method
	 * 
	 * @param browser name
	 */
	@BeforeMethod
	public void beforeMethod(){
		OpenBrowser(browserName);		
	}

	@AfterSuite
	public void afterSuite(){
		System.out.println("****The test suite completed sucessfully****");
	}

	/**
	 * Closes all opened browser after test is complete
	 * 
	 */
	@AfterMethod
	public void afterMethod(){
		//closeBrowser();
	}

	/**
	 * Retrieves data from excel and stores in two dimensional object as the data type may not be string always. Remember to assign as many column
	 * values as parameters in @Test method
	 * 
	 * @param username
	 * @param password
	 */

	@DataProvider(name="ExcelData")
	public Object[][] dataProvider() {
		String[][] data = ExcelDataProvider.getExcelData("TestData");
		return data;

	}



}
