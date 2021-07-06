package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.ABase.BaseFragment;
import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.RecyclerView.CardModel;
import com.example.AbacusTest_ArianMostashari.RecyclerView.MyOrdersAdapter;
import com.example.AbacusTest_ArianMostashari.ViewModel.MyOrdersViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MyOrders extends BaseFragment {

  MyOrdersViewModel myOrdersViewModel;
  RecyclerView recyclerView;
  TextView txtIsEmpty;
  ArrayList<CardModel> cardModels;
  MyOrdersAdapter myOrdersAdapter;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);

    myOrdersViewModel = ViewModelProviders.of(this).get(MyOrdersViewModel.class);
    initListValue(rootView);





    return rootView;
  }

  private void initListValue(View root) {

    recyclerView = root.findViewById(R.id.cartRecyclerView);
    txtIsEmpty = root.findViewById(R.id.txt_isEmpty);


    cardModels = new ArrayList<>();
    List<com.example.AbacusTest_ArianMostashari.Model.MyOrders> myOrders = myOrdersViewModel.getAllOrders;
    if (myOrders.isEmpty()){
      txtIsEmpty.setText("The list of orders is empty!");
    }else {
      txtIsEmpty.setText("The list of purchased items:");
      ArrayList<String> names = new ArrayList<>();
      int value;
      for (com.example.AbacusTest_ArianMostashari.Model.MyOrders myOrder: myOrders) {
        //prevent duplication in the list
        if (names.contains(myOrder.cardName)){
          int index = names.indexOf(myOrder.cardName);
          cardModels.get(index).setValue(cardModels.get(index).getValue()+ 1);
        }else {
          String name = myOrder.cardName;
          names.add(name);
          value = 1;
          int pic = myOrder.cardImage;
          int[] pics = {pic};
          String price = myOrder.cardPrice;
          CardModel cardModel = new CardModel(pics, name, price, value, "", myOrder.cardId);
          cardModels.add(cardModel);
        }
      }
    }
    //Design layout

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
      getContext(), LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    //Initialize adapter
    myOrdersAdapter = new MyOrdersAdapter(cardModels, getContext());
    recyclerView.setAdapter(myOrdersAdapter);
  }
}