package com.example.AbacusTest_ArianMostashari.Activity.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.AbacusTest_ArianMostashari.R;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {

  ImageView pic;

  public DemoFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_demo, container, false);

    //get pictures from adapter
    int picture = getArguments().getInt("Picture");

    pic = rootView.findViewById(R.id.img_pics);
    pic.setImageResource(picture);

    return rootView;
  }

}
