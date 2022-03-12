package com.example.digitallibraryadmin.ModelClass;

public class ContentModel {
    int image,count,id;
    String des;


    public ContentModel(int image, int count, String des) {
        this.image = image;
        this.count = count;
        this.des = des;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getId() {
        return id;
    }

}
