package resultset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ExecuteQuery_Select_GetSet {
	public static void main(String[] args) {
		String driver;
		String url;
		String user;
		String password;
		String SelectQuery;
		Connection con=null;
		Statement stm=null;
		ResultSet rs=null;
		try {
			System.out.println("Load Property File.....");
			File file = new File("DBDetails.property");
			FileInputStream fis = new FileInputStream(file);
			Properties prop=new Properties();
			prop.load(fis);
			System.out.println("Read Data from Property File statred: ");

			driver=prop.getProperty("driver");
			url=prop.getProperty("url");
			user=prop.getProperty("user");
			password=prop.getProperty("password");
			SelectQuery=prop.getProperty("SelectQuery");

			System.out.println("Reading Data from Property file Completed: ");
			//get OracleDriver
			Class.forName(driver);
			//get getconnection
			con=DriverManager.getConnection(url, user, password);
			//create statement
			stm=con.createStatement();
			//to execute selectQuery Result provides a method executeQuery and returns ResultSet(Set of Values)
			rs=stm.executeQuery(SelectQuery);
			System.out.println("----------------------------------");
			//Read and get Data from ResultSet
			while(rs.next()) {
				int dno = rs.getInt(1);
				String dname=rs.getString(2);
				String empname = rs.getString(3);
				System.out.println(dno+"\t"+dname+"\t"+empname);
			}
		}catch(FileNotFoundException fis) {
			fis.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}catch(SQLException sql) {
			sql.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) {
					rs.close();
				}
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