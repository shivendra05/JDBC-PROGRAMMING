package callableStatement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class Function_SelectAllDataWithCursor {
	public static void main(String[] args)  {
		Connection con=null;
		CallableStatement cs=null;
		try {
			Scanner sn = new Scanner(System.in);
			int account;
			FileInputStream fis = new FileInputStream(new File("DBDetails.property"));
			Properties prop = new Properties();
			prop.load(fis);
			Class.forName(prop.getProperty("driver"));
			con=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
			System.out.println("Connection stablised sucessfully");
			System.out.println("Enter Account No.::-");
			account = sn.nextInt();
			/*
			 * create or replace function fselectAllDataWithCursor(acc number) return SYS_REFCURSOR is
			 	data SYS_REFCURSOR;
  				begin
				open data for select * from bank_account where accno<acc;
				return data;
 				end;
 				/    
			 * DML Command 		--returns-> false
			 * DDL Command 		--returns-> true
			 * Procedure Calls  --returns-> false 
			 * */
			
			/*call callableStatement --> syntex 
			 {SelectAllDataWithCursor(?,?)}
			 registerOutParameter(2, OracleTypes.CURSOR);
			 cs.setInt(1, account);
			 * */
			cs=con.prepareCall("{?=call  fselectAllDataWithCursor(?)}");
			//index should be same what we kept at registerOutPutParamater
			cs.setInt(2, account);
			//cs.registerOutParameter(2, Types.REF_CURSOR);//JDBC
			cs.registerOutParameter(1, OracleTypes.CURSOR);//JDBC
			
			System.out.println("Accunt Entered .. Procedure Execution Started::-");
			cs.execute();
			//index should be same what we kept at getObject()
			ResultSet rs= (ResultSet) cs.getObject(1);
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt("AGE")+" "+rs.getString(4)+" "+rs.getString("CITY"));
			}
		} catch (IOException| ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(cs!=null) {
					cs.close();
				}
				if(con!=null) {
					con.close();
				}
			}catch(SQLException sql) {
				sql.printStackTrace();
			}
		}
	}
}