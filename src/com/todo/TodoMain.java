package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;
import com.todo.service.JSONHandler;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
//		l.importData("todolist.txt");
//		boolean isList = false;
		boolean quit = false;
		
//		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
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
				
			case "add_m":
				TodoUtil.createItem_m(l);
				break;
			
			case "del_m":
				TodoUtil.deleteItem_m(l);
				break;
				
			case "edit_m":
				TodoUtil.updateItem_m(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.println("\nSystem: 제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",1);
				break;

			case "ls_name_desc":
				System.out.println("\nSystem: 제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",0);
				break;
				
			case "ls_date":
				System.out.println("\nSystem: 날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",1);
				break;
				
			case "ls_date_desc":
				System.out.println("\nSystem: 최신순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",0);
				break;
			
			case "find":
				TodoUtil.find(l,sc.next());
				break;
			
			case "find_cate":
				TodoUtil.find_cate(l,sc.next());
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;
			
			case "complete":
				TodoUtil.manageCompletion(l);
				break;
				
			case "complete_m":
				TodoUtil.manageCompletion_m(l);
				break;
			case "make_JSON":
				JSONHandler.serializeObject(l,sc.next());
				break;
				
			case "get_JSON":
				JSONHandler.deserializeObject(sc.next());
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
		} while (!quit);
		sc.close();
//		TodoUtil.saveList(l, "todolist.txt");
//		System.out.println("\nSystem: 저장되었습니다.");
		System.out.println("\nSystem:  Bye");
	}
}
