import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeRecursive {
	static Map<String, String> test = new HashMap<String, String>();
	public static String longestPalindrome(String s) {
		if(LongestPalindromeRecursive.test.containsKey(s)){
			return LongestPalindromeRecursive.test.get(s);
		}
		if(s.length()<2){
			LongestPalindromeRecursive.test.put(s, s);
			return s;
		}
		
		Map<String, Boolean> pal = new HashMap<String, Boolean>();
		String s1,s2;
		if(!pal.containsKey(s)){
			pal.put(s, LongestPalindromeRecursive.isPallindrome(s));
		}
		if(!pal.get(s).booleanValue()){
    	   s1=longestPalindrome(s.substring(1));
    	   s2=longestPalindrome(s.substring(0, s.length()-1));
    	   String ans = LongestPalindromeRecursive.max(s1, s2);
    	   LongestPalindromeRecursive.test.put(s, ans);
    	   return LongestPalindromeRecursive.test.get(s); 
		}
		LongestPalindromeRecursive.test.put(s, s);
		return LongestPalindromeRecursive.test.get(s);
       
    }
	
	static String max(String s1 , String s2 ){
		if(s1.length()>=s2.length())
			return s1;
		return s2;
	}
	
	public static boolean isPallindrome(String s){
    	//System.out.println(s.substring(0,1));
        int p = s.length();
        int j=p-1;
        for(int i=0;i<p;i++){
            if(s.charAt(i) != s.charAt(j--)){
                return false;
            }
            if(j<=i){
                return new Boolean(true);
            }
        }
        return new Boolean(true);
    }

}
