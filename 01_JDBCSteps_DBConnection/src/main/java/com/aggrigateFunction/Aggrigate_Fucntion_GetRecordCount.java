package com.aggrigateFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aggrigate_Fucntion_GetRecordCount {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "system";
		String password = "shiv";
		String aggrigateQuery ="Select count(mno)from movie";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,userName,password);
		st = con.createStatement();

		rs = st.executeQuery(aggrigateQuery);

		if(rs.next()) {
			System.out.println("Number of Records are available :: "+rs.getInt(1));
		}
	}
}