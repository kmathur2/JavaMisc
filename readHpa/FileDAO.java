package Zephyr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;



public class FileDAO {

	static void readLine(String path) throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			line=br.readLine();
			int count=0;
			while (line != null) {
				String columns[]=line.split("\",\"");
				if(columns.length>2){
					String authors[]=columns[2].split(",");
					for(String s : authors){
						s=removeNoise(s);
						if(isDesired(s)){
							updateHash(s);
						}
					}

				}


				line = br.readLine();
			}
			System.out.println("number of misformed lines are "+count);
		}

	}

	static String removeNoise(String s){
		s=s.replace(".","");
		s=s.replace(";","");
		s=s.trim();
		return s;
	}

	static void updateHash(String s){
		Author auth=new Author(s);
		if(Driver.pubCount.containsKey(auth)){
			Driver.pubCount.put(auth,Driver.pubCount.get(auth)+1);
		}else{
			Driver.pubCount.put(auth,1);
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

		return mid+" "+names[2];
	}

	//For more accuracte counts need to take care of some cases where author is not
	// properly formatted
	static boolean isDesired(String s){
		
		for(String st:Driver.desiredAuthors){
			if(s.contains(st)){
				return true;
			}
		}
		return false;
		//return Driver.desiredAuthors.contains(s);
	}

	static void writeLine(String path) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(path);
		for(Author a : Driver.pubCount.keySet()){
			writer.println(a.getFirst()+" "+a.getLast()+","+a.getInitials()+","+Driver.pubCount.get(a)) ;

		}
		writer.close();

	}

}
