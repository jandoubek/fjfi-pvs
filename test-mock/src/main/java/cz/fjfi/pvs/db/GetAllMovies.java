package cz.fjfi.pvs.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAllMovies {
	
	public static void displayAllMovies() throws SQLException{
		
		DBClient dbClient = new DBClient();
		
		ResultSet rs = dbClient.executeQuery("select * from movies");
		
		while (rs.next()) {
			System.out.println(rs.getString("NAME"));
		}
	}

}
