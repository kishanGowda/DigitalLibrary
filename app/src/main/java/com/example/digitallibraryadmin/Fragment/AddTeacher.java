package com.example.digitallibraryadmin.Fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.digitallibraryadmin.Adapter.AddTeacherAdapter;
import com.example.digitallibraryadmin.ApiLibrary.AddTeacherRequestPost;
import com.example.digitallibraryadmin.ApiLibrary.AddTeacherResponse;
import com.example.digitallibraryadmin.ApiLibrary.AddTeacherResponsePost;
import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
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
    Button addButton;
    RecyclerView recyclerView;
    AddTeacherAdapter adapters;
    LinearLayoutManager layoutManager;
    ArrayList<AddTeacherModel> addTeacherModels = new ArrayList<>();
    AutoCompleteTextView textView;
    int subjectId, standardId;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<AddTeacherModel> addTeacherSuggets = new ArrayList<>();
    LoginService loginService;
    Retrofit retrofit;
    ImageView back;
    String name;
    int size;
    int count;
    List<AddTeacherResponse> addTeacherResponses;




    public AddTeacher() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TransitionInflater inflate = TransitionInflater.from(requireContext());
        setExitTransition(inflate.inflateTransition(R.transition.fade));
        view = inflater.inflate(R.layout.fragment_add_teacher, container, false);
        standardId = Integer.valueOf(getArguments().getString("standardId"));
        subjectId = Integer.valueOf(getArguments().getString("subjectId"));
        Log.i("TAG", String.valueOf(standardId));
        Log.i("TAG", String.valueOf(subjectId));
        addButton = view.findViewById(R.id.add);
        textView = view.findViewById(R.id.add_auto);
        textView.addTextChangedListener(addTeacher);
        apiInit();
        addTeacherss();

        back = view.findViewById(R.id.back_teacher_add);
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
//             String hello  = adapter.getAutofillOptions().toString();
//                Log.i("hello", hello);

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
                name=sug;
                Log.i("name", name);
                //
                count=0;

                for(int i=0;i<=size-1;i++){
                    if(name.equals(addTeacherResponses.get(i).getName())){
                       break;
                    }
                    else{
                        count++;
                    }
                }
                Log.i("count", String.valueOf(count));

                //
                boolean isPresent = false;
                for (int j = 0; j <= list.size() - 1; j++) {
                    if (list.get(j).equals(sug)) {
                        isPresent = true;
                     addTeacherSuggets.add(new AddTeacherModel(sug, R.drawable.ic_baseline_close_24));
                        Log.i("TAG", String.valueOf(addTeacherSuggets.size()));
                        build();
                        postTeacherManagement();
                    }
                }
                if (!isPresent)
                    Toast.makeText(getContext(), "Enter Valid Teacher Name", Toast.LENGTH_SHORT).show();
            }




        });

    }
    public  void removeItem(int position){
        addTeacherSuggets.remove(position);

    }

    public void postTeacherManagement() {
        ArrayList<Integer> teacherId = new ArrayList<>();
        teacherId.add(count);
        ArrayList<Integer> deleteteacherId=new ArrayList<>();
        AddTeacherRequestPost addTeacherRequest = new AddTeacherRequestPost(teacherId,subjectId,standardId,deleteteacherId);
        Call<AddTeacherResponsePost> call = loginService.postTeachermngtCall(addTeacherRequest);
        Log.i("teacherId",String.valueOf( teacherId));
        Log.i("subjectId",String.valueOf( subjectId));
        Log.i("standardId",String.valueOf( standardId));
        call.enqueue(new Callback<AddTeacherResponsePost>() {
            @Override
            public void onResponse(Call<AddTeacherResponsePost> call, Response<AddTeacherResponsePost> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                AddTeacherResponsePost postTeacherManagementResponse = response.body();
                Toast.makeText(getContext(),postTeacherManagementResponse.show.message, Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().popBackStack();

            }
            @Override
            public void onFailure(Call<AddTeacherResponsePost> call, Throwable t) {
                Toast.makeText(getContext(), "Error :(", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void build() {
        recyclerView = view.findViewById(R.id.add_rvv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapters = new AddTeacherAdapter(getContext(), addTeacherSuggets);
        recyclerView.setAdapter(adapters);
        adapters.setOnItemClickListener(new AddTeacherAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Log.i("pp", String.valueOf(addTeacherSuggets.get(position)));
                textView.setText(addTeacherSuggets.get(position).getTeacherName());

            }

            @Override
            public void onDeleteClick(int position) {
                addTeacherSuggets.remove(position);
                adapters.notifyItemRemoved(position);
            }

            @Override
            public void onNameClick(int position) {
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
}