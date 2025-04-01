package generic_utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Java_File_Utility {
	
	public int randomNumber(int num) {
		Random rdm = new Random();
		int number = rdm.nextInt(num);
		return number;
	}
	
	public String generateDate(int num){
		Date dateobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String todays_date = sim.format(dateobj);
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, num);
		String closeDate = sim.format(cal.getTime());
		return closeDate;
	}
}