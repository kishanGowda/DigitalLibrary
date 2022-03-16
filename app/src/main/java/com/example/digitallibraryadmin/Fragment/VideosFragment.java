package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.digitallibraryadmin.Adapter.VideosAdapter;
import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.Content;
import com.example.digitallibraryadmin.ApiLibrary.GetLibraryResponse;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ModelClass.VideoModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VideosFragment extends Fragment {
    View view;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Retrofit retrofit;
    LoginService loginService;
    ArrayList<VideoModel> videoModel;
    VideosAdapter adapter1;
    LinearLayout novideo;
    String subjectName, standardName, topicName, chapterName, sectionName;
    int chapterId, topicId, standardId, subjectId;

    public VideosFragment(String subjectName, String topicName, String chapterName, String standardName, String sectionName, int chapterId, int topicID, int standardId, int subjectId) {
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
        view = inflater.inflate(R.layout.fragment_videos, container, false);
        Log.i("chapterv", String.valueOf(chapterId));
        Log.i("topicv", String.valueOf(topicId));
        Log.i("standardv", String.valueOf(standardId));
       novideo=view.findViewById(R.id.no_video_avialbale);
        apiInit();
        getLibrary();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayoutvideos);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                    apiInit();
                    getLibrary();
                    swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        return view;

    }

    public void apiInit() {
        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();
    }

    public void getLibrary() {
        Call<GetLibraryResponse> call = loginService.getLibraryCall_notes(topicId,standardId,chapterId,"video");
        call.enqueue(new Callback<GetLibraryResponse>() {
            @Override
            public void onResponse(Call<GetLibraryResponse> call, Response<GetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                GetLibraryResponse getLibraryResponse = response.body();
                ArrayList<Content> contents = getLibraryResponse.contents;
                int length=contents.size();
                Log.i("videoSize",String.valueOf(length));
                if(length==0){
                    novideo.setVisibility(View.VISIBLE);
                }
                else {
                    videoModel=new ArrayList<>();
                    for (int i = 0; i <= length - 1; i++) {
                        videoModel.add(new VideoModel(R.drawable.mapchem,R.drawable.ic_baseline_more_vert_24,getLibraryResponse.contents.get(i).title,"12:00",getLibraryResponse.contents.get(i).link,getLibraryResponse.contents.get(i).file,getLibraryResponse.contents.get(i).status,getLibraryResponse.contents.get(i).id));
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

    public  void buildR(){
        recyclerView=view.findViewById(R.id.video_rvvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter1 = new VideosAdapter(videoModel,getContext(),subjectName,topicName,chapterName,sectionName,standardName);
        recyclerView.setAdapter(adapter1);}

}