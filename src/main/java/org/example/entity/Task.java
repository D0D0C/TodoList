package org.example.entity;

public class TodoList {

    private int Id;

    private String title;

    private Boolean Status;

    public TodoList(String title, Boolean status) {
        this.title = title;
        Status = status;
    }

    public TodoList(int id, String title, Boolean status) {
        Id = id;
        this.title = title;
        Status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", Status=" + Status +
                '}';
    }
}
