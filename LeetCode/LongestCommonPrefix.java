
public class LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
		if(strs.length==0)
			return "";
		
        int min=strs[0].length(),max=0,minI=0,maxI=0;
        for(int i=0;i<strs.length;i++){
            if(strs[i].length() < min){
                min=strs[i].length();
                minI=i;
            }
        }
       
        String smallest=strs[minI];
        //System.out.println(smallest); 
       for(int i=0;i<strs.length;i++){
    	   if(min==0)
           	return "";
    	String next=strs[i];
    	for(int j=0;j<min;j++){
    		if(next.charAt(j)!=smallest.charAt(j)){
    			smallest=smallest.substring(0, j);
        		min=smallest.length();
    		}		
    	}
       	}
        System.out.println(smallest);
        return smallest;

    }
}

