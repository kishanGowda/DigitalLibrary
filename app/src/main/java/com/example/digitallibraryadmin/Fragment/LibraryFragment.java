package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitallibraryadmin.Adapter.ContentAdapter;
import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.GetLibraryResponse;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ModelClass.ContentModel;
import com.example.digitallibraryadmin.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LibraryFragment extends Fragment {
    View view;
    TabLayout tabLayout;
    ViewPager viewPager;
    LoginService loginService;
    Retrofit retrofit;
    Toolbar toolbar;
    public int notes, video, question;
    RecyclerView recyclerView;
    ImageView back;

    RecyclerView.LayoutManager layoutManager;
    ContentAdapter adapter1;
    GetLibraryResponse getLibraryResponse;
    ArrayList<ContentModel> contentModels;
    private int chapterId, topicId, standardId,subjectId;
    String subjectName,topicName,chapterName,standardName,sectionName;

    public LibraryFragment(int topicId, int chapterId, int standardId, String topicName, String chapterName, String standardName, String section, String subjectName, int subjectId) {
            this.topicId=topicId;
            this.chapterId=chapterId;
            this.standardId=standardId;
            this.topicName=topicName;
            this.chapterName=chapterName;
            this.standardName=standardName;
            sectionName=section;
            this.subjectName=subjectName;
            this.subjectId=subjectId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_library, container, false);


        apiInit();
        back = view.findViewById(R.id.library_activity_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        TextView tv1 = view.findViewById(R.id.topic_libary_name);
        tv1.setText(topicName);
        getLibrary();
        tab();

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayoutLibrary);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
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
        Log.i("topic", String.valueOf(topicId));

        Call<GetLibraryResponse> call = loginService.getLibraryCall(topicId, standardId, chapterId);
        call.enqueue(new Callback<GetLibraryResponse>() {
            @Override
            public void onResponse(Call<GetLibraryResponse> call, Response<GetLibraryResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                getLibraryResponse = response.body();
                contentModels = new ArrayList<>();
                notes = Integer.valueOf(getLibraryResponse.totalCount.get(0).notesCount);
                video = Integer.valueOf(getLibraryResponse.totalCount.get(0).videoCount);
                question = Integer.valueOf(getLibraryResponse.totalCount.get(0).quesBankCount);
                Log.i("notes", String.valueOf(notes));
                contentModels.add(new ContentModel(R.drawable.lecturenotes, notes, "Lecture notes"));
                contentModels.add(new ContentModel(R.drawable.lecturevideos, video, "Videos"));
                contentModels.add(new ContentModel(R.drawable.questionbank, question, "Question Bank"));
                build();
            }

            @Override
            public void onFailure(Call<GetLibraryResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

            }
        });
    }

        public void tab(){

            Call<GetLibraryResponse> call = loginService.getLibraryCall(topicId, standardId, chapterId);
            call.enqueue(new Callback<GetLibraryResponse>() {
                @Override
                public void onResponse(Call<GetLibraryResponse> call, Response<GetLibraryResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                    }
                    getLibraryResponse = response.body();
                    notes = Integer.valueOf(getLibraryResponse.totalCount.get(0).notesCount);
                    video = Integer.valueOf(getLibraryResponse.totalCount.get(0).videoCount);
                    question = Integer.valueOf(getLibraryResponse.totalCount.get(0).quesBankCount);


                    FragmentManager fm = getParentFragmentManager();
                    ViewStateAdapter sa = new ViewStateAdapter(fm, getLifecycle());
                    final ViewPager2 pa = view.findViewById(R.id.pager);
                    pa.setAdapter(sa);
                    TabLayout tabLayout = view.findViewById(R.id.tabLayout);
                    //
                    tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("Lecture notes(" + notes + ")")));
                    tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("videos(" + video + ")")));
                    tabLayout.addTab(tabLayout.newTab().setText(String.valueOf("Question banks(" + question + ")")));
                    //
                    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            pa.setCurrentItem(tab.getPosition());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                    pa.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            tabLayout.selectTab(tabLayout.getTabAt(position));
                        }
                    });
                }


                @Override
                public void onFailure(Call<GetLibraryResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

                }
            });


        }




    private void build() {
        recyclerView = view.findViewById(R.id.recy1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        adapter1 = new ContentAdapter(contentModels, getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter1);
    }

    private class ViewStateAdapter extends FragmentStateAdapter {

        public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Hardcoded in this order, you'll want to use lists and make sure the titles match
            if (position == 0) {
                LecturerNoteFragment fragment = new LecturerNoteFragment(subjectName,topicName,chapterName,standardName,sectionName,chapterId, topicId, standardId,subjectId);
                return fragment;
            }
            if (position == 1) {
                return new VideosFragment(subjectName,topicName,chapterName,standardName,sectionName,chapterId, topicId, standardId,subjectId);
            }
            return new QuestionBackFragment(subjectName,topicName,chapterName,standardName,sectionName,chapterId, topicId, standardId,subjectId);
        }

        @Override
        public int getItemCount() {
            // Hardcoded, use lists
            return 3;
        }
    }

}