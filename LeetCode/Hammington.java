
public class Hammington {
	public static int hammingDistance(int x, int y) {
        int small, big;
		if(x > y){
        	small=y; 
        	big=x;
        }else{
        	small=x;
        	big=y;
        }
			int i=1;
        	while(big>small){
        		big=big>>1;
        		//System.out.println(big);
        		if(small==big){
        			return i;
        		}
            i++;
        }
        	return -1;
       
    }
}
