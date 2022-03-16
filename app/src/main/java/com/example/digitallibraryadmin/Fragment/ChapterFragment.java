package com.example.digitallibraryadmin.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitallibraryadmin.Adapter.ChapterDetailsAdapter;
import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.ChapterListResponse;
import com.example.digitallibraryadmin.ApiLibrary.DashBoardOne;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ModelClass.ChapterDetails;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChapterFragment extends Fragment {

  View view;
  LoginService loginService;
  Retrofit retrofit;
  RecyclerView recyclerView;
  ChapterDetailsAdapter chapterDetailsAdapter;
  ArrayList<ChapterDetails> chapterdetails;
  RecyclerView.LayoutManager layoutManager;
  String subjectId;
  LinearLayout linearLayout;
  String standardName,section;
  Button addTeacher,later;




  //accessing imageview for adding te teacher
  ImageView setting;
  //back
  ImageView back;
  String standardId;
  String subjectName;

  public ChapterFragment(String subjectId, String subjectName, String standardId, String section, String standard) {
        this.subjectId=subjectId;
        this.subjectName=subjectName;
        this.standardId=standardId;
       this. section=section;
       standardName=standard;


  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_chapter, container, false);
    TransitionInflater inflate = TransitionInflater.from(requireContext());
    setExitTransition(inflate.inflateTransition(R.transition.fade));

    Log.i("subject", String.valueOf(subjectId));
    Log.i("standardnameChapter", String.valueOf(standardName));
    Log.i("sectionchapter", String.valueOf(section));
    Log.i("name", String.valueOf(subjectName));
    Log.i("standardId", standardId);
    setting=view.findViewById(R.id.setting_subject);
    back=view.findViewById(R.id.back_chapter_detials);
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getActivity().getSupportFragmentManager().popBackStack();
      }
    });


// Load and use views afterwards
    TextView tv1 =view.findViewById(R.id.subject_name);
    tv1.setText(subjectName);

    apiInit();
    createCard();
    SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayoutChapter);
    swipeRefreshLayout.setOnRefreshListener(
            new SwipeRefreshLayout.OnRefreshListener() {
              @Override
              public void onRefresh() {
              createCard();
                swipeRefreshLayout.setRefreshing(false);
              }
            }
    );



    setting.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        settings();
      }
    });


    return view;
  }

  public void apiInit() {
    retrofit = ApiClient.getRetrofit();
    loginService = ApiClient.getApiService();
  }

  public void createCard() {
    chapterdetails = new ArrayList<>();
    Call<ChapterListResponse> call = loginService.chapterListCall(Integer.valueOf(subjectId), Integer.valueOf(standardId));

    call.enqueue(new Callback<ChapterListResponse>() {
      @Override
      public void onResponse(Call<ChapterListResponse> call, Response<ChapterListResponse> response) {
        if (!response.isSuccessful()) {
          Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
        }
        ChapterListResponse chapterListResponse = response.body();
        int length = chapterListResponse.chapters.size();
        if(length==0){
         linearLayout=view.findViewById(R.id.no_chapter_avialbale_);
         linearLayout.setVisibility(View.VISIBLE);

        }
        else {
          for (int i = 0; i <= length - 1; i++) {
            chapterdetails.add(new ChapterDetails(String.valueOf(chapterListResponse.chapters.get(i).getChapterNo()),
                    String.valueOf(chapterListResponse.chapters.get(i).getTopicCount()),
                    String.valueOf(chapterListResponse.chapters.get(i).getChapterName()),
                    Integer.valueOf(chapterListResponse.chapters.get(i).getNotesCount()),
                    Integer.valueOf(chapterListResponse.chapters.get(i).getVideoCount()),
                    Integer.valueOf(chapterListResponse.chapters.get(i).getQuesBankCount()),
                    Integer.valueOf(chapterListResponse.chapters.get(i).getChapterId())));
          }
        }
        buildRecyclerView();
      }
      @Override
      public void onFailure(Call<ChapterListResponse> call, Throwable t) {

      }
    });
  }
  public void buildRecyclerView() {
    recyclerView = view.findViewById(R.id.subject_rvv);
    recyclerView.setHasFixedSize(true);
    layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
    recyclerView.setLayoutManager(layoutManager);
    chapterDetailsAdapter = new ChapterDetailsAdapter(chapterdetails, getContext(),Integer.valueOf(subjectId),standardId,section,standardName,subjectName);
    Log.i("section2",section);
    Log.i("standard2",standardName);
    recyclerView.setAdapter(chapterDetailsAdapter);
  }
  public void settings(){
    Dialog dialog = new Dialog(getContext());
    dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
   dialog.getWindow().getAttributes().windowAnimations=R.style.MyDialogAni;
   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.setCancelable(false);
    dialog.setContentView(R.layout.dailog_add_teacher);
    addTeacher=dialog.findViewById(R.id.add_teacher_button_dialog);
    later=dialog.findViewById(R.id.later_button_dialog);
    later.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog.dismiss();
      }
    });
    addTeacher.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog.dismiss();
        Fragment fragment = new AddTeacher(Integer.valueOf(standardId),Integer.valueOf(subjectId),subjectName,standardName,section);
        FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
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

    later.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog.dismiss();
      }
    });
    dialog.show();
  }


}