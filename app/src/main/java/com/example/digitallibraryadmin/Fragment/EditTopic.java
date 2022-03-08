package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.digitallibraryadmin.ApiLibrary.ApiClient;
import com.example.digitallibraryadmin.ApiLibrary.EditTopicRequest;
import com.example.digitallibraryadmin.ApiLibrary.EditTopicResponse;
import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditTopic extends Fragment {
View view;
int topicId;
String topicName;
LoginService loginService;
Retrofit retrofit;
EditText nameEditText;
Button saveButton;
ImageView back;

    String newTopicName="";
    public EditTopic() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_edit_topic, container, false);
        Bundle bundle = getArguments();
        topicId = Integer.valueOf(bundle.getString("topicId"));
        topicName=bundle.getString("topicName");
        String newTopicName="";
        nameEditText=view.findViewById(R.id.editTopicName);
        nameEditText.setText(topicName);
        Log.i("etn", topicName);
        Log.i("eti", String.valueOf(topicId));

        saveButton=view.findViewById(R.id.editTopicButtonSave);


        apiInit();

        back=view.findViewById(R.id.back_edit_topic);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        // save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nameEditText.getText().toString().trim().isEmpty()){
                    createUpdateEdit();
                }else
                {
                    Toast.makeText(getContext(), "Please enter topic Name", Toast.LENGTH_LONG).show();
                }

            }
        });
        return  view;

    }

    public void apiInit() {

        retrofit = ApiClient.getRetrofit();
        loginService = ApiClient.getApiService();

    }



    public void createUpdateEdit() {
        newTopicName=nameEditText.getText().toString();
        EditTopicRequest createUpdateEditRequest = new EditTopicRequest(topicId,newTopicName);
        Call<EditTopicResponse> call = loginService.editTopicCall(createUpdateEditRequest);
        call.enqueue(new Callback<EditTopicResponse>() {
            @Override
            public void onResponse(Call<EditTopicResponse> call, Response<EditTopicResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                EditTopicResponse createUpdateEditResponse = response.body();
                Toast.makeText(getContext(),createUpdateEditResponse.show.message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<EditTopicResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error :(", Toast.LENGTH_LONG).show();
            }
        });
    }

}