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
				+ "제목 >> ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("System: 제목은 중복될 수 없습니다.");
			return;
		}
		sc.nextLine();
		System.out.println("내용 >> ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("System: 추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n"
				+ "========== 항목 삭제 ==========\n"
				+ "삭제할 항목의 제목 >>  ");
		
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("System: 삭제되었습니다.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 항목 수정 ==========\n"
				+ "수정할 항목의 제목 >> ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("System: 제목이 존제하지 않습니다.\n");
			return;
		}

		System.out.println("항목의 새 제목 >> ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("System: 제목은 중복될 수 없습니다.\n");
			return;
		}
		sc.nextLine();
		System.out.println("항목의 새 내용 >> ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("System: 힝목이 수정되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("");
		System.out.println("<Todo List>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
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
				System.out.println("1개의 항목을 읽었습니다.\n");
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while((line = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(line,"##");
					TodoItem t = new TodoItem(st.nextToken(), st.nextToken(),st.nextToken());
					l.addItem(t);
				}
				br.close();
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
