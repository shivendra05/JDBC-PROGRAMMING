package callableStatement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Peocedure_UpdateWithInParameter {
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
			System.out.println("Enter Account No. for data update::-");
			account = sn.nextInt();
			/*
			 * create or replace procedure AccountAmount_update(dno number) is
  				begin
				update bank_account set amount=amount+50 where amount<10000;
 				end;
 				/
			 * DML Command 		--returns-> false
			 * DDL Command 		--returns-> true
			 * Procedure Calls  --returns-> false 
			 * */
			/*call callableStatement --> syntex 
			 {AccountAmount_update(?)}
			 * */
			cs=con.prepareCall("{call AccountAmount_update(?)}");
			cs.setInt(1, account);
			System.out.println("Accunt Entered .. Procedure Execution Started::-");
			boolean status = cs.execute();
			System.out.println("Data Update Sucessfully:!!!"+status);
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