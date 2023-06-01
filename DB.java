package labDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
      
import javax.swing.table.TableModel;

public class DB {
	private static Connection con;

	public static Connection getConnection() {
		// step1 load the driver class
		try {
			System.out.println("..............................");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("..............................");

			// step2 create the connection object
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "TECHACAD", "sahana");
			System.out.println("Database Connected");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}
	
	public static void main(String args[]){
		getConnection();
	}

}


