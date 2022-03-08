package com.example.digitallibraryadmin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibraryadmin.Fragment.SubjectFragment;
import com.example.digitallibraryadmin.ModelClass.ChildModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;


public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder> {
    public ArrayList<ChildModel> childModelArrayList;
    Context cxt;
    private  OnItemClickListener onItemClickListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView notesCount,videoCount,quesCount,course,section;



        public MyViewHolder(View itemView,OnItemClickListener listener) {
            super(itemView);
            notesCount = itemView.findViewById(R.id.notes_count);
            videoCount = itemView.findViewById(R.id.video_count);
            quesCount = itemView.findViewById(R.id.question_count);
            course = itemView.findViewById(R.id.course);
            section = itemView.findViewById(R.id.section);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAbsoluteAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClickListener(position);

                        }

                    }
                }
            });


        }
    }
    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener=listener;
    }

    public ChildRecyclerViewAdapter(ArrayList<ChildModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_items, parent, false);
        return new MyViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ChildModel currentItem = childModelArrayList.get(position);

        holder.course.setText(childModelArrayList.get(position).getCourse());
        holder.section.setText(String.valueOf(childModelArrayList.get(position).getSection()));
        holder.notesCount.setText(String.valueOf(childModelArrayList.get(position).getNotesCount()));
        holder.videoCount.setText(String.valueOf(childModelArrayList.get(position).getVideoCount()));
        holder.quesCount.setText(String.valueOf(childModelArrayList.get(position).getQuesCount()));



        //creating new activity

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new SubjectFragment();
                FragmentManager fragmentManager = ((FragmentActivity) cxt).getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putString("Position",String.valueOf(childModelArrayList.get(position).getStandardId()));
                args.putString("section",childModelArrayList.get(position).getSection());
                args.putString("standardName",String.valueOf(childModelArrayList.get(position).getStandard()));
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


    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }
}