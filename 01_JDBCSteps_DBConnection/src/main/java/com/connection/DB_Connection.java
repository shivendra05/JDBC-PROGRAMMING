package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Connection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection con= null;
		DriverManager manager = null;
		Statement stm= null;
		ResultSet rs= null;

		//Class.forName("oracle.jdbc.driver.OracleDriver");
		con = manager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "shiv");
		stm = con.createStatement();
		rs = stm.executeQuery("select * from customer_details");
		System.out.println("************ DB Details Result *************");
		while (rs.next()) {
			System.out.println(rs.getInt(1)+" \t "+rs.getDouble(2)+" \t "+rs.getString(3)+" \t "+rs.getString(4));
		}
	}
}