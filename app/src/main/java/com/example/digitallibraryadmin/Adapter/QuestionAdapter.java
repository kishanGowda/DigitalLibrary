package com.example.digitallibraryadmin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibraryadmin.ApiLibrary.GetLibraryResponse;
import com.example.digitallibraryadmin.Fragment.ChapterFragment;
import com.example.digitallibraryadmin.Fragment.PdfReader;
import com.example.digitallibraryadmin.ModelClass.QuestionModel;
import com.example.digitallibraryadmin.R;


import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyviewHolder>{
    ArrayList<QuestionModel>questionModels;
    Context context;
    private OnItemClickListener onItemClickListener;
    int chapterId,standardId,topicId;
    GetLibraryResponse getLibraryResponse;
String pdfFile;


    public QuestionAdapter(ArrayList<QuestionModel> questionModels, Context context, int chapterId, int standardId, int topicID, GetLibraryResponse getLibraryResponse) {
        this.questionModels=questionModels;
        this.context=context;
        this.chapterId=chapterId;
        this.standardId=standardId;
        this.topicId=topicID;
        this.getLibraryResponse=getLibraryResponse;

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_bank_item,parent,false);
        MyviewHolder cvh = new MyviewHolder(view,onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position) {
        QuestionModel currentCards = this.questionModels.get(position);
        holder.edit.setImageResource(currentCards.getImageView());
        holder.content.setText(currentCards.getInfoText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("position", String.valueOf(position));
                Fragment fragment = new PdfReader();
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                Bundle args = new Bundle();
                pdfFile=getLibraryResponse.contents.get(position).file;
                args.putString("questionPosition",String.valueOf(pdfFile));
                args.putString("parameter","question-bank");
                args.putString("questionName",String.valueOf(currentCards.getInfoText()));
                args.putString("standardIdQuestion",String.valueOf(standardId));
                args.putString("topicIdQuestion",String.valueOf(topicId));
                args.putString("chapterIdQuestion",String.valueOf(chapterId));
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.your_placeholder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView edit;

        public MyviewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            content=itemView.findViewById(R.id.question_text_);
            edit=itemView.findViewById(R.id.edit_question);
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
        onItemClickListener=  listener;
    }
}