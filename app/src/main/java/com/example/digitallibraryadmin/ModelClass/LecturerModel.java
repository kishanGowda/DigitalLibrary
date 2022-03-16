package com.example.digitallibraryadmin.ModelClass;

public class LecturerModel {
    int edit;
    String content;
    int id;
    String file;

    public LecturerModel(int edit, String content,int id,String file) {
        this.edit = edit;
        this.content = content;
        this.id=id;
        this.file=file;
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

    public int getId() {
        return id;
    }

    public String getFile() {
        return file;
    }
}
