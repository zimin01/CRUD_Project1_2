package com.mycom;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{

	ArrayList<Word> list;
	Scanner s;
	
	
	WordCRUD(Scanner s){
		list = new ArrayList<>();
		this.s=s;
	}
}
