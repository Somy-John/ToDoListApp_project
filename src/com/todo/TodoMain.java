package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList  = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("\nSystem: 제목순으로 정렬하였습니다.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("\nSystem: 제목역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("\nSystem: 날짜순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("\\nSystem: 최신순으로 정렬하였습니다.");
				isList = true;
				break;
			
			case "find":
				TodoUtil.find(l,sc.next());
				break;
			
			case "find_cate":
				TodoUtil.find_cate(l,sc.next());
				break;
			
			case "exit":
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;

			default:
				System.out.println("\nSystem: 명령어를 정확하게 입력하여주십시오. (도움말 - help)");
				break;
				
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		sc.close();
		TodoUtil.saveList(l, "todolist.txt");
		System.out.println("\nSystem: 저장되었습니다.");
	}
}
