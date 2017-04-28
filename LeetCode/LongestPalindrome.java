
public class LongestPalindrome {
	public static int longestPalindrome(String s) {
        int[] counts=new int[52];
        for(int i=0;i<counts.length;i++){
            counts[i]=0;
        }
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isUpperCase(c)){
                counts[c-'A'+26]=counts[c-'A'+26]+1;
            }else{
                counts[c-'a']=counts[c-'a']+1;
            }
        }
        System.out.println(s.substring(0, 2));
        int even=0,odd=0,n=0;
        for(int i=0;i<52;i++){
            if(counts[i]%2==0){
                even=even+counts[i];
            }else{
                n++;
                if(n==1){
                    odd=odd+counts[i];
                }else
                    odd=odd+counts[i]-1;
            }
        }
        return even+odd;
    }
}
