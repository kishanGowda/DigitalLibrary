package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.digitallibraryadmin.Adapter.Card1HoriAdapter;
import com.example.digitallibraryadmin.Adapter.ParentRecyclerViewAdapter;

import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.DashBoardOne;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ModelClass.Card1Hori;
import com.example.digitallibraryadmin.ModelClass.ParentModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainFragment extends Fragment {
    View view;
    ArrayList<Card1Hori> cardHori;
    DashBoardOne homePageGetAllStdResponse;
    RecyclerView recyclerView1;
    Card1HoriAdapter cardHoriAdapter;

    RecyclerView.LayoutManager layoutManager1;
    ConstraintLayout cons;
    private RecyclerView.LayoutManager parentLayoutManager;
    private RecyclerView parentRecyclerView;
    private RecyclerView.Adapter ParentAdapter;
    ArrayList<ParentModel> parentModelArrayList;


    Retrofit retrofit;
    LoginService loginService;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_main, container, false);
        apiInit();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayout);
        getSubmitAns();
        standard();
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getSubmitAns();
                        standard();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        return view;

    }
    public void standard() {
        Call<DashBoardOne> call = loginService.getHomepageCall();
        call.enqueue(new Callback<DashBoardOne>() {
            @Override
            public void onResponse(Call<DashBoardOne> call, Response<DashBoardOne> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                homePageGetAllStdResponse = response.body();
                ArrayList<String> standard=new ArrayList();
                for(int i=0;i<=homePageGetAllStdResponse.standardslength-1;i++) {
                    standard.add(homePageGetAllStdResponse.data.get(i).std_std);
                }
                Set values=new HashSet(standard);
                Log.i("tag",values.toString());
                ArrayList<String> uni=new ArrayList<>(values);
                parentModelArrayList = new ArrayList<>();
                for (int i = 0; i <= values.size()-1; i++) {
                    parentModelArrayList.add(new ParentModel(uni.get(i)));
                }
                buildRecyclerView2();

            }

            @Override
            public void onFailure(Call<DashBoardOne> call, Throwable t) {
                Toast.makeText(getContext(), "Error submit :(", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void buildRecyclerView2(){
        parentRecyclerView =view.findViewById(R.id.rv_parent);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(getContext());
        ParentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList, getContext(),homePageGetAllStdResponse,cons);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();

    }
    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }

    public void getSubmitAns() {
        Call<DashBoardOne> call = loginService.getHomepageCall();
        call.enqueue(new Callback<DashBoardOne>() {
            @Override
            public void onResponse(Call<DashBoardOne> call, Response<DashBoardOne> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                DashBoardOne homePageGetAllStdResponse = response.body();
                cardHori = new ArrayList<>();
//                String last=String.valueOf(homePageGetAllStdResponse.totalCount.lastWeekLectureNotesCount);
                cardHori.add(new Card1Hori(R.drawable.notes,homePageGetAllStdResponse.totalCount.toalNotesCount,"Lecture",String.valueOf(homePageGetAllStdResponse.totalCount.lastWeekLectureNotesCount+"  From last week")));
                cardHori.add(new Card1Hori(R.drawable.videos,homePageGetAllStdResponse.totalCount.totalVideoCount,"videos",String.valueOf(homePageGetAllStdResponse.totalCount.lastWeekVideoCount+"  From last week")));
                cardHori.add(new Card1Hori(R.drawable.quebank,homePageGetAllStdResponse.totalCount.totalQuesBankCount,"Question",String.valueOf(homePageGetAllStdResponse.totalCount.lastWeekQuestionBankCount+"  From last week")));
                buildRecyclerView();

            }

            @Override
            public void onFailure(Call<DashBoardOne> call, Throwable t) {
                Toast.makeText(getContext(), "Error submit :(", Toast.LENGTH_LONG).show();
            }
        });
    }




    public void buildRecyclerView() {

        recyclerView1 = view.findViewById(R.id.recyler1);
        recyclerView1.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        cardHoriAdapter = new Card1HoriAdapter(cardHori);
        recyclerView1.setAdapter(cardHoriAdapter);
    }


}
