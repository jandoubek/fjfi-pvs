package cz.fjfi.pvs.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import cz.fjfi.pvs.parser.EmailSearch;
import cz.fjfi.pvs.parser.UrlSearch;
import cz.fjfi.pvs.threads.ProducerConsumerLogging;

public class EmailCrawlerSophisticated {
	
	static Logger logger = Logger.getLogger(ProducerConsumerLogging.class);
	
	protected static String rootUrl;
	
	protected static Set<String> alreadyVisitedUrls;
	
	protected static Set<String> notVisitedUrls;
	
	protected static Set<String> foundUrlsOnPage;
	
	protected static Set<String> emails;
	
	protected static WebClient webClient;
		
	public static void main(String[] args){
		
//		String rootUrl = "http://www.evald.cz/";
//		String rootUrl = "http://www.lucerna.cz/kino.php";
//		String rootUrl = "http://www.biooko.net/cz/";
//		String rootUrl = "http://www.biooko.net/cz/static/kontakt/";
		
		if (args.length == 1) {
			rootUrl = args[0];
		}
		
		if (rootUrl == null) {
			loadProperties();
		}
		
		initGlobalVariables();
		
		crawlForEmails();
		
		printResults();
		
	}

	protected static void loadProperties() {
		Properties props = new Properties();
		try {
			props.load(EmailCrawlerSophisticated.class.getResourceAsStream("../../../../emailCrawlerProperties.properties"));
			rootUrl = props.getProperty("rootUrl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static void initGlobalVariables() {
		notVisitedUrls = new HashSet<String>();
		alreadyVisitedUrls = new HashSet<String>();
		foundUrlsOnPage = new HashSet<String>();
		notVisitedUrls.add(rootUrl);
		webClient = new PureJavaWebClient();
		emails = new HashSet<String>();
	}	
	
	protected static void crawlForEmails(){
		while (!notVisitedUrls.isEmpty()) {			
			for (Iterator<String> it = notVisitedUrls.iterator(); it.hasNext();) {
				String processedUrl = it.next(); 
				processOneUrl(processedUrl);
				it.remove();								
			}
			notVisitedUrls.addAll(foundUrlsOnPage);
			foundUrlsOnPage.clear();
		}
	}
	
	protected static void printResults(){
		logger.info("Printing output");
		for (String email : emails) {
			System.out.println(email);
		}
	}	
	
	protected static void processOneUrl(String nowVisiting){
		try {
			//Get the html code
			String urlsHtml = webClient.getUrl(nowVisiting);
			logger.info("Now visiting: " + nowVisiting);
			alreadyVisitedUrls.add(nowVisiting);
					
			//Search for urls on page
			Set<String> urlsOnPage = UrlSearch.getUrls(urlsHtml);
			Set<String> urlsFoundOnCurrentPage = getValidUrlsToVisitNext(urlsOnPage);
			logger.info("Found following urls to visit: " + urlsFoundOnCurrentPage.toString());
			foundUrlsOnPage.addAll(urlsFoundOnCurrentPage);			
			
			List<String> foundEmails = EmailSearch.getEmails(urlsHtml);
			logger.info("Found following emails: " + foundEmails);
			emails.addAll(foundEmails);			
			
		} catch (MalformedURLException e) {					 
			e.printStackTrace();
		} catch (IOException e) {					 
			e.printStackTrace();
		}
	}
	
	private static Set<String> getValidUrlsToVisitNext(Set<String> urls){
		Set<String> discoveredUrls = new HashSet<String>();		
		for (String urlCandidate : urls) {
			if(urlCandidate.contains(rootUrl)){
				if(!alreadyVisitedUrls.contains(urlCandidate)){
					discoveredUrls.add(urlCandidate);
				}
			}
		}		
		return discoveredUrls;
	}
	
}
