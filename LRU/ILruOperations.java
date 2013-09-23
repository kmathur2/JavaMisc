package LRU;

public interface ILruOperations {
	
	public int CACHE_SIZE=5;
	
	public void accessPage(int num);
	
	//public void findPage(int num);
	
	public boolean isPageFault(int num);
	
	public void addPage(int num);
	
	public void reArrangeLinkList(Page p);
	
	//public void deletePage();
	

}
