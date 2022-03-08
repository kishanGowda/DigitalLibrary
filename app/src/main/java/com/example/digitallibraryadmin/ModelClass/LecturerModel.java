package com.example.digitallibraryadmin.ModelClass;

public class LecturerModel {
    int edit;
    String content;

    public LecturerModel(int edit, String content) {
        this.edit = edit;
        this.content = content;
    }

    public int getEdit() {
        return edit;
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
