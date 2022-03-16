package com.example.digitallibraryadmin.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.digitallibraryadmin.ModelClass.QuestionModel;
import com.example.digitallibraryadmin.R;
import com.example.digitallibraryadmin.WebView;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyviewHolder>{
    ArrayList<QuestionModel>questionModels;
    Context context;
    private OnItemClickListener onItemClickListener;
    int chapterId,standardId,topicId;
    GetLibraryResponse getLibraryResponse;
String pdfFile;
BottomSheetDialog bt;
Retrofit retrofit;
LoginService loginService;
    String subjectName, standardName, topicName, chapterName, sectionName;


    public QuestionAdapter(ArrayList<QuestionModel> questionModels, Context context, int chapterId,
                           int standardId, int topicID, GetLibraryResponse getLibraryResponse,
                           String subjectName, String topicName, String chapterName, String sectionName, String standardName) {
        this.questionModels=questionModels;
        this.context=context;
        this.chapterId=chapterId;
        this.standardId=standardId;
        this.topicId=topicID;
        this.getLibraryResponse=getLibraryResponse;
        this.subjectName=subjectName;
        this.topicName=topicName;
        this.chapterName=chapterName;
        this.sectionName=sectionName;
        this.standardName=standardName;


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
                                                  DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(Integer.valueOf(currentCards.getId()),1);
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
                        String titleName=currentCards.getInfoText();
                        Fragment fragment=new Edit(subjectName,topicName,chapterName,sectionName,titleName,standardName);
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

                String link= "https://docs.google.com/viewer?url="+"https://test-digital-library.s3.ap-south-1.amazonaws.com/"+ currentCards.getFile();
                String title=currentCards.getInfoText();
                Log.i("link",link );
                Intent intent = new Intent(context, WebView.class);
                intent.putExtra("key",link);
                intent.putExtra("title",title);
                context.startActivity(intent);
//                Log.i("position", String.valueOf(position));
//                Fragment fragment = new PdfReader();
//                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
//                Bundle args = new Bundle();
//                pdfFile=getLibraryResponse.contents.get(position).file;
//                args.putString("questionPosition",String.valueOf(pdfFile));
//                args.putString("parameter","question-bank");
//                args.putString("questionName",String.valueOf(currentCards.getInfoText()));
//                args.putString("standardIdQuestion",String.valueOf(standardId));
//                args.putString("topicIdQuestion",String.valueOf(topicId));
//                args.putString("chapterIdQuestion",String.valueOf(chapterId));
//                fragment.setArguments(args);
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.your_placeholder, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
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
    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }
}