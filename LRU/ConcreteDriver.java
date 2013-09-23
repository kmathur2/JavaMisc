package LRU;

import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;



public class ConcreteDriver extends AbstractLruOperations {

	public static void main(String args[]){
		//create a random cache
		AbstractLruOperations abs=new AbstractLruOperations();
		for(int i=0;i<5;i++){
			Date d=new Date();
			Page p=new Page(i,"TIME "+d.getTime());
			abs.cache.put(new Integer(i),p);
			abs.list.add(p);


		}

		//3 2 7 4 1
		printCache(abs);
		System.out.println("\ntrying to access 3");
		abs.accessPage(3);

		printCache(abs);
		System.out.println("\n\ntrying to access 2");
		abs.accessPage(2);

		printCache(abs);
		System.out.println("\n\ntrying to access 7");
		abs.accessPage(7);

		printCache(abs);
		System.out.println("\n\ntrying to access 4");
		abs.accessPage(4);

		printCache(abs);
		System.out.println("\n\ntrying to access 1");
		abs.accessPage(1);
		
		printCache(abs);
		System.out.println("\n\ntrying to access 11");
		abs.accessPage(11);


	}

	private static void printCache(AbstractLruOperations ab){
		ListIterator<Page> it = ab.list.listIterator();
		System.out.println("\n");	
		while(it.hasNext()){
			System.out.print(it.next().id+" ");

		}
		System.out.println("\n");
		for(Integer i : ab.cache.keySet()){
			//System.out.print(i.intValue());
		}

	}
}
