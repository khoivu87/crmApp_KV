/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connect MySQL (running in docker)
 * Port: 3306
 */
public class MysqlConfig {
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/crm_app";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Kh12.v3@C4b5rs6ft";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER); 
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(ClassNotFoundException exception) {
			exception.printStackTrace();
		}catch(SQLException exception) {
			exception.printStackTrace();
		}
		
		return null;
	}
}
