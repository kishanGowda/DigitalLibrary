package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.digitallibraryadmin.Adapter.TopicModelAdapter;
import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ApiLibrary.TopicResponse;
import com.example.digitallibraryadmin.ModelClass.TopicModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TopicFragment extends Fragment {
View view;
int subjectId;
String  standardid,standardName,section,subjectName;
String chapterid,chapterName;
LoginService loginService;
Retrofit retrofit;
    TextView tv1;
    LinearLayout linearLayout;
ImageView back;
    private RecyclerView courseRV;
    private TopicModelAdapter adapter;
    private ArrayList<TopicModel> courseModalArrayList;
    public TopicFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TransitionInflater inflate = TransitionInflater.from(requireContext());
        setExitTransition(inflate.inflateTransition(R.transition.fade));
        view= inflater.inflate(R.layout.fragment_topic, container, false);
        subjectId = Integer.valueOf(getArguments().getString("subjectId"));
        Log.i("subject", String.valueOf(subjectId));
         chapterid = getArguments().getString("chapterId");
        Log.i("name", String.valueOf(chapterid));
        standardid = String.valueOf(getArguments().getString("standardId"));
        Log.i("standard",standardid);
        standardName=getArguments().getString("standardName1");
        section=getArguments().getString("sectionName1");
        subjectName=getArguments().getString("subjectName1");
        Log.i("sbn",String.valueOf(subjectName));
        Log.i("standardname",String.valueOf(standardName));
        Log.i("section",String.valueOf(section));
        chapterName=String.valueOf(getArguments().getString("topicName"));

        tv1 =view.findViewById(R.id.topic_chapter_name);
        tv1.setText(chapterName);

        courseRV = view.findViewById(R.id.idRVCourses);
        back=view.findViewById(R.id.back_no_topic);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayouttopic);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        topic();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        // calling method to
        // build recycler view.
       apiInit();
       topic();
        return view;
    }


    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.action_search);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });

    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<TopicModel> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (TopicModel item : courseModalArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCourseDescription().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    public void topic() {
        courseModalArrayList = new ArrayList<>();

        Call<List<TopicResponse>> call = loginService.getTopicCall(Integer.valueOf(chapterid),subjectId,Integer.valueOf(standardid));
        call.enqueue(new Callback<List<TopicResponse>>() {
            @Override
            public void onResponse(Call<List<TopicResponse>> call, Response<List<TopicResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                List<TopicResponse> topicFiterTAResponseList = response.body();
                int size = topicFiterTAResponseList.size();
                Log.i("size", String.valueOf(size));
                if (size == 0) {
                    linearLayout = view.findViewById(R.id.no_topic2_avialbale_);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    for (int i = 0; i <= size - 1; i++) {
                        courseModalArrayList.add(new TopicModel(R.drawable.ic_baseline_more_vert_24, Integer.valueOf(topicFiterTAResponseList.get(i).getVideoCount()), Integer.valueOf(topicFiterTAResponseList.get(i).getQuesBankCount()), Integer.valueOf(topicFiterTAResponseList.get(i).notesCount), String.valueOf(topicFiterTAResponseList.get(i).topicName), String.valueOf(topicFiterTAResponseList.get(i).getTopicId())));
                    }
                    buildRecyclerView();
                }
            }
            @Override
            public void onFailure(Call<List<TopicResponse>> call, Throwable t) {
                Toast.makeText(getContext(),"error", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void buildRecyclerView(){
        adapter = new TopicModelAdapter(courseModalArrayList,getContext(),standardid,chapterid,standardName,section,subjectName,chapterName,subjectId);
        LinearLayoutManager manager = new GridLayoutManager(getContext(), 2,RecyclerView.VERTICAL,false);
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(manager);
        courseRV.setAdapter(adapter);
    }
}
