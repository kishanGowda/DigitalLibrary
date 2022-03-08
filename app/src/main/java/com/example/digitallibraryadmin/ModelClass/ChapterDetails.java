package com.example.digitallibraryadmin.ModelClass;

public class ChapterDetails {
    String chapterNum,topicNum,aboutChapter;
    int vdoCount,noteCount,qCount,chapterId;

    public ChapterDetails(String chapterNum, String topicNum, String aboutChapter, int vdoCount, int noteCount, int qCount,int chapterId) {
        this.chapterNum = chapterNum;
        this.topicNum = topicNum;
        this.aboutChapter = aboutChapter;
        this.vdoCount = vdoCount;
        this.noteCount = noteCount;
        this.qCount = qCount;
        this.chapterId=chapterId;
    }

    public String getChapterNum() {
        return chapterNum;
    }

    public String getTopicNum() {
        return topicNum;
    }

    public String getAboutChapter() {
        return aboutChapter;
    }

    public int getVdoCount() {
        return vdoCount;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public int getChapterId() {
        return chapterId;
    }

    public int getqCount() {
        return qCount;

    }
}
