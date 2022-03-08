package com.example.digitallibraryadmin.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.digitallibraryadmin.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomsheetDialog extends BottomSheetDialogFragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.edit,container,false);
        return v;
    }
}
