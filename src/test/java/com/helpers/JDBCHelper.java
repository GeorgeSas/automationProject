package com.helpers;

//STEP 1. Import required packages
import java.sql.*;

public class JDBCHelper {

	private Connection conn = null;

	public void connectToDB(String jdbcDriver, String dbUrl, String username, String password) throws ClassNotFoundException, SQLException {
		// STEP 2: Register JDBC driver
		Class.forName(jdbcDriver);

		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(dbUrl, username, password);
	}

	public ResultSet executeQuerySQL(String query) throws SQLException {
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		Statement stmt = conn.createStatement();

		return stmt.executeQuery(query);
	}
	
	public void executeSQL(String query) throws SQLException {
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		Statement stmt = conn.createStatement();

		stmt.execute(query);
	}

	/* - this should be removed after the helper is used from step classes
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/adrese";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		connectToDB(JDBC_DRIVER, DB_URL, USER, PASS);
		
		ResultSet rs = executeQuerySQL("SELECT * FROM user");

		// STEP 5: Extract data from result set
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String email = rs.getString("email");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", username: " + username);
			System.out.print(", password: " + password);
			System.out.println(", email: " + email);
		}
		
		 executeSQL("INSERT INTO user (`username`, `password`, `email`) VALUES ('gheorghe', 'ion', 'mailu')");
		 
		 executeSQL("UPDATE user SET `password`='nimic' WHERE `username`='gheorghe'");
		 
		 executeSQL("DELETE FROM user WHERE `username`='gheorghe'");

		System.out.println("Goodbye!");
	}*/
}
