package statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteUpdateDrop {
	public static void main(String[] args) {
		Connection con=null;
		Statement stm=null;
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="system";
		String pws="shiv";
		String query = "drop table depart";
		try {
			//load OracleDriver
			Class.forName(driver);
			//initialize Connection obj
			con=DriverManager.getConnection(url,user,pws);
			//initialize Statement obj
			stm=con.createStatement();
			//execute drop query
			int count = stm.executeUpdate(query);
			System.out.println("Table Dropped Sucessfully: "+count);
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
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
				sql.printStackTrace();
			}
		}
	}
}