package api.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
	
	@DataProvider(name="AllUserData")
    public static String[][] readExcel() {
    	
    	 // Get the location of the file
        String EXCEL_DATA_FilePath = System.getProperty("user.dir") + "//testData//Userdata.xlsx";

		// A Two dimensional array of Strings which represents the data in the
		// sheet
		String[][] data = null;
		try {
			// A Buffered File Input Stream to read the data
			InputStream is = new BufferedInputStream(new FileInputStream(EXCEL_DATA_FilePath));
			// Workbook representing the excel file
			XSSFWorkbook wb = new XSSFWorkbook(is);
			// Next a sheet which represents the sheet within that excel file
			XSSFSheet sheet = wb.getSheet("Sheet1");
			// No of rows in the sheet
			int rowNum = sheet.getLastRowNum() + 1;
			// No of columns in the sheet
			int colNum = sheet.getRow(0).getLastCellNum();
			data = new String[rowNum][colNum];
			for (int i = 1; i < rowNum; i++) {
				// Get the row
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < colNum; j++) {
					// Get the columns or cells for the first row and keep
					// looping
					// for the other rows
					XSSFCell cell = row.getCell(j);
					// Make a call to the method cellToString which actually
					// converts the cell contents to String
					String value = cellToString(cell);
					data[i-1][j] = value;
					// Logic for handling the data
					// You can write the logic here, or leave the method as it
					// is to return a two dimensional array
					// representing the excel data
					//System.out.println("Value:" + value);
				}

			}
			// Print data in table format
            printTable(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	private static String cellToString(XSSFCell cell) {
		String result = null;

        if (cell != null) {
            switch (cell.getCellType()) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        result = cell.getDateCellValue().toString();
                    } else {
                        result = String.valueOf(cell.getNumericCellValue());
                    }
                    break;

                case STRING:
                    result = cell.getStringCellValue();
                    break;

                case BOOLEAN:
                    result = String.valueOf(cell.getBooleanCellValue());
                    break;

                case FORMULA:
                    result = cell.getCellFormula();
                    break;

                default:
                    throw new RuntimeException("Unknown Cell Type");
            }	
        }
        return result.toString();
	}
	
	private static void printTable(String[][] data) {
		for (String[] row : data) {
	        for (String cell : row) {
	            System.out.printf("%-15s | ", cell); // Adjust the width as per your data
	        }
	        System.out.println(); // Move to the next line for the next record
		}
	}
}
