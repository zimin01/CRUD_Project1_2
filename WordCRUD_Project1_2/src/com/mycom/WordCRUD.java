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

	public Object add() {
		System.out.print("==> 난이도(1,2,3) & 새 단어 입력 : ");
		int level=s.nextInt();
		String word = s.nextLine(); //공백포함 
		
		System.out.print("뜻 입력 : ");
		String meaning = s.nextLine();
		
		return new Word(0,level,word,meaning); 
	}
	
	public void addWord() {
		// TODO Auto-generated method stub
		Word one = (Word)add();
		list.add(one);
		System.out.println("새 단어가 단어장에 추가되었습니다. ");
		
	}
	
	public void listAll() {//사용자에게 단어를 입력받는 함수 
		// TODO Auto-generated method stub
		System.out.println("--------------------------------");
		for(int i=0; i<list.size(); i++)
		{
			System.out.print(i+1); //출력문 맨 앞 단어 수 카운트 
			System.out.println(list.get(i).toString());
		}
		System.out.println("--------------------------------");

	}
}
