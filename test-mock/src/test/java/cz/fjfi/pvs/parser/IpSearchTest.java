package cz.fjfi.pvs.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class IpSearchTest {
	
	
	@Test
	public void shouldFindTheIpsInText(){
				
		String searchedText = "tahle adresa je 192.168.1.111 tak sem napiste";
		
		List<String> result = IpSearch.getIps(searchedText);
		
		Assert.assertTrue(result.contains("192.168.1.111"));
				
	}
	
}
