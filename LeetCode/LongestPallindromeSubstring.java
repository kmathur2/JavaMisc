
public class LongestPallindromeSubstring {
	public static String longestPalindrome(String s) {
        if(s.length()<=1){
            return s;
        }
        
        int length = s.length();
        for(int w=length;w>0;w--){
            for(int j=0;j<=(length-w);j++){
            	String st = s.substring(j,j+w);
            	//System.out.println(st);
                if(isPallindrome(st)){
                    return st;
                }
            }
        }
        return Character.toString(s.charAt(0));
        
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
                return true;
            }
        }
        return true;
    }

}
