package com.example.digitallibraryadmin.ModelClass;

public class QuestionModel {
    private int imageView;
    String infoText;
    int id;
    String file;

    public QuestionModel(int imageView, String infoText,int id,String file) {
        this.imageView = imageView;
        this.infoText = infoText;
        this.id=id;
        this.file=file;

    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public int getId() {
        return id;
    }

    public String getFile() {
        return file;
    }
}
