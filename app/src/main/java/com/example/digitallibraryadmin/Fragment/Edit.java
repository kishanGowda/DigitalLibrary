package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.DashBoardOne;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ApiLibrary.TopicResponse;
import com.example.digitallibraryadmin.ModelClass.ParentModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Edit extends Fragment {

View view;
ImageView close;
EditText name,chapter,titleName;
int position;
LoginService loginService;
Retrofit retrofit;
ArrayList<String> uni;
ArrayList<ParentModel> parentModels;
    DashBoardOne homePageGetAllStdResponse;
    AutoCompleteTextView text,topicAuto;
    String subjectName,standardName,topicName,chapterName,sectionName,title;
    ArrayList<String> names=new ArrayList<>();
    ArrayList<String> chapterNames=new ArrayList<>();
    int standardId,subjectId,chapterId,topicId;
    ImageView back;


    public Edit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit, container, false);
        name = view.findViewById(R.id.name);
        Bundle bundle = getArguments();
        standardName = bundle.getString("pos");
        // subjectName=b.getString("position");
        close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
            }
        });
        subjectName = getArguments().getString("subjectName3");
        standardName = getArguments().getString("standardName3");
        sectionName = getArguments().getString("sectionName3");
        chapterName = getArguments().getString("chapterName3");
        topicName = getArguments().getString("topicName3");
        title = getArguments().getString("title3");
        position = Integer.valueOf(getArguments().getString("position3"));
        standardId= Integer.valueOf(getArguments().getString("standardId"));
        subjectId= Integer.valueOf(getArguments().getString("subjectId"));
        chapterId= Integer.valueOf(getArguments().getString("chapterId"));
        topicId=Integer.valueOf(getArguments().getString("topicId"));
        Log.i("s1", standardName);
        Log.i("s2", sectionName);
        Log.i("s2", subjectName);
        Log.i("s2", chapterName);
        Log.i("s2", topicName);
        Log.i("s2", title);
        Log.i("s22", String.valueOf(standardId));
        Log.i("s22", String.valueOf(subjectId));
        Log.i("s22", String.valueOf(chapterId));
        Log.i("s22", String.valueOf(topicId));
        names.add("system");
        text=(AutoCompleteTextView)view.findViewById(R.id.standard_drop);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,names);
        text.setAdapter(adapter);
        text.setThreshold(1);

        //subject name
        name.setText(subjectName);
        apiInit();
        standard();

        //chapter name
        chapters();

        //topic name
        topic();

        //title

        titleName=view.findViewById(R.id.title_edit);
        titleName.setText(title);

        back=view.findViewById(R.id.back_edit_detials);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return  view;
    }
    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

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
                for (int i = 0; i <= values.size()-1; i++) {
                    names.add(String.valueOf(uni.get(i).toString()));
                }
            }

            @Override
            public void onFailure(Call<DashBoardOne> call, Throwable t) {
                Toast.makeText(getContext(), "Error submit :(..", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void chapters(){
        //chapter name
        chapter=view.findViewById(R.id.capter_edit);
        chapter.setText(String.valueOf(chapterName));

        ImageView close=view.findViewById(R.id.close_chapter);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapter.setText("");
            }
        });
    }

    public void topic() {

        Call<List<TopicResponse>> call = loginService.getTopicCall(chapterId,subjectId,standardId);
        call.enqueue(new Callback<List<TopicResponse>>() {
            @Override
            public void onResponse(Call<List<TopicResponse>> call, Response<List<TopicResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                List<TopicResponse> topicFiterTAResponseList = response.body();
                int size = topicFiterTAResponseList.size();
                Log.i("size", String.valueOf(size));
                for (int i=0;i<=size-1;i++){
                    chapterNames.add(topicFiterTAResponseList.get(i).topicName);
                    Log.i("TAG", String.valueOf(topicFiterTAResponseList.get(i).topicName));

                }
                topicAuto=(AutoCompleteTextView)view.findViewById(R.id.topic_drop);
                ArrayAdapter adapter2 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,chapterNames);
                topicAuto.setAdapter(adapter2);
                topicAuto.setThreshold(1);
            }
            @Override
            public void onFailure(Call<List<TopicResponse>> call, Throwable t) {
                Toast.makeText(getContext(),"error", Toast.LENGTH_LONG).show();

            }
        });
    }


}