package com.example.digitallibraryadmin.ModelClass;

public class AddTeacherModel {
    String teacherName;
    int cancel;
    int id;

    public AddTeacherModel(String teacherName, int cancel, int id) {
        this.teacherName = teacherName;
        this.cancel = cancel;
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getCancel() {
        return cancel;
    }

    public int getId() {
        return id;
    }
}
