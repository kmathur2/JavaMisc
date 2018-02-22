/**
 * Created by kartik on 2/21/18.
 */

/*
This is dynamic programming problem, so the way I am doing it is
example -

   f g d d d l d
d  0 0 1 1 1 0 1
d  0 0 1 2 2 0 1
d  0 0 1 2 3 0 1
 */
public class LongestSubstring {
    public static void main(String args[]){
        System.out.print(LongestSub("my name is ho lacko bum bum", "but my name is hothing bum bum"));
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

        /*line 27-45 is useless, filling 1st row
        and 1st column to avoid confusing if-else in the
        main loop**/

        //starts here
        if(s1.charAt(0)==s2.charAt(0)){
            L[0][0]=1;
        }else{
            L[0][0]=0;
        }
        for(int row=1;row<m;row++){
            if(s1.charAt(row) == s2.charAt(0)){
                L[row][0]=L[row-1][0]+1;
            }else{
                L[row][0] = L[row-1][0];
            }
        }
        for(int col=0;col<n;col++){
            if(s1.charAt(0) == s2.charAt(col)){
                L[0][col]=1;
            }else{
                L[0][col]=0;
            }
        }
        //ends here

        //main logic starts here, filling 1st row and
        //column has been taken care of earlier

        for(i=1;i<m;i++){
            for(j=1;j< n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        L[i][j] = L[i - 1][j - 1] + 1;
                    }else {
                        L[i][j]=1;
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
