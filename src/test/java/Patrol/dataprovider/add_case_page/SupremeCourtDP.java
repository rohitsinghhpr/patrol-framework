package Patrol.dataprovider.add_case_page;

import org.testng.annotations.DataProvider;

import Patrol.utilities.ExcelUtils;

public class SupremeCourtDP {
	@DataProvider(name = "supreme_court_data_By_Dairy_Number")
	public Object[][] supreme_court_data_By_Dairy_Number() {
		String filePath = "test-data/add_case_court_data/supreme_court_data.xlsx";
		String sheetName = "By_Dairy_Number";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "supreme_court_data_By_Case_Type")
	public Object[][] supreme_court_data_By_Case_Type() {
		String filePath = "test-data/add_case_court_data/supreme_court_data.xlsx";
		String sheetName = "By_Case_Type";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "supreme_court_data_By_Party_Name")
	public Object[][] supreme_court_data_By_Party_Name() {
		String filePath = "test-data/add_case_court_data/supreme_court_data.xlsx";
		String sheetName = "By_Party_Name";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "supreme_court_data_By_Advocate_Name")
	public Object[][] supreme_court_data_By_Advocate_Name() {
		String filePath = "test-data/add_case_court_data/supreme_court_data.xlsx";
		String sheetName = "By_Advocate_Name";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}
}
