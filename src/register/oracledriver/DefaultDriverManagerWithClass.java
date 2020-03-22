package register.oracledriver;

public class DefaultDriverManagerWithClass {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Registered Successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}