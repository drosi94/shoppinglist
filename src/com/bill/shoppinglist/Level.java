package com.bill.shoppinglist;

public class Level {


	String answer;

	public Level(int l) {

		switch (l) {
		case 1:
			answer = "Less Important Need";
			break;
		case 2:
			answer = "Daily Need";
			break;
		case 3:
			answer = "Highly Important Need";
			break;
		
		default:
			answer = "Error Occured";
		}
	}


	public String toString(){
		
		return "Priority Level: "+answer;
	}
}
