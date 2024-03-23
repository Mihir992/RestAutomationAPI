package api.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class ExcelUtils {
	
	@DataProvider(name="AllUserData")
	public void readDataFromExcelFile() throws IOException{
	
		//Get the location of file
		String EXCEL_DATA_FilePath = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		
		//Refer that file from location path
		FileInputStream inputStream = new FileInputStream(EXCEL_DATA_FilePath);
		
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();
		
		for(int r=0;r<rows;r++) 
		{
		    XSSFRow row=sheet.getRow(r);
			
		    for(int c=0;c<cols;c++) 
			{
			XSSFCell cell=row.getCell(c);
			
			switch(cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue());
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					break;
				}
				System.out.print(" | ");
			}
		    System.out.println();
		}
	}
}