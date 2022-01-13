package com.calleblestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class FindMovieBudget_MovieName {
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
		cs = con.prepareCall("{call findMovieDetailsUsingNumber(?,?,?,?)}");
		cs.setInt(1, movieNumber);
		
		cs.registerOutParameter(2, Types.INTEGER);
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.registerOutParameter(4, Types.INTEGER);
		cs.execute();
		
		int getBudget = cs.getInt(2);
		String mvnName = cs.getString(3);
		int DBNumberMovies = cs.getInt(4);
		System.out.println("Movie budget "+getBudget+" for Movie " +mvnName+" and have total "+DBNumberMovies);
	}
}