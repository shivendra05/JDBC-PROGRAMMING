package statement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ExecuteUpdateUpdatePropertyFile {
	public static void main(String[] args) {
		Connection con=null;
		Statement stm = null;
		String driver;
		String url;
		String user;
		String password;
		String UpdateQuery;
		try {
			FileInputStream fis = new FileInputStream( new File("E:\\AdvanceJava_Practice\\JDBCDriverManager1\\src\\statement\\DB_OracleData.property"));
			Properties prop = new Properties();
			prop.load(fis);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			UpdateQuery= prop.getProperty("UpdateQuery");
			//load OracleDriver
			Class.forName(driver);
			//get connection with DB with getConnection function of DriverManager
			con = DriverManager.getConnection(url, user, password);
			//create statement
			stm=con.createStatement();
			//execute update query
			int count = stm.executeUpdate(UpdateQuery);
			if(count==1) {
				System.out.println("Table Data Update Sucessfully: "+count);
			}else {
				System.out.println("Unable to update Table Data: "+count);
			}
			//exception when file is missing at the provided location
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			//exception when file is unable to find the searched data in the input file
		}catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}catch(SQLException sql) {
			sql.printStackTrace();
		}
		finally {
			try {
				if(stm!=null) {
					stm.close();
				}
				if(con!=null) {
					con.close();
				}
			}catch(SQLException sql) {
				sql.printStackTrace();
			}
		}
	}
}