package com.example.digitallibraryadmin.ApiLibrary;

import java.util.ArrayList;

public class User {
    public int id;
    public String name;
    public String email;
    public String phone;
    public Object details;
    public ArrayList<String> role;
    public ArrayList<Object> privilage;
    public String permission;
    public Object sessionId;
    public String image;
    public String status;
    public String pushId;
    public String pushOs;
    public Student student;

    public User(int id, String name, String email, String phone, Object details, ArrayList<String> role, ArrayList<Object> privilage, String permission, Object sessionId, String image, String status, String pushId, String pushOs, Student student) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.details = details;
        this.role = role;
        this.privilage = privilage;
        this.permission = permission;
        this.sessionId = sessionId;
        this.image = image;
        this.status = status;
        this.pushId = pushId;
        this.pushOs = pushOs;
        this.student = student;
    }
}
