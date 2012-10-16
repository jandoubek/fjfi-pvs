package cz.fjfi.pvs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Jan Doubek
 *
 */

public class DBClient {
	
	public ResultSet executeQuery(String query){
		String connectionURL = "jdbc:postgresql://localhost:5432/movies;user=java;password=samples";
		// Change the connection string according to your db, ip, username and
		// password

		Connection con = null;
		ResultSet rs = null;
		try {

			// Load the Driver class.
			Class.forName("org.postgresql.Driver");
			// If you are using any other database then load the right driver
			// here.

			// Create the connection using the static getConnection method
			con = DriverManager.getConnection(connectionURL);

			// Create a Statement class to execute the SQL statement
			Statement stmt = con.createStatement();

			// Execute the SQL statement and get the results in a Resultset
			rs = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				con.close();
			} catch (SQLException e) {				 
				e.printStackTrace();
			}
		}
		
		return rs;
	}	

}
