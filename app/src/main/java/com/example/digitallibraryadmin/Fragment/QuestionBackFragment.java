package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.digitallibraryadmin.Adapter.QuestionAdapter;
import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.Content;
import com.example.digitallibraryadmin.ApiLibrary.GetLibraryResponse;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ModelClass.LecturerModel;
import com.example.digitallibraryadmin.ModelClass.QuestionModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class QuestionBackFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    QuestionAdapter adapter;
    ArrayList<QuestionModel> questionModels;
    LinearLayoutManager layoutManager;
//    int chapterId,topicID,standardId;
    LoginService loginService;
    Retrofit retrofit;
    LinearLayout noQuestion;
    String subjectName, standardName, topicName, chapterName, sectionName;
    int chapterId, topicId, standardId, subjectId;
    GetLibraryResponse getLibraryResponse;


    public QuestionBackFragment(String subjectName, String topicName, String chapterName, String standardName, String sectionName, int chapterId, int topicID, int standardId, int subjectId) {
// Required empty public constructor
        this.subjectName=subjectName;
        this.topicName=topicName;
        this.chapterName=chapterName;
        this.standardName=standardName;
        this.sectionName=sectionName;
        this.chapterId=chapterId;
        this.topicId=topicID;
        this.standardId=standardId;
        this.subjectId=subjectId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_question_back, container, false);
        apiInit();

        noQuestion=view.findViewById(R.id.no_question_avialbale);

//
//        chapterId = Integer.valueOf(getArguments().getString("chapterIdQ"));
//        topicID = Integer.valueOf(getArguments().getString("topicIdQ"));
//        standardId = Integer.valueOf(getArguments().getString("standardIdQ"));
        Log.i("chapter2", String.valueOf(chapterId));
        Log.i("topic2", String.valueOf(topicId));
        Log.i("standard2", String.valueOf(standardId));
        getLibrary();
        return view;

    }
    public void apiInit() {
        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();
    }





    public void getLibrary() {
        Call<GetLibraryResponse> call = loginService.getLibraryCall_notes(topicId,standardId,chapterId,"question-bank");
        call.enqueue(new Callback<GetLibraryResponse>() {
            @Override
            public void onResponse(Call<GetLibraryResponse> call, Response<GetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                getLibraryResponse = response.body();
                ArrayList<Content> contents = getLibraryResponse.contents;
                int length=contents.size();
                Log.i("si",String.valueOf(length));
                if(length==0){
                    noQuestion.setVisibility(View.VISIBLE);
                }
                else {
                    questionModels=new ArrayList<>();
                    for (int i = 0; i <= length - 1; i++) {
                        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,getLibraryResponse.contents.get(i).title));

                    }
                    buildR();
                }
            }
            @Override
            public void onFailure(Call<GetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(),"error", Toast.LENGTH_LONG).show();

            }
        });
    }





//    public void forlecturer(){
//        questionModels=new ArrayList<>();
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//        questionModels.add(new QuestionModel(R.drawable.ic_baseline_more_vert_24,"havdhsvghdvhgsvghadhgsvgdh"));
//
//    }
    public  void buildR(){
        recyclerView=view.findViewById(R.id.question_rvvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuestionAdapter(questionModels,getContext(),chapterId,standardId,topicId,getLibraryResponse);
        recyclerView.setAdapter(adapter);

    }

}