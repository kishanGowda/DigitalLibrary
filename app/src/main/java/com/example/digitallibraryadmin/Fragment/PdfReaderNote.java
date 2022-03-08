package com.example.digitallibraryadmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digitallibraryadmin.R;


public class PdfReaderNote extends Fragment {

View view;
String pdfFile,name;
ImageView back;
    String subjectName,standardName,topicName,chapterName,sectionName;
    public PdfReaderNote() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_pdf_reader_note, container, false);

        pdfFile=String.valueOf(getArguments().getString("notePosition"));
        name=getArguments().getString("name");

        TextView tv1 =view.findViewById(R.id.note_pdf_tool_bar);
        tv1.setText(name);
//        Log.i("topicIdQuestion",String.valueOf(topic));
//        Log.i("chapterIdQuestion",String.valueOf(chapter));
//        Log.i("standardIdQuestion",String.valueOf(standard));
        back=view.findViewById(R.id.back_note_pdf);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        WebView webView = view.findViewById(R.id.web_note);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+pdfFile);
        return  view;
    }
}