package advsel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class Updation_To_DB {

	public static void main(String[] args) throws SQLException {
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_store", "root", "bangtan#swa2712");
		Statement stmt = conn.createStatement();
		//For inserting values
		int result = stmt.executeUpdate("Insert Into commondata values ('Firefox','http://49.249.28.218:8098/','rmgyantra','rmgy@9999')");
		
		//For updating values values
		//int result = stmt.executeUpdate("Update commondata set uname='admin' where Browser='Edge'");
		//For deleting the values
		//int result = stmt.executeUpdate("Delete from commondata where Browser='Firefox'");
		if(result==0) {
			System.out.println("Query execution unsuccessful");
		}else {
			System.out.println("Query execution successful");
		}
		conn.close();
		
	}

}
