package com.example.digitallibraryadmin.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.digitallibraryadmin.Fragment.LecturerNoteFragment;
import com.example.digitallibraryadmin.Fragment.QuestionBackFragment;
import com.example.digitallibraryadmin.Fragment.VideosFragment;


//public class MyAdapter extends FragmentPagerAdapter {
//    private Context myContext;
//    int totalTabs;
//
//    private int chapterId, topicID, standardId, subjectId;
//    String subjectName, topicName, chapterName, standardName, sectionName;
//
//    //subjectName,topicName,chapterName,standardName,sectionName,chapterId, topicID, standardId,subjectId
//    public MyAdapter(Context context, FragmentManager fm, int totalTabs, String subjectName, String topicName, String chapterName, String standardName, String sectionName, int chapterId, int topicID, int standardId, int subjectId) {
//        super(fm);
//        myContext = context;
//        this.totalTabs = totalTabs;
//        this.subjectName = subjectName;
//        this.topicName = topicName;
//        this.chapterName = chapterName;
//        this.standardName = standardName;
//        this.sectionName = sectionName;
//        this.chapterId = chapterId;
//        this.topicID = topicID;
//        this.standardId = standardId;
//        this.subjectId = subjectId;
//
//    }
//}
    // this is for fragment tabs
//    @Override
//    public Fragment getItem(int position) {
//        Log.i("TAG", String.valueOf("getItem: "+subjectName));
////        switch (position) {
////            case 0:
////            //    LecturerNoteFragment lectureNotes = new LecturerNoteFragment(getParentFragmentManager(), tabLayout.getTabCount(), subjectName, topicName, chapterName, standardName, sectionName, chapterId, topicID, standardId, subjectId);
////                Bundle args = new Bundle();
////                args.putString("chapterId", String.valueOf(chapterId));
////                args.putString("standardId", String.valueOf(standardId));
////                args.putString("topicId", String.valueOf(topicID));
////                args.putString("chapterName",String.valueOf(chapterName));
////                args.putString("standardName",String.valueOf(standardName));
////                args.putString("sectionTopic",String.valueOf(sectionName));
////                args.putString("topicName",String.valueOf(topicName));
////                args.putString("subjectNameTopic",String.valueOf(subjectName));
////                args.putString("subjectId",String.valueOf(subjectId));
////                lectureNotes.setArguments(args);
////                return lectureNotes;
////
////            case 1:
////                VideosFragment videosFragment=new VideosFragment();
////                Bundle args1 = new Bundle();
////                                args1.putString("chapterIdV", String.valueOf(chapterId));
////                                args1.putString("standardIdV", String.valueOf(standardId));
////                                args1.putString("topicIdV", String.valueOf(topicID));
////                                videosFragment.setArguments(args1);
////                return videosFragment;
////                case 2:
////                QuestionBackFragment questionBackFragment = new QuestionBackFragment();
////                Bundle args2 = new Bundle();
////                                args2.putString("chapterIdQ", String.valueOf(chapterId));
////                                args2.putString("standardIdQ", String.valueOf(standardId));
////                                args2.putString("topicIdQ", String.valueOf(topicID));
////                                args2.putString("chapterName",String.valueOf(chapterName));
////                                args2.putString("standardName",String.valueOf(standardName));
////                                args2.putString("sectionTopic",String.valueOf(sectionName));
////                                args2.putString("subjectNameTopic",String.valueOf(subjectName));
////                                args2.putString("subjectId",String.valueOf(subjectId));
////                                questionBackFragment.setArguments(args2);
////                return questionBackFragment;
////            default:
////                return null;
////        }
//    }
//    // this counts total number of tabs
//    @Override
//    public int getCount() {
//        return totalTabs;
//    }
//}
