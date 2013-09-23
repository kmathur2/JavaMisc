package practice;

import java.util.*;

public class Permutations{
	//private static int rank=1;
	public static void main(String args[]){
		ArrayList<String> out=permute("PCATIU");
		Collections.sort(out);
		int i=1;
		for(String s : out){
			System.out.println(i+" - "+s);
			i++;
		}
		
		
	}


	public static ArrayList<String> permute(String input){
		int rank=1;
		ArrayList<String> finalPermutations=new ArrayList<String>();

		if(input.length()==1){
			finalPermutations.add(input);
			return finalPermutations;
		}



		char first=input.charAt(0);
		String remaining=input.substring(1,input.length());


		ArrayList<String> permutations= new ArrayList<String>();
		permutations= permute(remaining);

		for(String s : permutations){
			for(int i=0;i<=s.length();i++){
				String str=insertAtMiddle(first,i,s);
				if(input.compareTo(str) < 0)
					rank++;
				finalPermutations.add(str);
			}
		}

		System.out.println("rank is "+ ++rank);
		return finalPermutations;

	}

	public static String insertAtMiddle(char a,int index,String str){
		if(index==0)
			return a+str;

		String before=str.substring(0,index);
		String after=str.substring(index,str.length());
		return before+a+after;
	}

}
