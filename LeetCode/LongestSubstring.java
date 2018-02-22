/**
 * Created by kartik on 2/21/18.
 */

/*
This is dynamic programming problem, so the way I am doing it is
   f g d d d l d
d  0 0 1 1 1 0 1
d  0 0 1 2 2 0 1
d  0 0 1 2 3 0 1 
 */
public class LongestSubstring {
    public static void main(String args[]){
        System.out.print(LongestSub("dddd", "fgdddll"));
    }

    static int LongestSub(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int i=0, j=0, max=0;
        int longestStart=0;
        int L[][] = new int[m][n];
        if(s1 == "" || s2 == ""){
            return 0;
        }
        for(i=0;i<m;i++){
            for(j=0;j< n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i > 0 && j > 0) {
                        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                            L[i][j] = L[i - 1][j - 1] + 1;
                        }else {
                            L[i][j]=1;
                        }
                    }else if(i==0) {
                        L[i][j]=1;
                    }else {
                        L[i][j] = 1 + L[i-1][j];
                    }
                }else {
                    L[i][j] = 0;
                }
                max = Math.max(max, L[i][j]);
                if(max==L[i][j])
                    longestStart=i+1-max;
                System.out.print(L[i][j]);
            }
            System.out.println(L[i][j-1]);
        }
        System.out.println(s1.substring(longestStart, longestStart+max));
        return max;
    }
}
