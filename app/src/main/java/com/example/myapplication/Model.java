package com.example.myapplication;

public class Model {
    String id, title, description, priority, status;

    public Model(String id, String title, String description, String priority, String status) {
        this.id = id;
        this.title = title;
//        this.title = description;
        this.description = description;
        this.priority = priority;
        this.status = status;

    }

    public String getId() {
        return id;
    }
//    public String getUId(){
//        return title;
//    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//    public void setUId(String title){
//        this.title = title;
//    }

}