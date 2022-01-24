package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utility.ExcelReader;
import utility.TestUtil;

public class AddCustomerTest extends BaseTest{

	
	@Test(dataProvider="getData")
	public void AddCustomer(String fistname,String lastname,String postcode){
		
		
		
	}
	
	
	@DataProvider
	public static Object[][] getData(){
		
	
		return TestUtil.getData("AddCustomerTest");
		
		}
	
	
}
