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

public class Function_SelectWithInParam {
	public static void main(String[] args)  {
		Connection con=null;
		CallableStatement cs=null;
		Scanner sn;
		try {
			sn=new Scanner(System.in);
			FileInputStream fis = new FileInputStream(new File("DBDetails.property"));
			Properties prop = new Properties();
			prop.load(fis);
			Class.forName(prop.getProperty("driver"));
			con=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
			System.out.println("Connection stablised sucessfully");
			System.out.println("Enter Account No.::-");
			int account = sn.nextInt();
			/*
			create or replace function fsalaryAcc_selection(acc number) return number is
			 	salary number;
  				begin
				select amount into salary from bank_account where accno=acc;
        		return salary;
 				end;
 				/    
			 * DML Command 		--returns-> false
			 * DDL Command 		--returns-> true
			 * Procedure Calls  --returns-> false 
			 * */
			
			/*call callableStatement --> syntex 
			 
			 { ? = call fsalary_selection(?)}
			 cs.setInt(2, account);
			cs.registerOutParameter(1, Types.NUMERIC);
			 * */
			cs=con.prepareCall("{ ? = call fsalaryAcc_selection(?)}");
			cs.registerOutParameter(1, Types.NUMERIC);
			cs.setInt(2, account);
			System.out.println("Accunt Entered .. function Execution Started::-");
			cs.execute();
			//index shopuld be same what we kept at registerOutPutParamater
			int sal=cs.getInt(1);
			System.out.println("Accont 1001 have amount: "+sal);
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