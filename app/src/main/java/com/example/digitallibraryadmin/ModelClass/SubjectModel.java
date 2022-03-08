package com.example.digitallibraryadmin.ModelClass;

public class SubjectModel {
    String subjectName,chapters;
    int ntsCount,vdoCount,quesCount,subjectId,standardId;

    public SubjectModel(String subjectName, String chapters, int ntsCount, int vdoCount, int quesCount,int subjectId,int standardId) {
        this.subjectName = subjectName;
        this.chapters = chapters;
        this.ntsCount = ntsCount;
        this.vdoCount = vdoCount;
        this.quesCount = quesCount;
        this.subjectId=subjectId;
        this.standardId=standardId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getChapters() {
        return chapters;
    }

    public int getNtsCount() {
        return ntsCount;
    }

    public int getVdoCount() {
        return vdoCount;
    }

    public int getQuesCount() {
        return quesCount;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStandardId() {
        return standardId;
    }
}
