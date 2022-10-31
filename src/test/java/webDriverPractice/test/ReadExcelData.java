package webDriverPractice.test;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReadExcelData {
	XSSFWorkbook workBook;
	int numberOfSheets;

	@BeforeMethod
	public void setup() throws Exception {
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/webDriverPractice/initialization/Book1.xlsx");
		workBook = new XSSFWorkbook(fs);
		numberOfSheets = workBook.getNumberOfSheets();
	}
	
	@Test
	public void readData() {
		System.out.println(numberOfSheets);
		
		for(int i=0; i<=numberOfSheets-1; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				XSSFSheet sheet1 = workBook.getSheetAt(i);
				
				System.out.println("Total row in Sheet1 is " + (sheet1.getLastRowNum()+1));
				
				for (int j=0; j<=sheet1.getLastRowNum(); j++) {
					XSSFRow row = sheet1.getRow(j);
				
					Iterator<Cell> cellIterator = row.cellIterator();
					
					while (cellIterator.hasNext()) {
						System.out.print(cellIterator.next()+" ");
						System.out.println(cellIterator.next());
						System.out.println();
						
						
						
				
					}
				}
				
			}
		}
	}

}
