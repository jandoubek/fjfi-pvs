package cz.fjfi.pvs.parser;

import java.util.List;

public class EmailSearch {
	
	private static String EMAIL_PATTERN = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
	
	public static List<String> getEmails(String text) {
		return TextSearchSophisticated.searchAndReturn(text, EMAIL_PATTERN);
	}
	
}
