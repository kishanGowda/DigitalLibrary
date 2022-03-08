package com.example.digitallibraryadmin.ApiLibrary;

public class DeleteTopicRequest {
    public int topicId;
        public int deleted;

    public DeleteTopicRequest(int topicId, int deleted) {
        this.topicId = topicId;
        this.deleted = deleted;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getDeleted() {
        return deleted;
    }
}
