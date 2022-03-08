package com.example.digitallibraryadmin.ApiLibrary;

import java.util.Date;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
public class Chapter {
    public int chapterId;
    public String chapterName;
    public String topicCount;
    public String notesCount;
    public String videoCount;
    public String quesBankCount;
    public int chapterNo;

    public int id;
    public String name;
    public boolean status;
    public boolean deleted;
    public Date updatedAt;
    public Date createdAt;

    public int getChapterId() {
        return chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getTopicCount() {
        return topicCount;
    }

    public String getNotesCount() {
        return notesCount;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public String getQuesBankCount() {
        return quesBankCount;
    }

    public int getChapterNo() {
        return chapterNo;
    }
}
