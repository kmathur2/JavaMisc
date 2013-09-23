package practice;

public class ReverseWords {
	public static void main(String args[]){
		String sentence ="Hi My Name is kartik mathur";
		String[] array=sentence.split(" ");
		String newSentence="";
		
		for(int i=array.length-1;i>=0;i--){
			newSentence+=reverse(array[i])+" ";
		}
		System.out.println(newSentence);
	}
	
	private static String reverse(String str){
		int i=0,j=str.length()-1;
		    char[] arr=str.toCharArray();
			char temp;
		    while(i<=j){
		    	temp=arr[i];
		    	arr[i]=arr[j];
		    	arr[j]=temp;
		    	i++;j--;
		    }
		    
		    return new String(arr);
	}

}
