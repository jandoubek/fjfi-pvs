package cz.fjfi.pvs.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EmailSearchTest {
	
	
	@Test
	public void shouldFindTheEmailInText(){
				
		String searchedText = "tahle adresa je ahoj@svete.info tak sem napiste";
		
		List<String> result = EmailSearch.getEmails(searchedText);
		
		Assert.assertTrue(result.contains("ahoj@svete.info"));
				
	}
	
}
