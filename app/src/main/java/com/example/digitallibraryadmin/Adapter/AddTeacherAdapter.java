package com.example.digitallibraryadmin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibraryadmin.ModelClass.AddTeacherModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;

public class AddTeacherAdapter extends RecyclerView.Adapter<AddTeacherAdapter.ViewHolder> {
    Context context;
    AddTeacherModel modal;
    private OnItemClickListener onItemClickListener;
    ArrayList<AddTeacherModel> addTeacherSuggets;
    public AddTeacherAdapter(Context context, ArrayList<AddTeacherModel> addTeacherSuggets, int pos) {
        this.context=context;
        this.addTeacherSuggets=addTeacherSuggets;
    }

    @NonNull
    @Override
    public AddTeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggest_item, parent, false);
        ViewHolder cvh = new ViewHolder(view,onItemClickListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AddTeacherAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            modal = addTeacherSuggets.get(position);
        holder.name.setText(String.valueOf(modal.getTeacherName()));
        holder.cancel.setImageResource(modal.getCancel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("s",String.valueOf(position));
            }
        });

    }


    public interface OnItemClickListener {
        void onItemClickListener(int position);
        void onDeleteClick(int position, String teacherName, int id);
        void onNameClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return addTeacherSuggets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView cancel;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            name=itemView.findViewById(R.id.suggest_name);
            cancel=itemView.findViewById(R.id.cancel_suggest);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onNameClick(position);

                        }

                    }

                }
            });
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
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position,modal.getTeacherName(),modal.getId());

                        }

                    }

                }
            });

        }
    }
}
