package register.oracledriver;

import oracle.jdbc.driver.OracleDriver;

public class DefaultDriverManager {
	public static void main(String[] args) {
/*
 * OracleDriver class initializes, OracleDriver in static block
 * and static block initializes all its static variables as class loads
 * So we dont need to call registerDriver. Since OracleDriver already been initializes driver by default with static block
 * */
		OracleDriver driver = new OracleDriver();
		System.out.println(driver);
	}
}