package com.GetAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Retrieve_All {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Scanner sn = null;
		sn = new Scanner(System.in);
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		boolean flag=false;

		System.out.println("Enter Movie Number to be deleted:: ");
		int movieNumber  = Integer.parseInt(sn.nextLine());

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "system";
		String password = "shiv";
		String selectQuery =String.format("Select * from movie where BUDGET>=%d",movieNumber);

		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,userName,password);
		st = con.createStatement();

		rs = st.executeQuery(selectQuery);
		while(rs.next()) {
			flag=true;
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4));
		}
		if(flag) {
		}else {
			System.out.println("Sorry !! Unable print Selected Data ");
		}
	}
}