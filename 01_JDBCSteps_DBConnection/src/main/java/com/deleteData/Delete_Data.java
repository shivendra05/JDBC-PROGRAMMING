package com.deleteData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete_Data {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Scanner sn = null;
		sn = new Scanner(System.in);
		Connection con = null;
		Statement st = null;

		System.out.println("Enter Movie Number to be deleted:: ");
		int movieNumber  = Integer.parseInt(sn.nextLine());

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "system";
		String password = "shiv";
		String deleteQuery =String.format("delete from movie where MNO=%d",movieNumber);

		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,userName,password);
		st = con.createStatement();

		int status = st.executeUpdate(deleteQuery);
		
		if(status==1) {
			System.out.println("Data deleted - Status :: "+status);
		}else {
			System.out.println("Unable to deleted Data - Status :: "+status);
		}
	}
}
