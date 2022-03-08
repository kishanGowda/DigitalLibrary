package com.example.digitallibraryadmin.ApiLibrary;

//public class DashBoardOne {
//        public int totalSize;
//        public SizeOfContents sizeOfContents;
//        public long unusedSpace;
//        public boolean videoPremiumFeature;
//        public int datalength;
//        public int standardslength;
//        public ArrayList<Datum> data;
//        public ArrayList<Standard> standards;
//        public TotalCount totalCount;
//        public ArrayList<AnalyticsData> analyticsData;
//
//        public int getTotalSize() {
//                return totalSize;
//        }
//
//        public SizeOfContents getSizeOfContents() {
//                return sizeOfContents;
//        }
//
//        public long getUnusedSpace() {
//                return unusedSpace;
//        }
//
//        public boolean isVideoPremiumFeature() {
//                return videoPremiumFeature;
//        }
//
//        public int getDatalength() {
//                return datalength;
//        }
//
//        public int getStandardslength() {
//                return standardslength;
//        }
//
//        public ArrayList<Datum> getData() {
//                return data;
//        }
//
//        public ArrayList<Standard> getStandards() {
//                return standards;
//        }
//
//        public TotalCount getTotalCount() {
//                return totalCount;
//        }
//
//        public ArrayList<AnalyticsData> getAnalyticsData() {
//                return analyticsData;
//        }
// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class Count {
    public String notesCount;
    public String videoCount;
    public String quesBankCount;
    public int lastWeekLectureNotesCount;
    public int lastWeekVideoCount;
    public int lastWeekQuestionBankCount;
}
