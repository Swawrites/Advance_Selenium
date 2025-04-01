package generic_utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class Database_Utility
{
	Connection Conn;
	public void getDBConnection(String url, String uname, String password) throws SQLException{
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			Conn = DriverManager.getConnection(url,uname,password);
		}catch (Exception e) {
			System.out.println("Connection Not established");
		}
	}
	

	public void closeDBConnection() {
		try
		{
			Conn.close();
		}
		catch (Exception e) {
			
		}
	}
	
	public ResultSet executeSelectQuery(String query) {
		ResultSet result = null;
		try {
			Statement stmt = Conn.createStatement();
			result = stmt.executeQuery(query);
		}
		catch(Exception e) {
			
		}
		return result;
	}
	
	public int executeNonSelectQuery(String query) {
		int result = 0;
		try {
			Statement stmt = Conn.createStatement();
			result = stmt.executeUpdate(query);
		}
		catch(Exception e) {
			
		}
		return result;
	}
}