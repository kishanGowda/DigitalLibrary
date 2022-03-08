package com.example.digitallibraryadmin.Fragment;

import android.app.SearchManager;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitallibraryadmin.Adapter.SubjectAdapter;

import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;

import com.example.digitallibraryadmin.ApiLibrary.StandardByID;
import com.example.digitallibraryadmin.MainActivity;
import com.example.digitallibraryadmin.ModelClass.SubjectModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SubjectFragment extends Fragment {
    View view;
    int i;
    String section,standard;

    RecyclerView recyclerView2;
    // for recycler
    private RecyclerView recyclerView;
    SubjectAdapter subjectAdapter;
    ArrayList<SubjectModel> subjectModels;
    LoginService loginService;
    Retrofit retrofit;
    int position;
    LinearLayout linearLayout;



    private RecyclerView.LayoutManager layoutManager;

    //back
    ImageView back;

    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TransitionInflater inflate = TransitionInflater.from(requireContext());
        setExitTransition(inflate.inflateTransition(R.transition.fade));
        view = inflater.inflate(R.layout.fragment_subject, container, false);
        section=getArguments().getString("section");
        standard=getArguments().getString("standardName");
        Log.i("section", section);
        Log.i("standard",String.valueOf( standard));
        TextView tv1 =view.findViewById(R.id.standard_tool_bar1);
        tv1.setText(standard);
        TextView tv2 =view.findViewById(R.id.section_toolbar1);
        tv2.setText(section);
        apiInit();
        standardById();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayoutSubjet);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                       standardById();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );


        back=view.findViewById(R.id.back_subject);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        return view;
    }

    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }

    //            int position = Integer.valueOf(getArguments().getString("Position"));
//            Log.i("Position", String.valueOf(position));


    public void standardById() {
        position = Integer.valueOf(getArguments().getString("Position"));
         Log.i("Position", String.valueOf(position));
        Call<StandardByID> standardByIdResponseCall = loginService.standardCall(position);
        standardByIdResponseCall.enqueue(new Callback<StandardByID>() {
            @Override
            public void onResponse(Call<StandardByID> call, Response<StandardByID> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                StandardByID standard = response.body();
                subjectModels = new ArrayList<>();
                i = standard.subjects.size();
                Log.i("i", String.valueOf(i));
                if (i == 0) {
                    linearLayout=view.findViewById(R.id.no_subject_avialbale_);
                    linearLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "0", Toast.LENGTH_LONG).show();
                } else {
                    for (int j = 0; j <= i - 1; j++) {
                        subjectModels.add(new SubjectModel(standard.subjects.get(j).subjects_name, String.valueOf(standard.subjects.get(j).chapterCount), Integer.valueOf(standard.subjects.get(j).notesCount), Integer.valueOf(standard.subjects.get(j).videoCount), Integer.valueOf(standard.subjects.get(j).quesBankCount),standard.subjects.get(j).subjects_id,standard.data.get(0).getStd_id()));
                    }
                    build();
                }
            }

            @Override
            public void onFailure(Call<StandardByID> call, Throwable t) {
                Toast.makeText(getContext(), "Error :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void build() {
        recyclerView = view.findViewById(R.id.subject_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        subjectAdapter = new SubjectAdapter(subjectModels, getContext(),position,section,standard);
        recyclerView.setAdapter(subjectAdapter);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

         // It's important here
    }
//
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//        setHasOptionsMenu(true);
//        MenuItem searchViewItem = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchView.clearFocus();
//                return false;
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                subjectAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
        // Associate searchable configuration with the SearchView

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getContext().SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        Log.d("Tab", "SearchManager: " + searchManager + " : " + searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                subjectAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    }






//subjectModels.add(new SubjectModel(standard.subjects.get(i).subjects_name,String.valueOf(standard.subjects.get(i).chapterCount),Integer.valueOf(standard.subjects.get(i).notesCount),Integer.valueOf(standard.subjects.get(i).videoCount),Integer.valueOf(standard.subjects.get(i).quesBankCount)));

