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

	public static void createItem(TodoList l) {

		String title, desc;
		Scanner sc = new Scanner(System.in);

		System.out.println("\n" + "========== 항목 추가 ========== \n" + "카테고리 >> ");
		String category = sc.next().trim();
		sc.nextLine();
		System.out.println("제목 >> ");
		title = sc.next();
		if (l.isDuplicate(title)) {
			System.out.println("System: 제목은 중복될 수 없습니다.");
			sc.close();
			return;
		}
		sc.nextLine();
		System.out.println("내용 >> ");
		desc = sc.nextLine().trim();
		System.out.println("마감일자 >> ");
		String due_date = sc.nextLine().trim();
		TodoItem t = new TodoItem(category, title, desc, due_date);
//		l.addItem(t);
		if (l.addItem(t) > 0)
			System.out.println("추가되었습니다.");
	}

//	public static void deleteItem(TodoList l) {
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("\n"
//				+ "========== 항목 삭제 ==========\n"
//				+ "삭제할 항목의 번호 >>  ");
//		int num;
//		try{
//			num = sc.nextInt();
//		}catch(InputMismatchException e) {
//			System.out.println("System: 올바른 형식으로 입력해주십시오.");
//			deleteItem(l);
//			return;
//		}
//		
//		if (l.getList().size()<num) {
//			System.out.println("System: 번호가 존제하지 않습니다.\n");
//			return;
//		}
//		
//		int index = 0;
//		for (TodoItem item : l.getList()) {
//			if (num-1 == index) {
//				System.out.print("\n"+num+". "+item.toString()+"\n\nSystem:정말 삭제하시겠습니까? (확인: y) >>");
//				String realy = sc.next();
//				if(realy.equals("y")) {
//					l.deleteItem(item);
//					System.out.println("System: 삭제되었습니다.");
//				}
//				else System.out.println("System: 삭제가 취소되었습니다.");
//				break;
//			}
//			index += 1;
//		}
//	}
	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + "========== 항목 삭제 ==========\n" + "삭제할 항목의 번호 >>  ");
		int index = sc.nextInt();
		if (l.deleteItem(index) > 0)
			System.out.println("삭제되었습니다.");
	}

//	public static void updateItem(TodoList l) {
//		
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("\n"
//				+ "========== 항목 수정 ==========\n"
//				+ "수정할 항목의 번호 >> ");
//		int num = sc.nextInt();
//		if (l.getList().size()<num) {
//			System.out.println("System: 번호가 존제하지 않습니다.\n");
//			return;
//		}
//		System.out.println("항목의 새 카테고리 >> ");
//		String new_category = sc.next().trim();
//		sc.nextLine();
//		System.out.println("항목의 새 제목 >> ");
//		String new_title = sc.next().trim();
//		if (l.isDuplicate(new_title)) {
//			System.out.println("System: 제목은 중복될 수 없습니다.\n");
//			return;
//		}
//		sc.nextLine();
//		System.out.println("항목의 새 내용 >> ");
//		String new_description = sc.nextLine().trim();
//		System.out.println("항목의 새 마감일자 >> ");
//		String new_due_date = sc.nextLine().trim();
//		int index=0;
//		for (TodoItem item : l.getList()) {
//			if (num-1 == index) {
//				l.deleteItem(item);
//				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
//				l.addItem(t);
//				System.out.println("System: 항목이 수정되었습니다.");
//				break;
//			}
//			index += 1;
//		}
//	}
	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + "========== 항목 수정 ==========\n" + "수정할 항목의 번호 >> ");
		int index = sc.nextInt();

		System.out.print("새 제목 >");
		new_title = sc.next().trim();
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if (l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");
	}

	public static void find(TodoList l, String key) {
		System.out.println("<키워드 \"" + key + "\"의 검색 결과>");
		int count = 0;
		for (TodoItem item : l.getList(key)) {
				System.out.println(item.toString());
				count ++;
		}
		System.out.printf("System: 총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void find_cate(TodoList l, String key) {
		System.out.println("<카테고리 \"" + key + "\"의 검색 결과>");
		int count = 0;
		for (TodoItem item : l.getListCategory(key)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("System: 총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void listAll(TodoList l) {
		System.out.println("");
		System.out.printf("<전체 목록, 총 %d개>\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	public static void listCate(TodoList l) {
		System.out.println("");
		System.out.println("<카테고리 목록>");
//		List<String> cates = new ArrayList<String>();
//		String tmp;
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item+" ");
			count ++;
		}
		System.out.printf("\nSystem: 총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}

//	public static void saveList(TodoList l, String filename) {
//		File file = new File(filename);
//		if (!file.exists())
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		FileWriter fw;
//		try {
//			fw = new FileWriter(file);
//			for (TodoItem item : l.getList())
//				fw.write(item.toSaveString());
//			fw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	public static void loadList(TodoList l, String filename) {
//		File file = new File(filename);
//		try {
//			if (!file.exists())
//				System.out.println("System: " + filename + " 파일이 없습니다.");
//			else {
//				int count = 0;
//				BufferedReader br = new BufferedReader(new FileReader(file));
//				String line;
//				while ((line = br.readLine()) != null) {
//					count += 1;
//					StringTokenizer st = new StringTokenizer(line, "##");
//					TodoItem t = new TodoItem(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(),
//							st.nextToken());
//					l.addItem(t);
//				}
//				br.close();
//				System.out.println("System: " + count + "개의 항목을 읽었습니다.\n");
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
