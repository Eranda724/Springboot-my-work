package com.example.Book.model;

public class Service {
    private String name;
    private int marks;

    public Service(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "StudentController{" +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
