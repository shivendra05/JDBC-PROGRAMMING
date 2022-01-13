package com.calleblestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class CallebaleStatement{
	public static void main( String[] args ) throws Throwable  {
		Connection con=null;
		CallableStatement cs = null;

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "system";
		String password = "shiv";
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,userName,password);
		cs = con.prepareCall("{call squar_root(?,?)}");
		cs.setInt(1, 120);
		cs.registerOutParameter(2, Types.INTEGER);

		cs.executeUpdate();
		int values = cs.getInt(2);
		
		System.out.println(values);
		
		System.out.println("Connection Closed");
	}
}