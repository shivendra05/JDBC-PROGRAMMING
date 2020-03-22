package statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExceuteUpdateInsertPropertyFile{
	public static void main(String[] args) {
		Connection con = null;
		Statement stm = null;
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="system";
		String pws="shiv";
		String query = "insert into dept values(6,'PM','Laksmi')";
		try {
			//load Oracle driver using forName static forName factory method
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pws);
			//initialize Statement object.
			stm = con.createStatement();
			//execute query by using executeUpdate().
			int count = stm.executeUpdate(query);
			if(count==1) {
				System.out.println("Data Inserted Sucessfuly");
			}else {
				System.out.println("Failed to Insert Data");
			}
			//handle getConnection interface exception if it throws
		} catch (SQLException e) {
			e.printStackTrace();
			//handle OracleDriver interface exception if it throws
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				//close Statement object
				if(stm!=null) {
					stm.close();
				}
				//close Connection object
				if(con!=null) {
					con.close();
				}
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}