package Permutations;

import java.util.*;

public class Strings {

	public static void main(String args[]){
		ArrayList<String> out=permute("PCAT");

		for(String s : out){
			System.out.println(s);
		}
	}


	public static ArrayList<String> permute(String input){
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
				finalPermutations.add(insertAtMiddle(first,i,s));
			}
		}

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
