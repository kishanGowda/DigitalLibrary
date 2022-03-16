package com.example.digitallibraryadmin.ModelClass;

public class VideoModel {
    private int imageView,edit;
    String infoText,timimgs,link,file,published;
    int id;

    public VideoModel(int imageView, int edit, String infoText, String timimgs,String link,String file,String published,int id) {
        this.imageView = imageView;
        this.edit = edit;
        this.infoText = infoText;
        this.timimgs = timimgs;
        this.link=link;
        this.file=file;
        this.published=published;
        this.id=id;

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

    public String getLink() {
        return link;
    }

    public String getFile() {
        return file;
    }

    public String getPublished() {
        return published;
    }

    public int getId() {
        return id;
    }
}
