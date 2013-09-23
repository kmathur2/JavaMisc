package LRU;

import java.util.LinkedList;
import java.util.*;

public class AbstractLruOperations implements ILruOperations {
	public LinkedList<Page> list = new LinkedList<Page>();
	public Map<Integer,Page> cache = new HashMap<Integer,Page>();

	public void accessPage(int n){
		if(isPageFault(n)){
			addPage(n);
		}else
			reArrangeLinkList(cache.get(new Integer(n)));

	}

	public void addPage(int n){
		Date d=new Date();
		Page p=new Page(n,"test"+d.getTime());

		list.addFirst(p);
		cache.put(new Integer(n),p);
		if(list.size() > CACHE_SIZE){
			Page e = list.removeLast();
			System.out.println("REMOVING "+e.id+" from map");
			cache.remove(new Integer(e.id));
		}
	}

	public void reArrangeLinkList(Page p){
		System.out.println("Rearranging list removing "+p.id+" from cache");
		list.removeFirstOccurrence(p);
		System.out.println("Adding "+p.id+" to beginning");
		list.addFirst(p);
	}

	public boolean isPageFault(int n){
		if(cache.containsKey(new Integer(n))){
			System.out.println("Page Hit");
			return false;
		}else{
			System.out.println("Page Fault");
			return true;
		}

	}

}
