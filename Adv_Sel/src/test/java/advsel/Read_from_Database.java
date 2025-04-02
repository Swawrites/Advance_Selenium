package advsel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class Read_from_Database {

	public static void main(String[] args) throws SQLException {
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_store", "root",
				"bangtan#swa2712");
		Statement stmnt = conn.createStatement();
		ResultSet result = stmnt.executeQuery("Select * from CommonData");

		while (result.next()) {
			String browser = result.getString(1);
			String url = result.getString(2);
			String uname = result.getString(3);
			String password = result.getString(4);

			System.out.println(browser);
			System.out.println(url);
			System.out.println(uname);
			System.out.println(password);
		}
		conn.close();
	}
}
