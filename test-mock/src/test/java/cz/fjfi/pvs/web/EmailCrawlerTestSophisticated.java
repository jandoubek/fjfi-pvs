package cz.fjfi.pvs.web;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class EmailCrawlerTestSophisticated {
	
	@Test
	public void liveTest(){
		
		EmailCrawlerSophisticated.rootUrl = "http://www.biooko.net/cz/static/kontakt/";;
		EmailCrawlerSophisticated.initGlobalVariables();
		
		EmailCrawlerSophisticated.crawlForEmails();
		
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("info@shortcat.cz"));
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("pokladna@biooko.net"));
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("martin@svihla.net"));
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("olja@biooko.net"));
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("jakub@aerofilms.cz"));
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("martin@biooko.net"));
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("jiri.sebesta@kinoaero.cz"));
	}
	
		
	
	@Test
	public void validniUrlka() throws MalformedURLException, IOException{
		String testUrl = "http://www.testurl.cz";
		
		EmailCrawlerSophisticated.rootUrl = testUrl;
		EmailCrawlerSophisticated.initGlobalVariables();
		
		String rootPageHtml = loadStringFromFile("src/test/resources/rootPage.htm");
		String secondPage = loadStringFromFile("src/test/resources/pronajem.aspx");
		String thirdPage = loadStringFromFile("src/test/resources/evaldschorm.aspx");
		
		WebClient wc = mock(WebClient.class);
		EmailCrawlerSophisticated.webClient = wc;	
		when(wc.getUrl(testUrl)).thenReturn(rootPageHtml);
		when(wc.getUrl("http://www.testurl.cz/pronajem.aspx")).thenReturn(secondPage);
		when(wc.getUrl("http://www.testurl.cz/evaldschorm.aspx")).thenReturn(thirdPage);
		
		EmailCrawlerSophisticated.crawlForEmails();	
		
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("dotazy@seznam.cz"));
		Assert.assertTrue("Tento email není nalezen i když se na stránce nachází", EmailCrawlerSophisticated.emails.contains("evald@evald.cz"));
		
	}
	
	private static String loadStringFromFile(String filename){
		String output = "";
		try {
			Scanner sc = new Scanner(new File(filename));
			output = sc.useDelimiter("\\Z").next();
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return output;
	}
	
	@Test
	public void testPropertiesLoader(){
		EmailCrawlerSophisticated.loadProperties();
		Assert.assertTrue(EmailCrawlerSophisticated.rootUrl != null);
	}

}
