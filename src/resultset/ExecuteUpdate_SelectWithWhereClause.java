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
import java.util.Scanner;

public class ExecuteUpdate_SelectWithWhereClause {
	public static void main(String[] args) {
		Connection con=null;
		Statement stm=null;
		ResultSet rs=null;
		String driver;
		String url;
		String user;
		String password;
		String select_WhereClause;
		int deptno;
		String ename;
		try {
			Scanner sn= new Scanner(System.in);
			File file =new File("DBDetails.property");
			FileInputStream fis = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fis);
			System.out.println("Reading property file: ");
			driver=prop.getProperty("driver");
			url=prop.getProperty("url");
			user=prop.getProperty("user");
			password=prop.getProperty("password");
			System.out.println("Reading property file completed: ");
			System.out.println("Enter EmpName and deprtment Number:- ");
			ename=sn.next();
			deptno=sn.nextInt();
			select_WhereClause="select dname from dept where dno="+deptno+ " and empname="+ename;
			//load he OracleDriver Class
			Class.forName(driver);
			//Create connection with DB
			con=DriverManager.getConnection(url, user, password);
			//create statement
			stm=con.createStatement();
			//executeQuery
			rs = stm.executeQuery(select_WhereClause);
			System.out.println("----------------------------------");
			System.out.println("Inputs: "+ename+" and "+deptno+" ");
			while(rs.next()) {
				String dname=rs.getString(1);
				System.out.println("Output DeptName: "+dname);
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