package com.example.digitallibraryadmin.ModelClass;

public class AddTeacherModel {
    String teacherName;
    int cancel;

    public AddTeacherModel(String teacherName, int cancel) {
        this.teacherName = teacherName;
        this.cancel = cancel;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getCancel() {
        return cancel;
    }
}
