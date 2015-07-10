package Zephyr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	public static void main(String[] args){
		//String path="";
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line = br.readLine();
			line=br.readLine();
			int count=0;
			while (line != null) {
				if(line.contains("Ludwig")){
					count++;
				}
				line = br.readLine();
				/*if(line.contains("")){
					count++;
				}*/
				
			}
			System.out.println(count);
			System.out.println("number of misformed lines are "+count);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
