package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.ChapterfilterResponse;
import com.example.digitallibraryadmin.ApiLibrary.DashBoardOne;
import com.example.digitallibraryadmin.ApiLibrary.GetSubjectBySubIdResponse;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ApiLibrary.SubjectFilterResponse;
import com.example.digitallibraryadmin.ApiLibrary.TopicFilterResponse;
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
    EditText name, chapter, titleName,topic;
    int position;
    LoginService loginService;
    Retrofit retrofit;
    int count,classcount;
    ArrayList<String> uni;
    int filterdSubjectID;
    int filSubject;
    int topicFilterId;
    ArrayList<ParentModel> parentModels;
    DashBoardOne homePageGetAllStdResponse;
    AutoCompleteTextView text, topicAuto,chapters;
    ArrayList<String> subjectFiltersList;
    String subjectName, standardName, topicName, chapterName, sectionName, title;
    ArrayList<String> names;
    ArrayList<String> chapterFilter;
    ArrayList<String> topicFiltersArray;
    int chapterFilterId;
    ArrayList<String> chapterNames = new ArrayList<>();
    int standardId, subjectId, chapterId, topicId;
    ImageView back;
    int chaptercount=0;
    int subjectFilterId = 0;
    int topicCountes=0;
    int classId;
    int topicCount;
    int filterTopicId;
    String topicEdited;



    public Edit( String subjectName, String topicName, String chapterName, String sectionName,
                String titleName, String standardName) {

       this.subjectName=subjectName;
       this.topicName=topicName;
       this.chapterName=chapterName;
       this.sectionName=sectionName;
       title=titleName;
       this.standardName=standardName;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit, container, false);
        name = view.findViewById(R.id.name);

        // subjectName=b.getString("position");
        close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
            }
        });

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




        //subject name
        name.setText(subjectName);
        apiInit();
//        standard();

        //chapter name
        chapters();

        //topic name
        //topic();


        subjectName();

        topic=view.findViewById(R.id.title_edit);
       topicEdited= topic.getText().toString().trim();




        Log.i("filSubject1", String.valueOf(subjectFilterId));    Log.i("filSubject2", String.valueOf(subjectFilterId));
//        subjectClass();
        //title

        titleName = view.findViewById(R.id.title_edit);
        titleName.setText(title);

        back = view.findViewById(R.id.back_edit_detials);
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

    public void chapters() {
        //chapter name
        chapter = view.findViewById(R.id.capter_edit);
        chapter.setText(String.valueOf(chapterName));

        ImageView close = view.findViewById(R.id.close_chapter);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapter.setText("");
            }
        });
    }

//    public void topic() {
//
//        Call<List<TopicResponse>> call = loginService.getTopicCall(chapterId, subjectId, standardId);
//        call.enqueue(new Callback<List<TopicResponse>>() {
//            @Override
//            public void onResponse(Call<List<TopicResponse>> call, Response<List<TopicResponse>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
//                }
//                List<TopicResponse> topicFiterTAResponseList = response.body();
//                int size = topicFiterTAResponseList.size();
//                Log.i("size", String.valueOf(size));
//                for (int i = 0; i <= size - 1; i++) {
//                    chapterNames.add(topicFiterTAResponseList.get(i).topicName);
//                    Log.i("TAG", String.valueOf(topicFiterTAResponseList.get(i).topicName));
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<TopicResponse>> call, Throwable t) {
//                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }


    //filter
    public void subjectName() {

        Call<List<SubjectFilterResponse>> call = loginService.subjectFilterCall();
        call.enqueue(new Callback<List<SubjectFilterResponse>>() {
            @Override
            public void onResponse(Call<List<SubjectFilterResponse>> call, Response<List<SubjectFilterResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                List<SubjectFilterResponse> subjectFilterResponses = response.body();
                int size = subjectFilterResponses.size();
                Log.i("siez", String.valueOf(size));
                subjectFiltersList = new ArrayList<>();
                if (size == 0) {
                } else {
                    for (int p = 0; p <= size - 1; p++) {
                        Log.i("ggg", subjectFilterResponses.get(p).getName());
                        subjectFiltersList.add(subjectFilterResponses.get(p).getName().trim());
                    }
                }
                for (int k = 0; k <= size - 1; k++) {
                    Log.i("g", subjectFiltersList.get(k).toString().trim());

                }
                AutoCompleteTextView textView = (AutoCompleteTextView) view.findViewById(R.id.name);
                ArrayAdapter adapterOne = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, subjectFiltersList);
                textView.setAdapter(adapterOne);
                textView.setThreshold(1);
                textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = adapterView.getItemAtPosition(i).toString();



//                        Toast.makeText(getContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
                        boolean isRepeated = false;
                        count = 0;
                        for (int j = 0; j <= subjectFiltersList.size() - 1; j++) {
                            if (item == subjectFiltersList.get(j).toString()) {
                                break;
                            } else {
                                count++;
                            }
                        }
                        Log.i("numggg", String.valueOf(count));
                        filSubject=0;
                        filSubject = subjectFilterResponses.get(count).getId();
                      int  subjectFilterId=filSubject;
                        Log.i("sid", String.valueOf(filSubject));
                      pass(filSubject);



                    }
                });
            }

            @Override
            public void onFailure(Call<List<SubjectFilterResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

            }
        });


    }

    private void pass(int filSubject) {
       filterdSubjectID=filSubject;
        Log.i("filSubject333", String.valueOf(filterdSubjectID));
        Call<List<GetSubjectBySubIdResponse>> callSubject = loginService.getSubjectByCall(filterdSubjectID);
        callSubject.enqueue(new Callback<List<GetSubjectBySubIdResponse>>() {
            @Override
            public void onResponse(Call<List<GetSubjectBySubIdResponse>> call, Response<List<GetSubjectBySubIdResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                List<GetSubjectBySubIdResponse> getSubjectBySubIdResponses = response.body();
                int size = getSubjectBySubIdResponses.size();
                Log.i("size", String.valueOf(size));
                names=new ArrayList<>();
                for(int k=0;k<=size-1;k++){
                    names.add(getSubjectBySubIdResponses.get(k).std.toString());
                    Log.i("names", names.get(k).toString());
                }
                text = (AutoCompleteTextView) view.findViewById(R.id.standard_drop);
                ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, names);
                text.setAdapter(adapter);
                text.setThreshold(1);
                text.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = adapterView.getItemAtPosition(i).toString();



//
                        classcount = 0;
                        for (int j = 0; j <= getSubjectBySubIdResponses.size() - 1; j++) {
                            if (item == getSubjectBySubIdResponses.get(j).std) {
                                break;
                            } else {
                                classcount++;
                            }
                        }
                        Log.i("classcount", String.valueOf(classcount));
                        int classId=getSubjectBySubIdResponses.get(classcount).id;
                        chapter(classId);

                    }
                });

            }

            @Override
            public void onFailure(Call<List<GetSubjectBySubIdResponse>> call, Throwable t) {

            }
        });


    }

    private void chapter(int classId) {
      this.classId=classId;
        Log.i("subject", String.valueOf(filterdSubjectID));
        Log.i("id", String.valueOf(classId));
        Call<List<ChapterfilterResponse>> callSubject = loginService.getchapterByCall(filterdSubjectID,classId);
        callSubject.enqueue(new Callback<List<ChapterfilterResponse>>() {
            @Override
            public void onResponse(Call<List<ChapterfilterResponse>> call, Response<List<ChapterfilterResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                List<ChapterfilterResponse> chapterfilterResponses = response.body();
                int size = chapterfilterResponses.size();
                Log.i("size", String.valueOf(size));
                chapterFilter=new ArrayList<>();
                for(int k=0;k<=size-1;k++){
                    chapterFilter.add(chapterfilterResponses.get(k).name.toString());
                    Log.i("n", chapterFilter.get(k).toString());
                }
                chapters = view.findViewById(R.id.capter_edit);
                ArrayAdapter adapterthree = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, chapterFilter);
                chapters.setAdapter(adapterthree);
                chapters.setThreshold(1);
                chapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = adapterView.getItemAtPosition(i).toString();

                        chaptercount = 0;
                        for (int j = 0; j <= chapterfilterResponses.size() - 1; j++) {
                            if (item == chapterfilterResponses.get(j).name.toString()) {
                                break;
                            } else {
                                chaptercount++;
                            }
                        }
                        Log.i("classcount", String.valueOf(chaptercount));
                        int filtertopicId=chapterfilterResponses.get(chaptercount).id;
                        topic(filtertopicId);

                    }
                });
            }

            @Override
            public void onFailure(Call<List<ChapterfilterResponse>> call, Throwable t) {

            }
        });

    }

    private void topic(int filtertopicId) {
       this.filterTopicId=filtertopicId;
        Log.i("subjectrID", String.valueOf(filterdSubjectID));
        Log.i("StandardIdr", String.valueOf(classId));
        Log.i("chapterID", String.valueOf(filterTopicId));

        Call<List<TopicFilterResponse>> callSubject = loginService.filterTopicResponseCall(filterdSubjectID,classId,filterTopicId);
        callSubject.enqueue(new Callback<List<TopicFilterResponse>>() {
            @Override
            public void onResponse(Call<List<TopicFilterResponse>> call, Response<List<TopicFilterResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                List<TopicFilterResponse> topicFilterResponses = response.body();
                int size = topicFilterResponses.size();
                Log.i("size", String.valueOf(size));
                topicFiltersArray=new ArrayList<>();
                for(int k=0;k<=size-1;k++){
                    topicFiltersArray.add(topicFilterResponses.get(k).name.toString());
                    Log.i("sn", topicFiltersArray.get(k).toString());
                }
                topicAuto = (AutoCompleteTextView) view.findViewById(R.id.topic_drop);
                ArrayAdapter adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, topicFiltersArray);
                topicAuto.setAdapter(adapter2);
                topicAuto.setThreshold(1);
                topicAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = adapterView.getItemAtPosition(i).toString();

                        topicCountes = 0;
                        for (int j = 0; j <= topicFilterResponses.size() - 1; j++) {
                            if (item == topicFilterResponses.get(j).name.toString()) {
                                break;
                            } else {
                                topicCountes++;
                            }
                        }
                        Log.i("classcount", String.valueOf(topicCountes));

                        int topicFilterId=topicFilterResponses.get(topicCountes).id;
                            getTopic(topicFilterId);
                    }
                });



            }

            @Override
            public void onFailure(Call<List<TopicFilterResponse>> call, Throwable t) {

            }
        });



    }

    private void getTopic(int topicFilterId) {
        this.topicFilterId=topicFilterId;
    }
}