package com.DataTransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMySQL {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection conOracle = null,conMySQl = null;
		Statement stOracle = null,stMySQl = null;
		ResultSet rsOracle = null;
		int countDataTranfered=0;
		
		//MYSQL Data Connection Data
		String urlMySQLS = "jdbc:mysql:///shivdata";
		String userNameMySQL = "root";
		String passwordMySQL = "shiv";

		//Oracle Data Connection Data
		String urlOracle = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userNameOracle = "system";
		String passwordOracle = "shiv";

		String selectDataQueryOracle ="Select * from movie";

		//oracle DB Connection
		conOracle = DriverManager.getConnection(urlOracle,userNameOracle,passwordOracle);
		stOracle = conOracle.createStatement();

		//MySQL DB Connectrion
		Class.forName("com.mysql.cj.jdbc.Driver");
		conMySQl = DriverManager.getConnection(urlMySQLS,userNameMySQL,passwordMySQL);
		stMySQl = conMySQl.createStatement();

		rsOracle = stOracle.executeQuery(selectDataQueryOracle);
		while (rsOracle.next()) {
			int mnum = rsOracle.getInt(1);
			String title= rsOracle.getString(2);
			String actors= rsOracle.getString(3);
			int budget= rsOracle.getInt(4);
			stMySQl.executeUpdate(String.format("insert into movie values(%d,'%s','%s',%d)", mnum,title,actors,budget));
			countDataTranfered++;
		}
		System.out.println("Number of Records Copied from Oracle to MySQL are :: "+countDataTranfered);
	}
}