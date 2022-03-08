package com.example.digitallibraryadmin.ModelClass;

public class QuestionModel {
    private int imageView;
    String infoText;

    public QuestionModel(int imageView, String infoText) {
        this.imageView = imageView;
        this.infoText = infoText;
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
}
