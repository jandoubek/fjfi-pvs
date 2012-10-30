package cz.fjfi.pvs.parser;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlSearch {

	private static String ANCHOR_PATTERN = "<a.*?href=[\"\"'](.*?)[\"\"'].*?>(.*?)</a>";
	public static Set<String> getUrls(String htmlPage){
		
		Pattern pattern = Pattern.compile(ANCHOR_PATTERN);
		Matcher matcher = pattern.matcher(htmlPage);
		Set<String> result = new HashSet<String>();
		while (matcher.find()) {			
			result.add(matcher.group(1));
		}
		
		return result;		
		
	}
	
}
