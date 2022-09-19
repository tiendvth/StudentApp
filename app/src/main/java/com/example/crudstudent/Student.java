package com.example.crudstudent;

public class Student {
    private long id;
    private String name;
    private String email;
    private String phone;

    public Student() {
    }

    public Student(long id, String name, String email, String tel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = tel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return phone;
    }

    public void setTel(String tel) {
        this.phone = tel;
    }
}