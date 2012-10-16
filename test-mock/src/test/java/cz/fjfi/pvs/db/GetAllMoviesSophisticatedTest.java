package cz.fjfi.pvs.db;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class GetAllMoviesSophisticatedTest {
	
	@Test
	public void shouldWriteOneMovie() throws SQLException{
		
		ResultSet rs = mock(ResultSet.class);
		
		DBClient dbClient = mock(DBClient.class);
		
		when(dbClient.executeQuery(GetAllMoviesSophisticated.SQL_QUERY)).thenReturn(rs);
		when(rs.next()).thenReturn(true, false);
		when(rs.getString("NAME")).thenReturn("School bang");
				
		Assert.assertTrue(GetAllMoviesSophisticated.getAllMovies(dbClient).contains("School bang"));	
		
		verify(rs).close();
	}

}
