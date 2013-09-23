package LRU;

public class Node {

	private int page;
	private Node next;
	private Node prev;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}

}
