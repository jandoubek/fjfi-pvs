package cz.fjfi.pvs.parser;

import org.junit.Test;

public class TextSearchSophisticatedTest {
	
	
	@Test
	public void shouldFindTheEmailInText(){
				
		final String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
		
		String searchedText = "tahle adresa je ahoj@svete.info tak sem napiste";
		
		TextSearchSophisticated.search(searchedText, EMAIL_PATTERN);
				
	}
	
}
