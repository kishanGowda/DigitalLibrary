package com.example.digitallibraryadmin.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.DeleteTopicRequest;
import com.example.digitallibraryadmin.ApiLibrary.DeleteTopicResponse;
import com.example.digitallibraryadmin.ApiLibrary.GetLibraryResponse;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.Fragment.Edit;
import com.example.digitallibraryadmin.Fragment.PdfReader;
import com.example.digitallibraryadmin.Fragment.PdfReaderNote;
import com.example.digitallibraryadmin.ModelClass.LecturerModel;
import com.example.digitallibraryadmin.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LecturerAdapter extends RecyclerView.Adapter<LecturerAdapter.MyviewHolder> {
    ArrayList<LecturerModel> lecturerModels;
    Context context;
    String pdfFile;
    BottomSheetDialog bt;
    Retrofit retrofit;
    LoginService loginService;

    private OnItemClickListener onItemClickListener;
    GetLibraryResponse getLibraryResponse;
    String standardName,sectionName,subjectName,chapterName,topicName;
    int standardId,subjectId,chapterId,topicId;


    public LecturerAdapter(ArrayList<LecturerModel> lecturerModels, Context context, GetLibraryResponse getLibraryResponse, String standardName, String sectionName, String subjectName, String chapterName, String topicName, int standardId, int subjectId, int chapterId, int topicId) {
        this.lecturerModels = lecturerModels;
        this.context = context;
       this.getLibraryResponse=getLibraryResponse;
       this.standardName=standardName;
       this.sectionName=sectionName;
       this.subjectName=subjectName;
       this.chapterName=chapterName;
       this.topicName=topicName;
       this.standardId=standardId;
       this.subjectId=subjectId;
       this.chapterId=chapterId;
       this.topicId=topicId;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecturer_note_item, parent, false);
        return new MyviewHolder(view,onItemClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position) {
        LecturerModel modal = lecturerModels.get(position);
        holder.edit.setImageResource(modal.getEdit());
        holder.content.setText(modal.getContent());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt=new BottomSheetDialog(context, androidx.appcompat.R.style.Base_Theme_AppCompat);
                bt.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bt.getWindow().getAttributes().windowAnimations=R.style.ForBottom;
                view= LayoutInflater.from(context).inflate(R.layout.edit,null);
                bt.setContentView(view);
                bt.setCanceledOnTouchOutside(true);


                TextView edit=bt.findViewById(R.id.edit_topic);
                TextView delete=bt.findViewById(R.id.delete_topic);
                delete.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {

                                                      apiInit();
                                                      DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(Integer.valueOf(modal.getId()),1);
                                                      Call<DeleteTopicResponse> call = loginService.deleteCall(deleteTopicRequest);
                                                      call.enqueue(new Callback<DeleteTopicResponse>() {
                                                          @Override
                                                          public void onResponse(Call<DeleteTopicResponse> call, Response<DeleteTopicResponse> response) {
                                                              if (!response.isSuccessful()) {
                                                                  Toast.makeText(context, response.code(), Toast.LENGTH_LONG).show();
                                                              }
                                                              DeleteTopicResponse deleteTopicResponse = response.body();
                                                              Toast.makeText(context,"Topic deleted successfully!", Toast.LENGTH_LONG).show();

                                                          }

                                                          @Override
                                                          public void onFailure(Call<DeleteTopicResponse> call, Throwable t) {
                                                              Toast.makeText(edit.getContext(), "Error :(", Toast.LENGTH_LONG).show();
                                                          }
                                                      });



                                              }
                                          }
                );

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment fragment=new Edit();
                        Log.i("subl11",String.valueOf(subjectName));
                        Log.i("tnl",String.valueOf(topicName));
                        Log.i("cnl",String.valueOf(chapterName));
                        Log.i("sel",String.valueOf(sectionName));
                        Log.i("stbl",String.valueOf(standardName));
                        Bundle a=new Bundle();
                        a.putString("position3",String.valueOf(position));
                        a.putString("subjectName3",String.valueOf(subjectName));
                        a.putString("topicName3",String.valueOf(topicName));
                        a.putString("chapterName3",String.valueOf(chapterName));
                        a.putString("sectionName3",String.valueOf(sectionName));
                        a.putString("title3",String.valueOf(modal.getContent()));
                        a.putString("standardName3",String.valueOf(standardName));
                        a.putString("standardId",String.valueOf(standardId));
                        a.putString("subjectId",String.valueOf(subjectId));
                        a.putString("chapterId",String.valueOf(chapterId));
                        a.putString("topicId",String.valueOf(topicId));
                      fragment.setArguments(a);
                        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
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
                        bt.dismiss();
                    }
                });
                bt.show();
            }
        });
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
                args.putString("questionName",String.valueOf(modal.getContent()));
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
        return lecturerModels.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        ImageView edit;
        TextView content;

        public MyviewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            edit = itemView.findViewById(R.id.edit_lecturer_note);
            content = itemView.findViewById(R.id.lecturer_note_text);
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
        public interface OnItemClickListener{
            void onItemClickListener(int position);
        }
        public void setOnItemClickListener(OnItemClickListener listener){
            onItemClickListener=  listener;
        }
    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }
    }

