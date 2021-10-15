package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private String category;
    private String title;
    private String desc;
    private String due_date;
    private String current_date;
    private String location;
    private String with;
    private boolean isComplete;
    private int id;


    public TodoItem(String title, String desc,String category,String due_date, String location, String with, boolean isComplete){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	this.setCategory(category);
        this.title=title;
        this.desc=desc;
        this.setDue_date(due_date);
        this.current_date=sdf.format(new Date());
        this.location=location;
        this.with=with;
        this.isComplete=isComplete;
    }
    public TodoItem(String title, String desc,String category,String due_date, String location, String with){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	this.setCategory(category);
        this.title=title;
        this.desc=desc;
        this.setDue_date(due_date);
        this.current_date=sdf.format(new Date());
        this.location=location;
        this.with=with;
    }
    public TodoItem(String title, String desc,String category,String due_date, String current_date, String location, String with, boolean isComplete){
    	this.setCategory(category);
        this.title=title;
        this.desc=desc;
        this.setDue_date(due_date);
        this.current_date=current_date;
        this.location=location;
        this.with=with;
        this.isComplete=isComplete;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    @Override
    public String toString() {
    	return id+" [" + category + "] "+ title + " / "+ (isComplete ? "완료" : "미완료")+" - "+ desc +" - "+ with + " - " + location +" - "+due_date + " - " + current_date;
    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWith() {
		return with;
	}

	public void setWith(String with) {
		this.with = with;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
}
