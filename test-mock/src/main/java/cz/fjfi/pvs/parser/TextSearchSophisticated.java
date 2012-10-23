package cz.fjfi.pvs.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://www.dreamincode.net/code/snippet1761.htm
 * 
 * found here and modified
 * 
 * @author Jan Doubek
 * 
 */

public class TextSearchSophisticated {

	public static String readFile(String fileName) {
		File file = new File(fileName);
		FileReader ins = null;
		BufferedReader bf;

		StringBuffer sb = new StringBuffer();

		try {
			ins = new FileReader(file);
			bf = new BufferedReader(ins);

			while (bf.ready()) {
				sb.append(bf.readLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	public static void search(String searchedText, String patternString) {
		try {

			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(searchedText);

			while (matcher.find()) {
				System.out.println("The pattern starts at "
						+ (matcher.start() + 1) + " and ends at "
						+ (matcher.end() + 1));
			}
		} catch (Exception e) {
		}
	}

	public static List<String> searchAndReturn(String searchedText,	String patternString) {

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(searchedText);
		List<String> result = new LinkedList<String>();
		while (matcher.find()) {
			result.add(matcher.group());
		}
		
		return result;
		
	}

}