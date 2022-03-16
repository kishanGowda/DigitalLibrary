package com.example.digitallibraryadmin.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibraryadmin.Fragment.EditBottomSheet;
import com.example.digitallibraryadmin.ModelClass.Card1Hori;
import com.example.digitallibraryadmin.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class Card1HoriAdapter extends RecyclerView.Adapter<Card1HoriAdapter.CardViewHolder> {

    ArrayList<Card1Hori> returned;
    Context context;

    public Card1HoriAdapter(ArrayList<Card1Hori> returned) {
        this.returned = returned;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card1hori ,parent,false);
        CardViewHolder cvh = new CardViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card1Hori currentCards = this.returned.get(position);
        holder.imageForCard.setImageResource(currentCards.getImageForCard());
        holder.zeroText.setText(currentCards.getZeroText());
        holder.subjectText.setText(currentCards.getSubjectText());
        holder.lastWeek.setVisibility(View.VISIBLE);
        if (currentCards.getLastWeek().charAt(0) == '-'){
            holder.lastWeek.setTextColor(Color.parseColor("#FF414D"));
        }
//        else {
//            holder.lastWeek.setTextColor(Color.parseColor(""));
//        }
        holder.lastWeek.setText(currentCards.getLastWeek());}


    @Override
    public int getItemCount() {
        return returned.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        ImageView imageForCard;
        TextView zeroText,subjectText;
        TextView lastWeek;



        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageForCard=itemView.findViewById(R.id.imageForCard);
            zeroText=itemView.findViewById(R.id.zeroText);
            subjectText=itemView.findViewById(R.id.subjectText);
            lastWeek=itemView.findViewById(R.id.last_count_textv);

        }
    }
}



