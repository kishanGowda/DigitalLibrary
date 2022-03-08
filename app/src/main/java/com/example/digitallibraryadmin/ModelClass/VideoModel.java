package com.example.digitallibraryadmin.ModelClass;

public class VideoModel {
    private int imageView,edit;
    String infoText,timimgs;

    public VideoModel(int imageView, int edit, String infoText, String timimgs) {
        this.imageView = imageView;
        this.edit = edit;
        this.infoText = infoText;
        this.timimgs = timimgs;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public int getEdit() {
        return edit;
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getTimimgs() {
        return timimgs;
    }

    public void setTimimgs(String timimgs) {
        this.timimgs = timimgs;
    }
}
