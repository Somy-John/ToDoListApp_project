package com.todo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.println("\n"
				+ "========== 항목 추가 ========== \n"
				+ "카테고리 >> ");
		String category = sc.next().trim();
		sc.nextLine();
		System.out.println("제목 >> ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("System: 제목은 중복될 수 없습니다.");
			sc.close();
			return;
		}
		sc.nextLine();
		System.out.println("내용 >> ");
		desc = sc.nextLine().trim();
		sc.nextLine();
		System.out.println("마감일자 >> ");
		String due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("System: 추가되었습니다.");
		sc.close();
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "========== 항목 삭제 ==========\n"
				+ "삭제할 항목의 번호 >>  ");
		int num = sc.nextInt();
		
		if (l.getList().size()<num) {
			System.out.println("System: 번호가 존제하지 않습니다.\n");
			sc.close();
			return;
		}
		
		int index = 0;
		for (TodoItem item : l.getList()) {
			if (num-1 == index) {
				l.deleteItem(item);
				System.out.println("System: 삭제되었습니다.");
				break;
			}
		}
		sc.close();
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 항목 수정 ==========\n"
				+ "수정할 항목의 번호 >> ");
		int num = sc.nextInt();
		if (l.getList().size()<num) {
			System.out.println("System: 번호가 존제하지 않습니다.\n");
			sc.close();
			return;
		}
		System.out.println("항목의 새 카테고리 >> ");
		String new_category = sc.next().trim();
		sc.nextLine();
		System.out.println("항목의 새 제목 >> ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("System: 제목은 중복될 수 없습니다.\n");
			sc.close();
			return;
		}
		sc.nextLine();
		System.out.println("항목의 새 내용 >> ");
		String new_description = sc.nextLine().trim();
		sc.nextLine();
		System.out.println("항목의 새 마감일자 >> ");
		String new_due_date = sc.nextLine().trim();
		int index=0;
		for (TodoItem item : l.getList()) {
			if (num-1 == index) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
				l.addItem(t);
				System.out.println("System: 힝목이 수정되었습니다.");
			}
		}
		sc.close();
	}
	
	public static void find(TodoList l, String key) {
		System.out.println("<키워드 \""+key+"\"의 검색 결과>");
		int index = 0, count = 0;
		for (TodoItem item : l.getList()) {
			index+=1;
			if(item.toString().substring(item.toString().indexOf("]")).contains(key)) {
				System.out.println(index+". "+item.toString());
				count += 1;
			}
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n",count);
	}
	
	public static void find_cate(TodoList l, String key) {
		System.out.println("<카테고리 \""+key+"\"의 검색 결과>");
		int index = 0, count = 0;
		for (TodoItem item : l.getList()) {
			index+=1;
			if(item.toString().substring(item.toString().indexOf("["),item.toString().indexOf("]")).contains(key)) {
				System.out.println(index+". "+item.toString());
				count += 1;
			}
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n",count);
	}

	public static void listAll(TodoList l) {
		System.out.println("");
		System.out.printf("<전체 목록, 총 %d개>\n",l.getList().size());
		int index = 0;
		for (TodoItem item : l.getList()) {
			index+=1;
			System.out.println(index+". "+item.toString());
		}
	}
	
	public static void listCate(TodoList l) {
		System.out.println("");
		System.out.println("<카테고리 목록>");
		int count = 0;
		for (TodoItem item : l.getList()) {
			count+=1;
			System.out.println(item.toString());
		}
		System.out.printf("총 %d개의 카테고리가 등록되어 있습니다.\n",count);
	}
	
	public static void saveList(TodoList l, String filename) {
		File file=new File(filename);
        if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		FileWriter fw;
		try {
			fw = new FileWriter(file);
        for(TodoItem item : l.getList())
            fw.write(item.toSaveString());
        fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		File file=new File(filename); 
		try {
			if(!file.exists())
				System.out.println(filename + " 파일이 없습니다.");
			else {
				int count=0;
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while((line = br.readLine()) != null) {
					count += 1;
					StringTokenizer st = new StringTokenizer(line,"##");
					TodoItem t = new TodoItem(st.nextToken(),st.nextToken(),st.nextToken(), st.nextToken(),st.nextToken());
					l.addItem(t);
				}
				br.close();
				System.out.println(count+"개의 항목을 읽었습니다.\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
