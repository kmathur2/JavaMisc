package Zephyr;

public class Author {
	private String first;
	private String last;
	private String initials;
	
	public Author(String name){
		String[] n=name.split(" ");
		this.first=n[1];
		this.last=n[0];
		this.initials=n[1]+" "+n[0].substring(0,1);
	}

	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	@Override
    public int hashCode() {
        return (first+last).hashCode();
    }

	@Override
	public boolean equals(Object obj) {
		
		 // If the object is compared with itself then return true  
        if (obj == this) {
            return true;
        }
 
       
        if (!(obj instanceof Author)) {
            return false;
        }
        
        Author t=(Author)obj;
		
        return (this.hashCode()==t.hashCode()); 
	}


}
