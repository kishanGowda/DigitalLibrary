package com.example.digitallibraryadmin.Adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibraryadmin.Fragment.ChapterFragment;
import com.example.digitallibraryadmin.Fragment.TopicFragment;
import com.example.digitallibraryadmin.ModelClass.ChapterDetails;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;

public class ChapterDetailsAdapter extends RecyclerView.Adapter<ChapterDetailsAdapter.MyViewHolber> {
    ArrayList<ChapterDetails> chapterDetials;
    Context context;
    private OnItemClickListener onItemClickListener;
    int subjectId;
    String standardid;
    String standardName;
    String section;
    String subjectName;


    public ChapterDetailsAdapter(ArrayList<ChapterDetails> chapterDetials, Context context, int subjectId, String standardid, String section2, String standardName2,String subjectName) {
        this.chapterDetials = chapterDetials;
        this.context = context;
        this.subjectId = subjectId;
        this.standardid = standardid;
        section = section2;
        standardName = standardName2;
        this.subjectName=subjectName;
    }

    @NonNull
    @Override
    public MyViewHolber onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_mathematics_layout, parent, false);
        MyViewHolber cvh = new MyViewHolber(view, onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolber holder, int position) {
        ChapterDetails currentCards = chapterDetials.get(position);
        holder.qCount.setText(String.valueOf(currentCards.getqCount()));
        holder.chapterNum.setText(String.valueOf(currentCards.getChapterNum()));
        holder.vdoCount.setText(String.valueOf(currentCards.getVdoCount()));
        holder.noteCount.setText(String.valueOf(currentCards.getNoteCount()));
        holder.topicNum.setText(String.valueOf(currentCards.getTopicNum()));
        holder.aboutChapter.setText(currentCards.getAboutChapter());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chapterId= Integer.valueOf(currentCards.getChapterId());
                int standardId= Integer.valueOf(standardid);
                String topicName=currentCards.getAboutChapter();
                Fragment fragment = new TopicFragment(chapterId,standardId,subjectId,topicName,standardName,section,subjectName);
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                Bundle args = new Bundle();
                Log.i("onClickStandard", standardName);
                Log.i("OnclickStandard", section);
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        );
                fragmentTransaction.replace(R.id.your_placeholder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return chapterDetials.size();
    }


    public class MyViewHolber extends RecyclerView.ViewHolder {
        public TextView chapterNum, topicNum, aboutChapter,
                vdoCount, noteCount, qCount;

        public MyViewHolber(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            chapterNum = itemView.findViewById(R.id.chapters_in_subject);
            topicNum = itemView.findViewById(R.id.topic_number);
            aboutChapter = itemView.findViewById(R.id.about_chapter);
            noteCount = itemView.findViewById(R.id.video_count_tv_chapter);
            vdoCount = itemView.findViewById(R.id.notes_count_tv_chapter);
            qCount = itemView.findViewById(R.id.question_count_tv_chapter);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClickListener(position);

                        }

                    }
                }
            });


        }
    }
}
