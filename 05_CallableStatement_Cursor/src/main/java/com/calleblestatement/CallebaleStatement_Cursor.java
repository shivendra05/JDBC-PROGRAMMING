package com.calleblestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.sql.Types;

import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

public class CallebaleStatement_Cursor{
	public static void main( String[] args ) throws Throwable  {
		Connection con=null;
		CallableStatement cs = null;

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName = "system";
		String password = "shiv";
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,userName,password);
		cs = con.prepareCall("{call getMovieDetails(?)}");
		cs.registerOutParameter(1, OracleTypes.CURSOR);

		cs.executeUpdate();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" -- "+rs.getString(2)+" -- "+rs.getString(3)+" -- "+rs.getInt(4));
		}
		System.out.println("Connection Closed");
	}
}