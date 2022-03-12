package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digitallibraryadmin.ApiLibrary.LoginService;
import com.example.digitallibraryadmin.R;

import retrofit2.Retrofit;


public class PdfReader extends Fragment {
ImageView back;
View view;


    int chapter,standard,topic;
    String parameter,name,pdfFile;

    public PdfReader() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.pdf_layout, container, false);
         pdfFile=String.valueOf(getArguments().getString("questionPosition"));
         parameter=String.valueOf(getArguments().getString("parameter"));
        chapter=Integer.valueOf(getArguments().getString("chapterIdQuestion"));
        standard=Integer.valueOf(getArguments().getString("standardIdQuestion"));
        topic=Integer.valueOf(getArguments().getString("topicIdQuestion"));
        name=getArguments().getString("questionName");
        Log.i("file",String.valueOf(pdfFile));

        TextView tv1 =view.findViewById(R.id.question_pdf_tool_bar);
        tv1.setText(name);

        back=view.findViewById(R.id.back_question_pdf);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        web();
            return view;
    }
    public  void  web(){
        WebView webView = view.findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://docs.google.com/viewer?url="+"https://test-digital-library.s3.ap-south-1.amazonaws.com/"+pdfFile);
     //   webView.loadUrl("https://youtu.be/mAtkPQO1FcA");
    }
}