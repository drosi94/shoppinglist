package com.bill.shoppinglist;

public class Item {
	
	private String nameOfItem;
	private Level priorityLevel;
	
	public Item(String n, Level l){
		
		this.nameOfItem = n;
		this.priorityLevel = l;
		
	}
	
	public void setName(String n){
		if(n != null && !n.equals(""))
			this.nameOfItem = n;
		
	}
	
	public void setLevel(Level l){
		int lToNum = Integer.parseInt(l.toString());
		if( (lToNum >= 1) &&  (lToNum < 4))
			this.priorityLevel = l;
		
	}
	
	public String getName(){
		
		return nameOfItem;
		
	}
	
	public String toString(){
		
		return "Item: "+nameOfItem+"\n"
				+priorityLevel;
	}


}
