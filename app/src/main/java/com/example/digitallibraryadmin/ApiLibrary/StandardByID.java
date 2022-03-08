package com.example.digitallibraryadmin.ApiLibrary;

import java.util.ArrayList;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */




public class StandardByID {
//    public int totalSize;
//    public SizeOfContents sizeOfContents;
//    public int unusedSpace;
//    public ArrayList<Datum> data;
//    public int lastWeekLectureNotesCount;
//    public int lastWeekVideoCount;
//    public int lastWeekQuestionBankCount;
//    public ArrayList<Subject> subjects;
//    public ArrayList<AnalyticsData> analyticsData;
//    public ArrayList<ActiveTime> activeTime;
//
//    public int getTotalSize() {
//        return totalSize;
//    }
//
//    public SizeOfContents getSizeOfContents() {
//        return sizeOfContents;
//    }
//
//    public int getUnusedSpace() {
//        return unusedSpace;
//    }
//
//    public ArrayList<Datum> getData() {
//        return data;
//    }
//
//    public int getLastWeekLectureNotesCount() {
//        return lastWeekLectureNotesCount;
//    }
//
//    public int getLastWeekVideoCount() {
//        return lastWeekVideoCount;
//    }
//
//    public int getLastWeekQuestionBankCount() {
//        return lastWeekQuestionBankCount;
//    }
//
//    public ArrayList<Subject> getSubjects() {
//        return subjects;
//    }
//
//    public ArrayList<AnalyticsData> getAnalyticsData() {
//        return analyticsData;
//    }
//
//    public ArrayList<ActiveTime> getActiveTime() {
//        return activeTime;
//    }

    public int totalSize;
    public SizeOfContents sizeOfContents;
    public long unusedSpace;
    public ArrayList<Datum> data;
    public int lastWeekLectureNotesCount;
    public int lastWeekVideoCount;
    public int lastWeekQuestionBankCount;
    public ArrayList<Subject> subjects;
    public ArrayList<AnalyticsData> analyticsData;
    public ArrayList<ActiveTime> activeTime;

    public int getTotalSize() {
        return totalSize;
    }

    public SizeOfContents getSizeOfContents() {
        return sizeOfContents;
    }

    public long getUnusedSpace() {
        return unusedSpace;
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public int getLastWeekLectureNotesCount() {
        return lastWeekLectureNotesCount;
    }

    public int getLastWeekVideoCount() {
        return lastWeekVideoCount;
    }

    public int getLastWeekQuestionBankCount() {
        return lastWeekQuestionBankCount;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public ArrayList<AnalyticsData> getAnalyticsData() {
        return analyticsData;
    }

    public ArrayList<ActiveTime> getActiveTime() {
        return activeTime;
    }
}



