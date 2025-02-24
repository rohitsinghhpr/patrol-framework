package Patrol.dataprovider.add_case_page;

import org.testng.annotations.DataProvider;

import Patrol.utilities.ExcelUtils;

public class HighCourtDP {
	@DataProvider(name = "high_court_data_By_Case")
	public Object[][] high_court_data_By_Case() {
		String filePath = "test-data/add_case_court_data/high_court_data.xlsx";
		String sheetName = "By_Case";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "high_court_data_By_CNR_Number")
	public Object[][] high_court_data_By_CNR_Number() {
		String filePath = "test-data/add_case_court_data/high_court_data.xlsx";
		String sheetName = "By_CNR_Number";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "high_court_data_By_Party_Name")
	public Object[][] high_court_data_By_Party_Name() {
		String filePath = "test-data/add_case_court_data/high_court_data.xlsx";
		String sheetName = "By_Party_Name";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "high_court_data_By_Advocate")
	public Object[][] high_court_data_By_Advocate() {
		String filePath = "test-data/add_case_court_data/high_court_data.xlsx";
		String sheetName = "By_Advocate";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}
}
