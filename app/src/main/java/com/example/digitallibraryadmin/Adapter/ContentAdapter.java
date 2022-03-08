package com.example.digitallibraryadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibraryadmin.ModelClass.ContentModel;
import com.example.digitallibraryadmin.R;


import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {
    ArrayList<ContentModel> contentModels;
    Context context;

    public ContentAdapter(ArrayList<ContentModel> contentModels, Context context) {
        this.contentModels = contentModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_of_topic_item, parent, false);
        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ContentModel modal = contentModels.get(position);
        holder.count.setText(String.valueOf(modal.getCount()));
        holder.imageView.setImageResource(modal.getImage());
        holder.subject.setText(modal.getDes());

    }

    @Override
    public int getItemCount() {
        return contentModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView count,subject;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageForCard2);
            count=itemView.findViewById(R.id.zero);
            subject=itemView.findViewById(R.id.subject_text);
        }
    }
}
