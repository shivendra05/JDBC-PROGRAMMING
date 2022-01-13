package com.calleblestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class FindMovieBudget {
	public static void main(String[] args) throws Throwable {

		String driverName="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String userName="system";
		String password="shiv";
		int movieNumber = 0;
		Connection con=null;
		CallableStatement cs= null;
		Scanner sn=null;
		sn = new Scanner(System.in);
		
		System.out.println("Enter movie Number to get its Budget::");
		movieNumber = Integer.parseInt(sn.nextLine());
		
		Class.forName(driverName);
		con = DriverManager.getConnection(url,userName,password);
		cs = con.prepareCall("{call findMovieBudget(?,?)}");
		cs.setInt(1, movieNumber);
		
		cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		
		int getBudget = cs.getInt(2);
		System.out.println("Movie budget is::"+getBudget);
	}
}