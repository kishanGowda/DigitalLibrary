package com.example.digitallibraryadmin.ApiLibrary;

import java.util.ArrayList;

public class AddTeacherRequestPost {
    public ArrayList<Integer> teacherIds;
    public int subjectId;
    public int standardId;
    public ArrayList<Integer> deletedTeacherIds;

    public AddTeacherRequestPost(ArrayList<Integer> teacherIds, int subjectId, int standardId, ArrayList<Integer> deletedTeacherIds) {
        this.teacherIds = teacherIds;
        this.subjectId = subjectId;
        this.standardId = standardId;
        this.deletedTeacherIds = deletedTeacherIds;
    }

    public ArrayList<Integer> getTeacherIds() {
        return teacherIds;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStandardId() {
        return standardId;
    }

    public ArrayList<Integer> getDeletedTeacherIds() {
        return deletedTeacherIds;
    }
}
