package cz.fjfi.pvs.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GetAllMoviesSophisticated {
	
	protected final static String SQL_QUERY = "select * from movies";
	
	public static List<String> getAllMovies(DBClient dbClient) throws SQLException{
		ResultSet rs = dbClient.executeQuery(SQL_QUERY);
		
		List<String> result = new LinkedList<String>();
		
		while (rs.next()) {
			result.add(rs.getString("NAME"));
		}
		
		rs.close();
		
		return result;
	}

}
