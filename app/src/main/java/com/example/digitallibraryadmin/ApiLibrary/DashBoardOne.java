package com.example.digitallibraryadmin.ApiLibrary;

import java.util.ArrayList;
import java.util.Date;

        public class DashBoardOne {
                public CurrentSubscription currentSubscription;
                public int totalSize;
                public SizeOfContents sizeOfContents;
                public long unusedSpace;
                public boolean videoPremiumFeature;
                public int datalength;
                public int standardslength;
                public ArrayList<Datum> data;
                public ArrayList<Standard> standards;
                public TotalCount totalCount;
                public ArrayList<AnalyticsData> analyticsData;

                public DashBoardOne(CurrentSubscription currentSubscription, int totalSize, SizeOfContents sizeOfContents, long unusedSpace, boolean videoPremiumFeature, int datalength, int standardslength, ArrayList<Datum> data, ArrayList<Standard> standards, TotalCount totalCount, ArrayList<AnalyticsData> analyticsData) {
                        this.currentSubscription = currentSubscription;
                        this.totalSize = totalSize;
                        this.sizeOfContents = sizeOfContents;
                        this.unusedSpace = unusedSpace;
                        this.videoPremiumFeature = videoPremiumFeature;
                        this.datalength = datalength;
                        this.standardslength = standardslength;
                        this.data = data;
                        this.standards = standards;
                        this.totalCount = totalCount;
                        this.analyticsData = analyticsData;
                }

                public CurrentSubscription getCurrentSubscription() {
                        return currentSubscription;
                }

                public int getTotalSize() {
                        return totalSize;
                }

                public SizeOfContents getSizeOfContents() {
                        return sizeOfContents;
                }

                public long getUnusedSpace() {
                        return unusedSpace;
                }

                public boolean isVideoPremiumFeature() {
                        return videoPremiumFeature;
                }

                public int getDatalength() {
                        return datalength;
                }

                public int getStandardslength() {
                        return standardslength;
                }

                public ArrayList<Datum> getData() {
                        return data;
                }

                public ArrayList<Standard> getStandards() {
                        return standards;
                }

                public TotalCount getTotalCount() {
                        return totalCount;
                }

                public ArrayList<AnalyticsData> getAnalyticsData() {
                        return analyticsData;
                }
        }




//        public int totalSize;
//        public SizeOfContents sizeOfContents;
//        public int unusedSpace;
//        public boolean videoPremiumFeature;
//        public int datalength;
//        public int standardslength;
//        public ArrayList<Datum> data;
//        public ArrayList<Standard> standards;
//        public TotalCount totalCount;
//        public ArrayList<AnalyticsData> analyticsData;
//
//        public DashBoardOne(int totalSize, SizeOfContents sizeOfContents, int unusedSpace, boolean videoPremiumFeature, int datalength, int standardslength, ArrayList<Datum> data, ArrayList<Standard> standards, TotalCount totalCount, ArrayList<AnalyticsData> analyticsData) {
//                this.totalSize = totalSize;
//                this.sizeOfContents = sizeOfContents;
//                this.unusedSpace = unusedSpace;
//                this.videoPremiumFeature = videoPremiumFeature;
//                this.datalength = datalength;
//                this.standardslength = standardslength;
//                this.data = data;
//                this.standards = standards;
//                this.totalCount = totalCount;
//                this.analyticsData = analyticsData;
//        }
//
//        public int getTotalSize() {
//                return totalSize;
//        }
//
//        public SizeOfContents getSizeOfContents() {
//                return sizeOfContents;
//        }
//
//        public int getUnusedSpace() {
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




