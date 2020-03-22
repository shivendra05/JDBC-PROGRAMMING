package callableStatement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import java.util.Scanner;

public class Peocedure_SelectWithInOutParameter {
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
			 * create or replace procedure salary_selection(acc number, salary out number) is
  				begin
				select amount into salary from bank_account where accno=acc;
 				end;
 				/
			 * DML Command 		--returns-> false
			 * DDL Command 		--returns-> true
			 * Procedure Calls  --returns-> false 
			 * */
			
			/*call callableStatement --> syntex 
			 
			 {AccountAmount_update(?,?)}
			 registerOutParameter(1, TYPE.NEWMERIC);
			 * */
			cs=con.prepareCall("{call salary_selection(?,?)}");
			cs.setInt(1, account);
			cs.registerOutParameter(2, Types.NUMERIC);
			System.out.println("Accunt Entered .. Procedure Execution Started::-");
			cs.execute();
			//index shopuld be same what we kept at registerOutPutParamater
			int sal=cs.getInt(2);
			System.out.println("Accont "+account+" have amount: "+sal);
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