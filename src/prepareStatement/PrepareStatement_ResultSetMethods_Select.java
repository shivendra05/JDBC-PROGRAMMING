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

public class PrepareStatement_ResultSetMethods_Select {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs;
		Scanner sn=null;
		String driver;
		String url;
		String user;
		String password;
		String Select_ResultSetAbsoluteQuery;
		int rno;
		try {
			sn=new Scanner(System.in);
			System.out.println("Enter row number:-");
			rno = sn.nextInt();
			File file=new File("DBDetails.property");
			FileInputStream fis = new FileInputStream(file);
			Properties prop=new Properties();
			prop.load(fis);
			driver = prop.getProperty("driver");
			url =prop.getProperty("url");
			user =prop.getProperty("user");
			password=prop.getProperty("password");
			Select_ResultSetAbsoluteQuery=prop.getProperty("Select_ResultSetAbsoluteQuery");
			//load OracleDriver class
			Class.forName(driver);
			//create connection with DB
			con=DriverManager.getConnection(url, user, password);
			//PrepareStatement
			ps=con.prepareStatement(Select_ResultSetAbsoluteQuery,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			boolean rowfound = rs.absolute(rno);
			//select accno, custname, gender, city, amount from bank_account
			if(rowfound==true) {
				System.out.println(rs.getInt(1)+ " "+rs.getString(2)+ " "+rs.getString(3)+ " "+rs.getString(4));
			}else {
				System.out.println("Enter valid Row");
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