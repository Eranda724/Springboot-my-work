package com.example.Book.model;

public class Service {
    private String name;
    private String type;
    private boolean special;


    public Service(String name, String type, boolean special) {
        this.name = name;
        this.type = type;
        this.special = special;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "StudentController{" +
                ", name='" + name + '\'' +
                ", marks=" + type + '\'' +
                ", special=" + special + '\n' +
                '}';
    }
}
