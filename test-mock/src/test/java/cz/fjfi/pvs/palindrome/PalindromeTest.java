package cz.fjfi.pvs.palindrome;

import junit.framework.Assert;

import org.junit.Test;

public class PalindromeTest {
		
	@Test
	public void shouldReturnFalseWhenNonPalindromeInserted(){
		
		String testedString = "Ahoj";
		
		Assert.assertFalse("Toto neni palindrom: " + testedString, PalindromeChecker.checkIfThisIsPalindrome(testedString));
		
	}
	
	@Test
	public void shouldReturnTrueWhenPalindromeInserted(){
		
		String testedString = "jelenovipivonelej";
		
		Assert.assertTrue("Toto je palindrom: " + testedString, PalindromeChecker.checkIfThisIsPalindrome(testedString));
		
	}
	
}
