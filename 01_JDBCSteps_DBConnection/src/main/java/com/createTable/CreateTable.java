package com.createTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Statement st=null;
		Connection con = null;
		String createTable = "create table movie (mno number, title varchar(20), actor_Name varchar(40), budget number)";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "system";
		String password = "shiv";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,userName,password);
		st = con.createStatement();
		int status  = st.executeUpdate(createTable);
		if(status==1) {
			System.out.println("table created- Status :: "+status);
		}else {
			System.out.println("Unable to create table- Status :: "+status);
		}
	}
}