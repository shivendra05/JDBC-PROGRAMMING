package statement;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExecuteUpdateCreate {
	public static void main(String[] args) {
		Connection con=null;
		Statement stm = null;
		try {
			String driver="oracle.jdbc.driver.OracleDriver";
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user="system";
			String password="shiv";
			String query="create table dept(dno number,dname varchar(10),empname varchar(20))";
			//String query="create table depart(dno number,dname varchar(10),empname varchar(20))";
			//Load OracleDriver with static factory method forName
			Class.forName(driver);
			//create connection with getConnection---> enter url, usr and pws
			con = DriverManager.getConnection(url, user, password);
			//create statement object
			stm = con.createStatement();
			int count = stm.executeUpdate(query);
			if(count==0) {
				System.out.println("Table created Sucessfully: "+count);
			}else {
				System.out.println("Unable to create Table: "+count);
			}
			//Handling OracleDriver exception 
		}catch(ClassNotFoundException clf) {
			clf.printStackTrace();
			//Handling getconnection/createStatement/executeUpdate exception
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if(stm!=null) {
					stm.close();
				}if(con!=null) {
					con.close();
				}//Handling con/stm exception
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}