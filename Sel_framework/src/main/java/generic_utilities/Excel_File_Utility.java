package generic_utilities;

import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_File_Utility {
	
	public String readingDataFromExcelFile(String sheet,int row, int cell) throws IOException
	{
	FileInputStream fis = new FileInputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel_notes\\Exceldata.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	String data = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
	return data;
}
}
