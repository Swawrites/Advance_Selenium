package advsel;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import generic_utilities.Excel_File_Utility;

public class ReadingDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		Excel_File_Utility excl = new Excel_File_Utility();
		String Campaign_name = excl.readingDataFromExcelFile("Data",1,2);
		String TargetSize = excl.readingDataFromExcelFile("Data",1,3);
		System.out.println(Campaign_name);
		System.out.println(TargetSize);
	}

}
