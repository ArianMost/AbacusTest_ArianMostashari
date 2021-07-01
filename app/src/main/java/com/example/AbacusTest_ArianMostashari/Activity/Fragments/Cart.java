package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.RecyclerView.CartAdapter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Cart extends Fragment {

  CartAdapter cartAdapter;
  List<String> ids;
  List<String> names;
  List<Float> prices;
  List<Integer> imgs;



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

    initListValue(rootView);

    return rootView;
  }

  private void initListValue(View root) {
    RecyclerView recyclerView = root.findViewById(R.id.cartRecyclerView);
    cartAdapter = new CartAdapter(getActivity(), ids, names, imgs, prices);
    recyclerView.setAdapter(cartAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
  }
}