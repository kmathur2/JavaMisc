package Zephyr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver {
	static Map<Author,Integer> pubCount=new HashMap<Author,Integer>();
	static ArrayList<String> desiredAuthors=new ArrayList<String>();
	
	
	
	public static void main(String args[]){
		if(args.length==0){
			System.out.println("Please enter the file path as the first argument"
					+ "and result as the second argument while trying to run !!");
		}
		//Initialize 
		desiredAuthors.add(getPublicationName("Dr. Vincent Rajkumar"));
		desiredAuthors.add(getPublicationName("Dr. María-Victoria Mateos"));
		desiredAuthors.add(getPublicationName("Dr. David Vesole"));
		desiredAuthors.add(getPublicationName("Dr. William Bensinger"));
		desiredAuthors.add(getPublicationName("Dr. Edward Libby"));
		desiredAuthors.add(getPublicationName("Dr. Heinz Ludwig"));
		desiredAuthors.add(getPublicationName("Dr. Sagar Lonial"));
		desiredAuthors.add(getPublicationName("Dr. Hermann Einsele"));
		desiredAuthors.add(getPublicationName("Dr. Jatin Shah"));
		desiredAuthors.add(getPublicationName("Dr. Philip McCarthy"));
		desiredAuthors.add(getPublicationName("Dr. Amrita Krishnan"));
		desiredAuthors.add(getPublicationName("Dr. Ravi Vij"));
				
		try {
			///home/kmathur/Documents/pubmed_result/pubmed_result.csv"
			FileDAO.readLine(args[0]);
			FileDAO.writeLine(args[1]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	//dirty method to format given name to excel name
	static String getPublicationName(String s){
		String[] names=s.split(" ");
		String mid;
		if(names[1].contains("-")){
			String[] middles=names[1].split("-");
			mid=String.valueOf(middles[0].charAt(0))+String.valueOf(middles[1].charAt(0));
		}else{
			mid=String.valueOf(names[1].charAt(0));
		}
		
		return names[2]+" "+mid;
	}
}
