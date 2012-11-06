package cz.fjfi.pvs.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cz.fjfi.pvs.parser.EmailSearch;
import cz.fjfi.pvs.parser.UrlSearch;

public class EmailCrawler {
	
	private static String rootUrl;
	
	private static Set<String> alreadyVisitedUrls;
	
	private static Set<String> notVisitedUrls;
		
	public static void main(String[] args){
		rootUrl = args[0];
//		String rootUrl = "http://www.evald.cz/";
//		String rootUrl = "http://www.lucerna.cz/kino.php";
//		String rootUrl = "http://www.biooko.net/cz/";
//		String rootUrl = "http://www.biooko.net/cz/static/kontakt/";
						
		Set<String> notVisitedUrls = new HashSet<String>();
		Set<String> alreadyVisitedUrls = new HashSet<String>();
		notVisitedUrls.add(rootUrl);
		
		Set<String> emails = new HashSet<String>();
		
		PureJavaWebClient javaClient = new PureJavaWebClient();
		
		while (!notVisitedUrls.isEmpty()) {
			Set<String> discoveredUrls = new HashSet<String>();
			for (Iterator<String> it = notVisitedUrls.iterator(); it.hasNext();) {
				String nowVisiting = (String) it.next();
				
				try {
					//Get the html code
					String urlsHtml = javaClient.getUrl(nowVisiting);
//					System.out.println(nowVisiting);
					alreadyVisitedUrls.add(nowVisiting);
					it.remove();
					//Search for urls on page
					Set<String> anchoredUrls = UrlSearch.getUrls(urlsHtml);
//					System.out.println(anchoredUrls);
					//Validate them and keep them for next searches
					
					//Search the HTML for emails
					emails.addAll(EmailSearch.getEmails(urlsHtml));
					
					discoveredUrls = getValidUrlsToVisit(anchoredUrls);
					
				} catch (MalformedURLException e) {					 
					e.printStackTrace();
				} catch (IOException e) {					 
					e.printStackTrace();
				}				
			}
			notVisitedUrls.addAll(discoveredUrls);
		}
		
		for (String email : emails) {
			System.out.println(email);
		}
		
	}
	
	private static Set<String> getValidUrlsToVisit(Set<String> anchoredUrls){
		Set<String> discoveredUrls = new HashSet<String>();
		
		for (String anchoredURL : anchoredUrls) {
			if(anchoredURL.contains(rootUrl)){
				if(!alreadyVisitedUrls.contains(anchoredURL)){
					discoveredUrls.add(anchoredURL);
				}
			}
		}
		
		return discoveredUrls;
	}
}
