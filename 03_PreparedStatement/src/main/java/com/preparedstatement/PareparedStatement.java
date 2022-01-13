package com.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PareparedStatement{
	public static void main( String[] args ) throws Throwable  {
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs= null;
		Scanner sn = null;
		int budget =0;
		
		sn =  new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "system";
		String password = "shiv";
		String selectQuery = "Select * from movie where budget>=?";
		System.out.println("Enter Budget condition for query::");

		//Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,userName,password);
		ps = con.prepareStatement(selectQuery);
		budget = Integer.parseInt(sn.nextLine());
		ps.setInt(1, budget);
		rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
		}

		rs.close();
		con.close();
		System.out.println("Connection Closed");
	}
}
