package cz.fjfi.pvs.palindrome;

import junit.framework.Assert;

import org.junit.Test;

public class PalindromeNumberGeneratorTest {
		
	@Test
	public void shouldReturnPalindrome(){
		int checkedNumber = 61;
		String result = PalindromeNumberGenerator.generatePalindrome(checkedNumber);
		Assert.assertTrue("This should be always palindrome generating number ", PalindromeChecker.checkIfThisIsPalindrome(result));
	}
}
