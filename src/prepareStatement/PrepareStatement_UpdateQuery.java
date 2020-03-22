package prepareStatement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class PrepareStatement_UpdateQuery {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs,rs1;
		Scanner sn=null;
		String driver;
		String url;
		String user;
		String password;
		String Update_Add_AmountQuery;
		String Update_Sub_AmountQuery;
		String Select_AmountQuery;
		int accno;
		int amount;
		boolean amountDeducted=false;
		int avail_amount = 0;
		try {
			sn= new Scanner(System.in);
			File file=new File("DBDetails.property");
			FileInputStream fis = new FileInputStream(file);
			Properties prop=new Properties();
			prop.load(fis);
			driver = prop.getProperty("driver");
			url =prop.getProperty("url");
			user =prop.getProperty("user");
			password=prop.getProperty("password");
			Update_Add_AmountQuery= prop.getProperty("Update_Add_AmountQuery");
			Update_Sub_AmountQuery=prop.getProperty("Update_Sub_AmountQuery");
			Select_AmountQuery=prop.getProperty("Select_AmountQuery");

			//load OracleDriver class
			Class.forName(driver);
			//create connection with DB
			con=DriverManager.getConnection(url, user, password);
			//PrepareStatement
			//update bank_account set amount= amount-? where accno=?
			System.out.println("Please enter your choice: debit/credit:-");
			String choice= sn.next();
			System.out.println("Enter amount.: ");
			amount =Integer.parseInt(sn.next());
			System.out.println("Enter your account No.: ");
			accno =Integer.parseInt(sn.next());
			if(choice.equalsIgnoreCase("debit")) {
				ps1=con.prepareStatement(Select_AmountQuery);
				//ps1=con.createStatement(Select_AmountQuery);
				ps1.setInt(1, accno);
				rs1=ps1.executeQuery();
				rs1.next();
				avail_amount= rs1.getInt(1);
				System.out.println("Account: "+accno+" have Total  amount-->"+avail_amount);
				if(avail_amount<=amount) {
					System.out.println("No Enough Balance!! Sorry Try Again"); 
					ps=con.prepareStatement(Update_Sub_AmountQuery);
					amount=0;
				}else {
					ps=con.prepareStatement(Update_Sub_AmountQuery); 
					amountDeducted=true;
				}
			}else if(choice.equalsIgnoreCase("credit")) {
				ps=con.prepareStatement(Update_Add_AmountQuery);
				amountDeducted=true;
			}
			ps.setInt(1, amount);
			ps.setInt(2, accno);
			int updateCount = ps.executeUpdate();
			if(amountDeducted==true) {
				System.out.println("Transaction Sucessfully!!!");
			}else{
				System.out.println("Transaction Failed!!!");	
			}
		}catch(IOException | ClassNotFoundException |SQLException fis) {
			fis.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
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