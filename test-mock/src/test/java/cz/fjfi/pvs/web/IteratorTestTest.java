package cz.fjfi.pvs.web;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class IteratorTestTest {
	
	private String aa = "one";
	
	private static class SomeClass{
		protected static String someClassString;
	}
	
	@Test
	public void crashOnCall(){
		Set<String> set = new HashSet<String>();		
		set.add(aa);
		List<String> mockedList = mock(List.class);
		when(mockedList.contains(aa)).thenReturn(true);
		SomeClass.someClassString = aa;
		
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String a = iterator.next();
			iterator.remove();			
		}
	}

}
