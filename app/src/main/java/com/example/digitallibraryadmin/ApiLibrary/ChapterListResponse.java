package com.example.digitallibraryadmin.ApiLibrary;

import java.util.ArrayList;
import java.util.Date;


// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */




public class ChapterListResponse {
        public int chapterCount;
        public ArrayList<Chapter> chapters;
        public ArrayList<AddedTeacher> addedTeachers;

    public int getChapterCount() {
        return chapterCount;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public ArrayList<AddedTeacher> getAddedTeachers() {
        return addedTeachers;
    }
}





