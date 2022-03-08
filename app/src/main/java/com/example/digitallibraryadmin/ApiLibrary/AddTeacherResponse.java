package com.example.digitallibraryadmin.ApiLibrary;

import java.util.ArrayList;

public class AddTeacherResponse {
    public int id;
    public String name;
    public String email;
    public String phone;
    public String details;
    public ArrayList<String> role;
    public ArrayList<String> privilage;
    public String permission;
    public Object sessionId;
    public String image;
    public String status;
    public String pushId;
    public String pushOs;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDetails() {
        return details;
    }

    public ArrayList<String> getRole() {
        return role;
    }

    public ArrayList<String> getPrivilage() {
        return privilage;
    }

    public String getPermission() {
        return permission;
    }

    public Object getSessionId() {
        return sessionId;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public String getPushId() {
        return pushId;
    }

    public String getPushOs() {
        return pushOs;
    }
}
