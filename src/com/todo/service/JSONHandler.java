package com.todo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class JSONHandler {

	public static void serializeObject(TodoList l, String fileName) {
		Gson gson = new Gson();
		try {
			FileWriter fw = new FileWriter(fileName);
			gson.toJson(l.getList(), fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("System: \""+fileName+"\"으로 내보내기 성공하였습니다.");

	}

	public static void deserializeObject(String fileName) {

		try {
			Gson gson = new Gson();
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			JsonArray array = JsonParser.parseString(br.readLine()).getAsJsonArray();
			TodoList list = new TodoList();
			for (JsonElement jElement : array) {
				TodoItem item = gson.fromJson(jElement, TodoItem.class);
				list.addItem(item);
			}
			br.close();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("System: \""+fileName+"\" 이 없습니다.");
			return;
		}
		System.out.println("System: \""+fileName+"\"에서 가져오기 성공하였습니다.");
	}
}
