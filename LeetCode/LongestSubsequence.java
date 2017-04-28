
public class LongestSubsequence {
		//**//
	public static String longestPalindromeSubseq(String s) {
		if(s.length()==1)
			return s;
		char f = s.charAt(0);
		String withoutFirst = longestPalindromeSubseq(s.substring(1));
		String fLongest, lLongest;
		if(isPallindrome(withoutFirst + Character.toString(f))){
			fLongest = withoutFirst + Character.toString(f); 
		}else if(isPallindrome(withoutFirst)){
			fLongest = withoutFirst;
		} else{
			fLongest = "";
		}
		char last = s.charAt(s.length()-1);
		String withoutLast = longestPalindromeSubseq(s.substring(0, s.length()-1));
		if(isPallindrome(Character.toString(f) + withoutLast)){
			lLongest = Character.toString(last) + withoutLast;
		}else if(isPallindrome(withoutLast)){
			lLongest = withoutLast;
		}else{
			lLongest= "";
		}
		
		if(fLongest.length() >= lLongest.length())
			return fLongest;
		return lLongest;
        
    }
		//**//
	
	static boolean isPallindrome(String s){
		if(s.length()==0)
			return true;
		int i=0;
		int j=s.length()-1;
		int count=0;
		while(true){
			if(s.charAt(i)==s.charAt(j)){
				i++;
				j--;
				if(i>=j)
					return true;
			}else{
				return false;
			}
		}
		
	}
}
