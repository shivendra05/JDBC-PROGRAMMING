package register.oracledriver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import oracle.jdbc.driver.OracleDriver;

public class RegisterNewDriverManager {
	public static void main(String[] args) {
		try {
			OracleDriver driver = new OracleDriver();
			/*
			 * OracleDriver class initializes, OracleDriver in static block
			 * and static block initializes all its static variables as class loads
			 * 
			 * again with the help of registerDriver, We are initializing one more driver.
			 * So we will be seeing 2 OracleDriver objs 
			 * */
			DriverManager.registerDriver(driver);
			Enumeration enume = DriverManager.getDrivers();
			while(enume.hasMoreElements()) {
				System.out.println(enume.nextElement());
			}
			//DriverManager throws SQLException 
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
