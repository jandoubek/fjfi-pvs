package cz.fjfi.pvs.palindrome;


public class PalindromeNumberGenerator {
	
	public static String generatePalindrome(int sourceNumber) {
				
		String result = "" + doTheMathTrick(sourceNumber);
		
		return result;
	}

	protected static int doTheMathTrick(int source) {
				
		int invSource;
		for (int i = 0; i < 7; i++) {
			invSource = invertNumber(source);
			System.out.println(source + " " + invSource);
			source = source + invSource;			
		}		
		return source;
	}
	
	protected static int invertNumber(int sourceNumber) {
		
		String sourceNumberString = "" + sourceNumber;
		
		StringBuffer sb = new StringBuffer(sourceNumberString);
		sb.reverse();
		
		int n = sourceNumberString.length();
				
		String interimResult = sb.toString();
		sb = new StringBuffer();
		
		boolean hitNonZero = false;
		for (int i = 0; i < n; i++) {			
			if(!hitNonZero && interimResult.charAt(i)=='0' ) {
				
			} else {
				hitNonZero = true;
				sb.append(interimResult.charAt(i));
			}
		}
		
		return Integer.parseInt(sb.toString());
	}
	
}
