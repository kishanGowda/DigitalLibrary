package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.digitallibraryadmin.Adapter.LecturerAdapter;
import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.Content;
import com.example.digitallibraryadmin.ApiLibrary.GetLibraryResponse;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ModelClass.LecturerModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LecturerNoteFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<LecturerModel> lecturerModels = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    LecturerAdapter adapter;
    View view;
    String subjectName, standardName, topicName, chapterName, sectionName;
    int chapterId, topicId, standardId, subjectId;
    LinearLayout noTopic;
    GetLibraryResponse getLibraryResponse;

    LoginService loginService;
    Retrofit retrofit;
    int length;
    int notes;


    public LecturerNoteFragment(String subjectName, String topicName, String chapterName, String standardName, String sectionName, int chapterId, int topicID, int standardId, int subjectId) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lecturer_note, container, false);

//        chapterId = Integer.valueOf(getArguments().getString("chapterId"));
//        topicId = Integer.valueOf(getArguments().getString("topicId"));
//        standardId = Integer.valueOf(getArguments().getString("standardId"));
//        subjectId = Integer.valueOf(getArguments().getString("subjectId"));
//
//
//        subjectName = getArguments().getString("subjectNameTopic");
//        topicName = getArguments().getString("topicName");
//        chapterName = getArguments().getString("chapterName");
//        standardName = getArguments().getString("standardName");
//        sectionName = getArguments().getString("sectionTopic");
//        Log.i("chapter1", String.valueOf(chapterId));
//        Log.i("topic1", String.valueOf(topicId));
//        Log.i("standard1", String.valueOf(standardId));
//        Log.i("subjecr", String.valueOf(subjectId));
//        Log.i("subl", subjectName);
//        Log.i("tnl", String.valueOf(topicName));
//        Log.i("cnl", chapterName);
//        Log.i("sel", sectionName);
//        Log.i("stbl", standardName);
        noTopic = view.findViewById(R.id.no_topic_avialbale);


        //   forlecturer();
        apiInit();
        getLibrary();
        //buildR();


        return view;

    }

    public void apiInit() {
        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();
    }




    public void getLibrary() {
        Call<GetLibraryResponse> call = loginService.getLibraryCall_notes(topicId, standardId, chapterId, "lecture-notes");
        call.enqueue(new Callback<GetLibraryResponse>() {
            @Override
            public void onResponse(Call<GetLibraryResponse> call, Response<GetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                getLibraryResponse = response.body();
                ArrayList<Content> contents = getLibraryResponse.contents;
                length = 0;
                int s=Integer.valueOf(getLibraryResponse.totalCount.get(0).notesCount);
                length = contents.size();
                Log.i("si", String.valueOf(s));

                if (s == 0) {
                    noTopic.setVisibility(View.VISIBLE);
                } else {
                    for (int i = 0; i <= s - 1; i++) {
                        lecturerModels.add(new LecturerModel(R.drawable.ic_baseline_more_vert_24, String.valueOf(getLibraryResponse.contents.get(i).title),getLibraryResponse.contents.get(i).id,getLibraryResponse.contents.get(i).file));
                    }

                }
                buildR();
                Log.i("rSize", String.valueOf(lecturerModels.size()));
            }

            @Override
            public void onFailure(Call<GetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void buildR() {
        recyclerView = view.findViewById(R.id.lecturernotervv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LecturerAdapter(lecturerModels, getContext(), getLibraryResponse, standardName, sectionName, subjectName, chapterName, topicName, standardId, subjectId, chapterId, topicId);
        recyclerView.setAdapter(adapter);

    }
}