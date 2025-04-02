package advsel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WritingDataIntoExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel_notes\\Exceldata02.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet("Newsheet").createRow(0).createCell(0).setCellValue("Advance Selenium");
		FileOutputStream fos = new FileOutputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel_notes\\Exceldata02.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("Written data successfully");
	}

}
