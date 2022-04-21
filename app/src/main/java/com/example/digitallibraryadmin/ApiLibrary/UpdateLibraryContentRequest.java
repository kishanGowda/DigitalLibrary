package com.example.digitallibraryadmin.ApiLibrary;


public class UpdateLibraryContentRequest {

    int subjectId,chapterId,standardId,topicId,id;
    String title;

    public UpdateLibraryContentRequest(int subjectId, int chapterId, String title, int standardId, int topicId, int id) {
        this.subjectId = subjectId;
        this.chapterId = chapterId;
        this.title = title;
        this.standardId = standardId;
        this.topicId = topicId;
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getTitle() {
        return title;
    }

    public int getStandardId() {
        return standardId;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getId() {
        return id;
    }

}
