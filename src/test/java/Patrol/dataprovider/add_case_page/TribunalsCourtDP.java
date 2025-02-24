package Patrol.dataprovider.add_case_page;

import org.testng.annotations.DataProvider;

import Patrol.utilities.ExcelUtils;

public class TribunalsCourtDP {
	@DataProvider(name = "tribunals_court_data_NCLT")
	public Object[][] tribunals_court_data_NCLT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "NCLT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_NCLAT")
	public Object[][] tribunals_court_data_NCLAT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "NCLAT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_ITAT")
	public Object[][] tribunals_court_data_ITAT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "ITAT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_APTET")
	public Object[][] tribunals_court_data_APTET() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "APTET";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_CONSUMMER_COURT_NCDRC")
	public Object[][] tribunals_court_data_CONSUMMER_COURT_NCDRC() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "CONSUMMER_COURT_NCDRC";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_CONSUMMER_COURT_SCDRC")
	public Object[][] tribunals_court_data_CONSUMMER_COURT_SCDRC() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "CONSUMMER_COURT_SCDRC";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_CONSUMMER_COURT_DCDRC")
	public Object[][] tribunals_court_data_CONSUMMER_COURT_DCDRC() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "CONSUMMER_COURT_DCDRC";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_DRT")
	public Object[][] tribunals_court_data_DRT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "DRT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_CAT")
	public Object[][] tribunals_court_data_CAT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "CAT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_TDSAT")
	public Object[][] tribunals_court_data_TDSAT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "TDSAT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_NGT")
	public Object[][] tribunals_court_data_NGT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "NGT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_SAT")
	public Object[][] tribunals_court_data_SAT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "SAT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_RERA")
	public Object[][] tribunals_court_data_RERA() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "RERA";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_CESTAT")
	public Object[][] tribunals_court_data_CESTAT() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "CESTAT";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}

	@DataProvider(name = "tribunals_court_data_ELECTRICITY_COMMISSION")
	public Object[][] tribunals_court_data_ELECTRICITY_COMMISSION() {
		String filePath = "test-data/add_case_court_data/tribunals_court_data.xlsx";
		String sheetName = "ELECTRICITY_COMMISSION";
		return ExcelUtils.getExcelData(filePath, sheetName);
	}
}
