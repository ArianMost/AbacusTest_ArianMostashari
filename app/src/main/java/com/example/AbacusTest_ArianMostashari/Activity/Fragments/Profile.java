package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.AbacusTest_ArianMostashari.R;

import androidx.fragment.app.Fragment;


public class Profile extends Fragment {






  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_profile, container, false);











    return rootView;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  //creating Edit option on top pf the screen
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    // TODO Add your menu entries here
    inflater.inflate(R.menu.edit, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }
}