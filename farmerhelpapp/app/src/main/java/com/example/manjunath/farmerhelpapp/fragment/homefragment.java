package com.example.manjunath.farmerhelpapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manjunath.farmerhelpapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class homefragment extends Fragment {


    public homefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homefragment3, container, false);
    }

}
