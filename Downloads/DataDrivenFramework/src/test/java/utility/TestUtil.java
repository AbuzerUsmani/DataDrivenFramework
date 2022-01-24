package utility;

import org.testng.annotations.DataProvider;

import base.BaseTest;

public class TestUtil extends BaseTest{
	
	
	@DataProvider
	public static Object[][] getData(String sheetName){
		
	
	
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
				
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){ //2 data[rowsNum-2][cols]
			
			for(int colNum=0 ; colNum< cols; colNum++){
				
				//data[0][0]
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum); //-2
			}
		
		}
		return data;
		
		}

}
