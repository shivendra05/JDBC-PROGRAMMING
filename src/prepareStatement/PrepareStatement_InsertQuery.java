package prepareStatement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class PrepareStatement_InsertQuery {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps=null;
		Scanner sn=null;
		String driver;
		String url;
		String user;
		String password;
		String Insert_Query;
		String ename;
		String dname;;
		int dno;
		try {
			File file=new File("DBDetails.property");
			FileInputStream fis = new FileInputStream(file);
			Properties prop=new Properties();
			prop.load(fis);
			driver = prop.getProperty("driver");
			url =prop.getProperty("url");
			user =prop.getProperty("user");
			password=prop.getProperty("password");
			Insert_Query=prop.getProperty("InsertQuery");
			//load OracleDriver class
			Class.forName(driver);
			//create connection with DB
			con=DriverManager.getConnection(url, user, password);
			//PrepareStatement
			ps=con.prepareStatement(Insert_Query);
			String choice= "Yes";
			sn= new Scanner(System.in);
			//insert values into DB unless choice==No
			while(choice.equalsIgnoreCase("Yes")){
				System.out.println("Enter Dno, DepatName, EmpName: ");
				dno =Integer.parseInt(sn.next());
				dname =sn.next();
				ename =sn.next();
				ps.setInt(1, dno);
				ps.setString(2, dname);
				ps.setString(3, ename);
				ps.executeUpdate();
				System.out.println("Data Added Sucessfully!!!");
				System.out.println("-----------------------------------");
				System.out.println("Do you wish to add new User!! Yes/No: ");
				choice =sn.next();
			}
			System.out.println("Data Insertion Completed Sucessfully!! Thanks !!");
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