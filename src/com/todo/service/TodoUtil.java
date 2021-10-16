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
		System.out.println("장소 >> ");
		String location = sc.nextLine().trim();
		System.out.println("함께하는 사람(그룹) >> ");
		String with = sc.nextLine().trim();
		TodoItem t = new TodoItem(category, title, desc, due_date, location, with);
		if (l.addItem(t) > 0)
			System.out.println("System: 추가되었습니다.");
	}

	public static void createItem_m(TodoList l) {

		String title, desc;
		Scanner sc = new Scanner(System.in);

		System.out.println("\n" + "========== 항목 추가 - Multi-item ========== \n" + "추가할 항목의 개수 >> ");
		int num = sc.nextInt();

		for (int i = 0; i < num; i++) {
			System.out.println("\n- " + num + "개 중 " + (i + 1) + "번째");
			System.out.println("카테고리 >> ");
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
			System.out.println("장소 >> ");
			String location = sc.nextLine().trim();
			System.out.println("함께하는 사람(그룹) >> ");
			String with = sc.nextLine().trim();
			TodoItem t = new TodoItem(category, title, desc, due_date, location, with);
			if (l.addItem(t) > 0)
				System.out.println("System: 추가되었습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + "========== 항목 삭제 ==========\n" + "삭제할 항목의 번호 >>  ");
		int index = sc.nextInt();
		if (l.deleteItem(index) > 0)
			System.out.println("삭제되었습니다.");
	}

	public static void deleteItem_m(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + "========== 항목 삭제 ==========\n" + "삭제할 항목의 번호를 모두 입력 (ex: 1,2,3,4) >>  ");

		String nums = sc.nextLine();
		StringTokenizer st = new StringTokenizer(nums, ",");
		ArrayList<Integer> deletedIndex = new ArrayList<Integer>();
		while (st.hasMoreElements()) {
			int index = Integer.parseInt(st.nextToken());
			if (l.deleteItem(index) > 0)
				deletedIndex.add(index);
		}
		if (deletedIndex.size() == 0) {
			System.out.println("System: 삭제를 실패하였습니다.");
		} else {
			System.out.print("System: ");
			for (int di : deletedIndex) {
				System.out.print("" + di + " ");
			}
			System.out.print("번을 삭제하였습니다.");
		}
	}

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
		System.out.println("새 장소 >> ");
		String location = sc.nextLine().trim();
		System.out.println("함께하는 사람(그룹) >> ");
		String with = sc.nextLine().trim();
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, location, with);
		t.setId(index);
		if (l.updateItem(t) > 0)
			System.out.println("System: 수정되었습니다.");
	}

	public static void updateItem_m(TodoList l) {
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + "========== 항목 수정 ==========\n" + "수정할 항목의 번호를 모두 입력 (ex: 1,2,3,4) >>  ");

		String nums = sc.nextLine();
		StringTokenizer st = new StringTokenizer(nums, ",");

		while (st.hasMoreElements()) {
			int index = Integer.parseInt(st.nextToken());
			System.out.println("System: " + index + "번 수정중...");
			System.out.print("새 제목 >");
			new_title = sc.next().trim();
			System.out.print("새 카테고리 > ");
			new_category = sc.next();
			sc.nextLine();
			System.out.print("새 내용 > ");
			new_desc = sc.nextLine().trim();
			System.out.print("새 마감일자 > ");
			new_due_date = sc.nextLine().trim();
			System.out.println("새 장소 >> ");
			String location = sc.nextLine().trim();
			System.out.println("함께하는 사람(그룹) >> ");
			String with = sc.nextLine().trim();
			TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, location, with);
			t.setId(index);
			if (l.updateItem(t) > 0)
				System.out.println("System: " + index + "번 수정되었습니다.");
		}
	}

	public static void manageCompletion(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + "========== 완료 관리 ==========\n" + "완료 관리할 항목의 번호 >> ");
		int id = sc.nextInt();
		System.out.print("\nSystem: " + id + "번 항목을 어떤 상태로 만드시겠습니까? (1: 완료, 2: 미완료) >> ");
		int cmd = sc.nextInt();
		if (cmd == 1) {
			if (l.updateComplete(id, true) > 0)
				System.out.println("System: " + id + "번 완료처리 되었습니다.");
		} else if (cmd == 2) {
			if (l.updateComplete(id, false) > 0)
				System.out.println("System: " + id + "번 미완료처리 되었습니다.");
		} else {
			System.out.println("System: 취소되었습니다.");
		}

	}

	public static void manageCompletion_m(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + "========== 완료 관리 ==========\n" + "완료 관리할 항목의 번호를 모두 입력 (ex: 1,2,3,4) >>  ");
		String nums = sc.nextLine();
		StringTokenizer st = new StringTokenizer(nums, ",");

		while (st.hasMoreElements()) {
			int id = Integer.parseInt(st.nextToken());
			System.out.print("\nSystem: " + id + "번 항목을 어떤 상태로 만드시겠습니까? (1: 완료, 2: 미완료) >> ");
			int cmd = sc.nextInt();
			if (cmd == 1) {
				if (l.updateComplete(id, true) > 0)
					System.out.println("System: " + id + "번 완료처리 되었습니다.");
			} else if (cmd == 2) {
				if (l.updateComplete(id, false) > 0)
					System.out.println("System: " + id + "번 미완료처리 되었습니다.");
			} else {
				System.out.println("System: 취소되었습니다.");
			}
		}

	}

	public static void find(TodoList l, String key) {
		System.out.println("<키워드 \"" + key + "\"의 검색 결과>");
		int count = 0;
		for (TodoItem item : l.getList(key)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("System: 총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void find_cate(TodoList l, String key) {
		System.out.println("<카테고리 \"" + key + "\"의 검색 결과>");
		int count = 0;
		for (TodoItem item : l.getListCategory(key)) {
			System.out.println(item.toString());
			count++;
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
	
	public static void listComp(TodoList l) {
		System.out.println("");
		ArrayList<TodoItem> list = l.getComp(true);
		System.out.printf("<완료 목록, 총 %d개>\n", list.size());
		for (TodoItem item : list) {
			System.out.println(item.toString());
		}
	}
	
	public static void listNcomp(TodoList l) {
		System.out.println("");
		ArrayList<TodoItem> list = l.getComp(false);
		System.out.printf("<미완료 목록, 총 %d개>\n", list.size());
		for (TodoItem item : list) {
			System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("<전체 목록, 총 %d개>\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	public static void listCate(TodoList l) {
		System.out.println("");
		System.out.println("<카테고리 목록>");
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\nSystem: 총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}

}
