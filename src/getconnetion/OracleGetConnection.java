package getconnetion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleGetConnection {
	public static void main(String[] args) {
		try {
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user="system";
			String password="shiv";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println(con);
			System.out.println("Connection establised Sucessfuly");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}