package com.aggrigateFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aggrigate_Fucntion_GetMaxBudgetMovie {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection con = null;
		Statement st = null;
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "system";
		String password = "shiv";
		String aggrigateQuery ="Select max(budget)from movie";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,userName,password);
		st = con.createStatement();

		int count = st.executeUpdate(aggrigateQuery);

		System.out.println("Number of Records are available :: "+count);
	}
}