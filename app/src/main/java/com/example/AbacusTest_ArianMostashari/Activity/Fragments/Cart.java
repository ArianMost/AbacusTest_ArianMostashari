package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.AbacusTest_ArianMostashari.Model.Cards;
import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.RecyclerView.CardModel;
import com.example.AbacusTest_ArianMostashari.RecyclerView.CartAdapter;
import com.example.AbacusTest_ArianMostashari.ViewModel.CardsViewModel;
import com.example.AbacusTest_ArianMostashari.ViewModel.CustomersViewModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Cart extends Fragment {

  RecyclerView recyclerView;
  CartAdapter cartAdapter;
  ArrayList<CardModel> cardModels;
  String FILE_EMAIL = "Email.txt";
  String customerEmail = "";
  CustomersViewModel customersViewModel;
  CardsViewModel cardsViewModel;




  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

    customersViewModel = ViewModelProviders.of(this).get(CustomersViewModel.class);
    cardsViewModel = ViewModelProviders.of(this).get(CardsViewModel.class);
    initListValue(rootView);


    return rootView;
  }

  private void initListValue(View root) {
    recyclerView = root.findViewById(R.id.cartRecyclerView);
    //create list of integer Arrays
    //List<int[]>pics = new ArrayList<>();
    //pics.add(new int[]{R.drawable.tradingcard1f, R.drawable.tradingcard1b});
    //pics.add(new int[]{R.drawable.tradingcard2f, R.drawable.tradingcard2b});

    //create string Array
    //String [] names = {"Card 1","Card 2"};

    //create float Array
    //String [] prices = {"200", "320"};

/*
    cardsViewModel.getCardsByUserId(customersViewModel.getCustomerById(customerEmail).customerId).observe(getActivity(), cards -> {
      for (Cards card: cards) {
        int pic = card.cardImage;
        int[] pictures = {pic};
        String name = card.cardName;
        String price = card.cardPrice;
        CardModel cardModel = new CardModel(pictures, name, price);
        cardModels.add(cardModel);
        //Design horizontal layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
          getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Initialize adapter
        cartAdapter = new CartAdapter(cardModels, getContext());
        recyclerView.setAdapter(cartAdapter);
      }
      });
 */
    readEmail(FILE_EMAIL);
    cardModels = new ArrayList<>();
    //Initialize ArrayList
    List<Cards> cards = cardsViewModel.getCardsByUserId(customersViewModel.getCustomerById(customerEmail).customerId);
    for (Cards card: cards) {
      int pic = card.cardImage;
      int[] pics = {pic};
      String name = card.cardName;
      String price = card.cardPrice;
      Log.i("Test", "name is: " + name);
      Log.i("Test", "price is: " + price);
      CardModel cardModel = new CardModel(pics, name, price);
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


  public void readEmail(String fileName) {
    FileInputStream fis = null;
    try {
      fis = getActivity().openFileInput(fileName);
      InputStreamReader isr = new InputStreamReader(fis);
      BufferedReader br = new BufferedReader(isr);
      StringBuilder sb = new StringBuilder();
      String email;
      while ((email = br.readLine()) != null){
        customerEmail = sb.append(email).toString();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if (fis != null){
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}