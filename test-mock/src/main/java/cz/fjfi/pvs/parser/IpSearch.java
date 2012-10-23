package cz.fjfi.pvs.parser;

import java.util.List;

public class IpSearch {
	
	private static String IP_PATTERN = "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}";
	
	public static List<String> getIps(String text) {
		return TextSearchSophisticated.searchAndReturn(text, IP_PATTERN);
	}
	
}
