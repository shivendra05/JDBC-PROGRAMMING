package resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class getFetchSize_setFetchSzie {
	public static void main(String[] args) {
		Connection con=null;
		Statement stm =null;
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="system";
		String password="shiv";
		try {
			//load OracleDriver class
			Class.forName(driver);
			//get connection
			con=DriverManager.getConnection(url,user,password);
			//create statement
			stm=con.createStatement();
			//getFetchSize
			System.out.println("Get Default FetchSize: "+stm.getFetchSize());
			System.out.println("Get Max Default MaxRow: "+stm.getMaxRows());
			System.out.println("Get Default Statement Fetch Direction: "+stm.getFetchDirection());

			stm.setFetchSize(20);
			stm.setMaxRows(40);

			System.out.println("Get Default FetchSize: "+stm.getFetchSize());
			System.out.println("Get Max Default MaxRow: "+stm.getMaxRows());
			System.out.println("Get Default Statement Fetch Direction: "+stm.getFetchDirection());
		}catch(ClassNotFoundException cnf) {
			System.out.println(cnf.getMessage());
		}catch(SQLException sql) {
			System.out.println(sql.getMessage());
		}
		finally {
			try {
				if(stm!=null) {
					stm.close();
				}
				if(con!=null) {
					stm.close();
				}
			}catch(SQLException sql) {
				sql.getStackTrace();
			}
		}
	}
}