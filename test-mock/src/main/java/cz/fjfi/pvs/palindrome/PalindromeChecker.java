package cz.fjfi.pvs.palindrome;

public class PalindromeChecker {
	
	public static boolean checkIfThisIsPalindrome(String checkedString) {
		int k = checkedString.length();
		int n = k / 2;
		
		for (int i = 0; i < n; i++) {
			if(checkedString.charAt(i) != checkedString.charAt(k-i-1)){
				return false;
			}
		}
		return true;
	}
	
}
