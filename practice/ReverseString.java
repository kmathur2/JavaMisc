package practice;

public class ReverseString {
	public static void main(String args[]){
		System.out.println("Reverse of kartik is "+reverse("hjygtfrd"));
		
	}
	
	private static String reverse(String s){
		if(s.length()==1)
			return s;
		
		return reverse(s.substring(1))+s.charAt(0);
		
	}

}
