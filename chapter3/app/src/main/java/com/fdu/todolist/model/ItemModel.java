package com.fdu.todolist.model;

public class ItemModel {
    private int id;
    private String content;
    private String date;
    private boolean checked;

    public ItemModel(int id, String content, String date, boolean checked) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
