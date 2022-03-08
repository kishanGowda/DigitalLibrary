package com.example.digitallibraryadmin.ApiLibrary;

public class EditTopicRequest {
    public int topicId;
    public String topicName;

    public EditTopicRequest(int topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }

    public int getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }
}