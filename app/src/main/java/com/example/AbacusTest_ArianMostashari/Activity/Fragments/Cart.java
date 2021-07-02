package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.RecyclerView.CardModel;
import com.example.AbacusTest_ArianMostashari.RecyclerView.CartAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Cart extends Fragment {

  RecyclerView recyclerView;
  CartAdapter cartAdapter;
  ArrayList<CardModel> cardModels;



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

    initListValue(rootView);

    return rootView;
  }

  private void initListValue(View root) {
    recyclerView = root.findViewById(R.id.cartRecyclerView);
    //create list of integer Arrays
    List<int[]>pics = new ArrayList<>();
    pics.add(new int[]{R.drawable.tradingcard1f, R.drawable.tradingcard1b});
    pics.add(new int[]{R.drawable.tradingcard2f, R.drawable.tradingcard2b});

    //create string Array
    String [] names = {"Card 1","Card 2"};

    //create float Array
    String [] prices = {"200", "320"};

    //Initialize ArrayList
    cardModels = new ArrayList<>();
    for (int i = 0 ; i < names.length ; i++){
      CardModel cardModel = new CardModel(pics.get(i), names[i], prices[i]);
      cardModels.add(cardModel);
    }
    //Design horizontal layout
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
      getContext(), LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    //Initialize adapter
    cartAdapter = new CartAdapter(cardModels, getContext());
    recyclerView.setAdapter(cartAdapter);
  }
}