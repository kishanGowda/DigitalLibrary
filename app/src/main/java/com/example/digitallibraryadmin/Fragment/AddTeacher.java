package com.example.digitallibraryadmin.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitallibraryadmin.Adapter.AddTeacherAdapter;
import com.example.digitallibraryadmin.ApiLibrary.AddTeacherRequestPost;
import com.example.digitallibraryadmin.ApiLibrary.AddTeacherResponse;
import com.example.digitallibraryadmin.ApiLibrary.AddTeacherResponsePost;
import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.ChapterListResponse;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.ApiLibrary.PostUpdateResponse;
import com.example.digitallibraryadmin.ModelClass.AddTeacherModel;
import com.example.digitallibraryadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AddTeacher extends Fragment {
    View view;
    Button addButton,saveButton;
    RecyclerView recyclerView;
    AddTeacherAdapter adapters;
    int deletedIdIn;
    LinearLayoutManager layoutManager;
    ArrayList<Integer> deleteteacherId = new ArrayList<>();
    ArrayList<AddTeacherModel> addTeacherModels = new ArrayList<>();
    AutoCompleteTextView textView;
    int deletedId;
    ArrayList<Integer> delete;
    int subjectId, standardId;
    String subjectName,standardName,section;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<AddTeacherModel> addTeacherSuggets = new ArrayList<>();
    LoginService loginService;
    Retrofit retrofit;
    ImageView back;
    ArrayList<Integer> addedId=new ArrayList<>();
    int addedTeacherId;
    String name;
    int count=0;
    int size, pos,forDelete;
    boolean isPresent;
    AddTeacherResponsePost postTeacherManagementResponse;
    ArrayList<Integer> num = new ArrayList<>();
    List<AddTeacherResponse> addTeacherResponses;

    public AddTeacher(Integer standardId, Integer subjectId, String subjectName, String standardName, String section) {
        // Required empty public constructor
        this.standardId=standardId;
        this.subjectId=subjectId;
        this.subjectName=subjectName;
        this.standardName=standardName;
        this.section=section;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TransitionInflater inflate = TransitionInflater.from(requireContext());
        setExitTransition(inflate.inflateTransition(R.transition.fade));
        view = inflater.inflate(R.layout.fragment_add_teacher, container, false);
//        Log.i("TAG", String.valueOf(subjectName));
//        Log.i("TAG", String.valueOf(standardName));
//        Log.i("TAG", String.valueOf(section));

        addButton = view.findViewById(R.id.add);
        textView = view.findViewById(R.id.add_auto);
        textView.addTextChangedListener(addTeacher);
        apiInit();
        createCard();
        addTeacherss();

        back = view.findViewById(R.id.back_teacher_add);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        saveButton=view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postTeacherManagement();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });



        return view;
    }

    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }
    public void createCard() {
        Call<ChapterListResponse> call = loginService.chapterListCall(Integer.valueOf(subjectId), Integer.valueOf(standardId));

        call.enqueue(new Callback<ChapterListResponse>() {
            @Override
            public void onResponse(Call<ChapterListResponse> call, Response<ChapterListResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                ChapterListResponse chapterListResponse = response.body();
                int length = chapterListResponse.addedTeachers.size();
                Log.i("addedteacher", String.valueOf(length));
                for (int i=0;i<=length-1;i++){
                    addTeacherSuggets.add(new AddTeacherModel(chapterListResponse.addedTeachers.get(i).name, R.drawable.ic_baseline_close_24,chapterListResponse.addedTeachers.get(i).id));
                }
                build();

            }
            @Override
            public void onFailure(Call<ChapterListResponse> call, Throwable t) {

            }
        });
    }



    public void addTeacherss() {
        Call<List<AddTeacherResponse>> call = loginService.addTeacherCall(subjectId, standardId);
        call.enqueue(new Callback<List<AddTeacherResponse>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<AddTeacherResponse>> call, Response<List<AddTeacherResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                addTeacherResponses = response.body();
                size = addTeacherResponses.size();
                Log.i("size", String.valueOf(size));
                for (int i = 0; i <= size - 1; i++) {
                    list.add(String.valueOf(addTeacherResponses.get(i).getName()));

                }
                textView = (AutoCompleteTextView) view.findViewById(R.id.add_auto);
                ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);
                textView.setAdapter(adapter);
                textView.setThreshold(1);
                textView.getDropDownHeight();
                textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            textView.showDropDown();

                        }

                    }
                });
                textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = adapterView.getItemAtPosition(i).toString();
                        count=0;
                        for (int j=0;j<= addTeacherResponses.size()-1;j++){
                            if(item.equals(addTeacherResponses.get(j).getName())){
                                break;
                            }else
                            {
                                count++;
                            }

                        }
                        addedTeacherId=addTeacherResponses.get(count).id;
                        Log.i("idForTeacher", String.valueOf(addedTeacherId));
                        Log.i("TAG", String.valueOf(standardId));
                        Log.i("TAG", String.valueOf(subjectId));
                        boolean isRepeated = false;
                        for (int m=0;m<=addTeacherSuggets.size()-1;m++){
                            if (addTeacherSuggets.get(m).getTeacherName().equals(item)) {
                                    isRepeated = true;
                                }
                            }
                        if(isRepeated){
                            Toast.makeText(getContext(), item+"already added", Toast.LENGTH_SHORT).show();
                        }
                            if (!isRepeated) {
                                addTeacherSuggets.add(new AddTeacherModel(item, R.drawable.ic_baseline_close_24,addedTeacherId));
                                addedId.add(addedTeacherId);

                        }
                            for (int n=0;n<= addTeacherSuggets.size()-1;n++){
                                Log.i("TAG", String.valueOf(addTeacherSuggets.get(n).getTeacherName().toString()+addTeacherSuggets.get(n).getId()));
                            }
                        build();




//                        boolean isRepeated = false;
//                        if (addTeacherSuggets.size() == 0) {
//                            addTeacherSuggets.add(new AddTeacherModel(item, R.drawable.ic_baseline_close_24,getId()));
//
//                            pos = 0;
//                            for (int m = 0; m <= list.size(); m++) {
//                                if (list.get(m).equals(item)) {
//                                    pos = m;
//                                    break;
//                                }
//                            }
//
//                           // postTeacherManagement();
//                            build();
//                        } else {
//                            for (int k = 0; k <= addTeacherSuggets.size() - 1; k++) {
//                                if (addTeacherSuggets.get(k).getTeacherName().equals(item)) {
//                                    isRepeated = true;
//                                }
//                            }
//                            if (!isRepeated) {
//                                addTeacherSuggets.add(new AddTeacherModel(item, R.drawable.ic_baseline_close_24,getId()));
//                                pos = 0;
//
//                                for (int m = 0; m <= list.size(); m++) {
//                                    if (list.get(m).equals(item)) {
//                                        pos = m;
//                                        break;
//                                    }
//                                }
//
//                              //  postTeacherManagement();
//                                build();
//                            }
//                        }
                   }
                });
            }

            @Override
            public void onFailure(Call<List<AddTeacherResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();

            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sug = textView.getText().toString();
                isPresent = false;
                for (int j = 0; j <= list.size() - 1; j++) {
                    if (list.get(j).equals(sug)) {
                        isPresent = true;
                        postTeacherManagement();
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                }
                if (!isPresent)
                    Toast.makeText(getContext(), "Enter Valid Teacher Name", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void removeItem(int position) {
        addTeacherSuggets.remove(position);

    }


    public void postTeacherManagementDelete(int positon) {
        ArrayList<Integer> teacher = new ArrayList<>();

        Log.i("pos", String.valueOf(positon));
        teacher.add(positon);
        ArrayList<Integer> delete=new ArrayList<>();
        delete.add(positon);
        AddTeacherRequestPost addTeacherRequest = new AddTeacherRequestPost(teacher, subjectId, standardId, delete);
        Call<PostUpdateResponse> call = loginService.postTeachermngtCall(addTeacherRequest);
        call.enqueue(new Callback<PostUpdateResponse>() {
            @Override
            public void onResponse(Call<PostUpdateResponse> call, Response<PostUpdateResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                PostUpdateResponse  postTeacherManagementResponse = response.body();
               Toast.makeText(getContext(), postTeacherManagementResponse.show.message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostUpdateResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error :(", Toast.LENGTH_LONG).show();
            }
        });
    }




    private void build() {
        recyclerView = view.findViewById(R.id.add_rvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapters = new AddTeacherAdapter(getContext(), addTeacherSuggets, pos);
        recyclerView.setAdapter(adapters);
        adapters.setOnItemClickListener(new AddTeacherAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Log.i("pp", String.valueOf(addTeacherSuggets.get(position)));
                textView.setText(addTeacherSuggets.get(position).getTeacherName());

            }

            @Override
            public void onDeleteClick(int position) {
                Log.i("name", String.valueOf(position));
                delete(position);
                addTeacherSuggets.remove(position);
                adapters.notifyItemRemoved(position);
            }

            @Override
            public void onNameClick(int i, String teacherName, int position) {
                textView.setText(addTeacherSuggets.get(position).getTeacherName());
            }
        });
    }

    private TextWatcher addTeacher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String user = textView.getText().toString().trim();
            addButton.setEnabled(!user.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



        public void postTeacherManagement() {
                AddTeacherRequestPost addTeacherRequest = new AddTeacherRequestPost(addedId, subjectId, standardId, deleteteacherId);
                Call<PostUpdateResponse> call = loginService.postTeachermngtCall(addTeacherRequest);
                call.enqueue(new Callback<PostUpdateResponse>() {
            @Override
            public void onResponse(Call<PostUpdateResponse> call, Response<PostUpdateResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                PostUpdateResponse  post = response.body();
                Log.i("TAG", "onResponse: ");
            }
            @Override
            public void onFailure(Call<PostUpdateResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error :(", Toast.LENGTH_LONG).show();
            }
        });
    }
    public  void delete(int position){
        forDelete=position;
        deleteteacherId.add(addTeacherSuggets.get(forDelete).getId());

    }

}