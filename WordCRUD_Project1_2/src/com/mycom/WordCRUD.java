package com.mycom;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{

	ArrayList<Word> list;
	Scanner s;
	
	final String fname = "Dictionary.txt";
	
	
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
	
	
	public ArrayList<Integer> listAll(String keyword){
		ArrayList<Integer> idlist= new ArrayList<>();
		int j=0;
		System.out.println("--------------------------------");
		for(int i=0; i<list.size(); i++) {
			String word = list.get(i).getWord();
			if(!word.contains(keyword)) continue;
			System.out.print((j+1)+" ");
			System.out.println(list.get(i).toString());
			idlist.add(i);
			j++;
		}
		System.out.println("--------------------------------");
		return idlist;
	}
	
	public void listAll(int level) {
		ArrayList<Integer> idlist= new ArrayList<>();
		int j=0;
		System.out.println("--------------------------------");
		for(int i=0; i<list.size(); i++) {
			int ilevel = list.get(i).getLevel();
			if(ilevel!=level) continue;
			System.out.print((j+1)+" ");
			System.out.println(list.get(i).toString());
			
			j++;
		}
		System.out.println("--------------------------------");
		
		}

	public void updateItem() {
		// TODO Auto-generated method stub
		
		System.out.print("=> 수정할 단어 검색 : ");
		String keyword = s.next(); //띄어쓰기 허용 안함 
		ArrayList<Integer> idlist = this.listAll(keyword);
		System.out.print("=> 수정할 번호 선택 : ");
		int id = s.nextInt();
		s.nextLine();
		System.out.print("=> 뜻 입력 : ");
		String meaning = s.nextLine();
		Word word = list.get(idlist.get(id-1));
		word.setMeaning(meaning);
		System.out.println("단어가 수정되었습니다.");
		
	}

	@Override
	public int update(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void selectOne(int id) {
		// TODO Auto-generated method stub
		
	}

	public void deleteItem() {
		// TODO Auto-generated method stub
		System.out.print("=> 삭제할 단어 검색 : ");
		String keyword = s.next();
		ArrayList<Integer> idlist=this.listAll(keyword);
		System.out.print("=> 삭제할 번호 선택 : ");
		int id = s.nextInt();
		s.nextLine();
		
		System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
		String ans = s.next();
		if(ans.equalsIgnoreCase("y")) {
			list.remove((int)idlist.get(id-1));
			System.out.println("단어가 삭제되었습니다.");
		}
		else
			System.out.println("취소되었습니다. ");
	}
	
	public void loadFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line;
			int count=0;
			while(true)
			{
				line = br.readLine();
				if(line==null) break;
				
				String data[]=line.split("\\|"); 
				int level = Integer.parseInt(data[0]);
				String word = data[1];
				String meaning = data[2];
				list.add(new Word(0,level,word,meaning));
				count++;
				
			}
			br.close();
			System.out.println("==> "+count+"개 로딩 완료!!!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveFile() {
		// TODO Auto-generated method stub
		//파일 이름과 위치를 저장 
		//printwriter 문장 단위로 
		try {
			PrintWriter pr = new PrintWriter(new FileWriter(fname));
			for(Word one : list) {
				pr.write(one.toFileString()+"\n");
			}
			pr.close();
			System.out.println("==> 데이터 저장 완료!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchLevel() {
		// TODO Auto-generated method stub
		System.out.print("==> 원하는 레벨은? (1~3)");
		int level = s.nextInt();
		listAll(level); //해당하는 레벨단어 출력 
	}
	
	public void searchWord()
	{
		System.out.print("=> 원하는 단어는? ");
		String keyword = s.next();
		listAll(keyword);
	}

	
}
