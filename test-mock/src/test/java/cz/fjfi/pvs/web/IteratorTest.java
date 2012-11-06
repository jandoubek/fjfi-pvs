package cz.fjfi.pvs.web;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IteratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		String aa = "one";		
		set.add(aa);
		set.add("third");
		
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String a = iterator.next();
			set.add("second");
			iterator.remove();			
		}

	}

}
