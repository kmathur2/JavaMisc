package practice;

import java.util.ArrayList;

public class findWords {

	public static void main(String args[]){

		ArrayList<String> al= getWords("lifeisbeautiful");

		for(String s : al){
			System.out.println(s);
		}

	}

	private static ArrayList<String> getWords(String str){
		ArrayList<String> answer = new ArrayList<String>();
		ArrayList<String> remaining = new ArrayList<String>();
		String word;
		boolean flag=true;
		for(int i=1;i<=str.length();i++){
			word=str.substring(0,i);

			if(isValid(word)){
				//System.out.println(word);
				remaining=getWords(str.substring(i));
				for(String s : remaining){
					flag=flag && isValid(s);
				}

				if(flag){
					answer.add(word);
					answer.addAll(remaining);
				}	
			}


		}

		return answer;
	}

	//temp dictionary method
	private static boolean isValid(String s){
		if(s.equals("life") || s.equals("is") || s.equals("beautiful"))
			return true;

		return false;
	}
}
