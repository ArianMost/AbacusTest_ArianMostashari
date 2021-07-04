package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.Activity.Payment;
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
  TextView txtIsEmpty;
  TextView txtTotalPrice;
  Button btnBuy;
  double totalprice;
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
    txtTotalPrice.setText(totalprice + "$");
    btnBuy.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), Payment.class);
      getActivity().startActivity(intent);
      });

    //getFragmentManager().beginTransaction().detach(Cart.this).attach(Cart.this).commit();
    //Fragment frg = getFragmentManager().findFragmentById(R.id.fragmentcart);
    //final FragmentTransaction ft = getFragmentManager().beginTransaction();
    //ft.detach(frg);
    //ft.attach(frg);
    //ft.commit();



    return rootView;
  }

  private void initListValue(View root) {
    recyclerView = root.findViewById(R.id.cartRecyclerView);
    txtIsEmpty = root.findViewById(R.id.txt_isEmpty);
    txtTotalPrice = root.findViewById(R.id.txt_totalPrice);
    btnBuy = root.findViewById(R.id.btn_buy);

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
    if (!customerEmail.isEmpty()){
      cardModels = new ArrayList<>();
      //Initialize ArrayList
      List<Cards> cards = cardsViewModel.getCardsByUserId(customersViewModel.getCustomerById(customerEmail).customerId);
      if (cards.isEmpty()){
        txtIsEmpty.setText("The cart is empty!");
      }
      else {
        txtIsEmpty.setText("The list of selected items:");
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        int value;
        for (Cards card: cards) {
          prices.add(card.cardPrice);
          //prevent duplication in the list
          if (names.contains(card.cardName)){
            int index = names.indexOf(card.cardName);
            cardModels.get(index).setValue(cardModels.get(index).getValue()+ 1);
          }else {
            String name = card.cardName;
            names.add(name);
            value = 1;
            int pic = card.cardImage;
            int[] pics = {pic};
            String price = card.cardPrice;
            CardModel cardModel = new CardModel(pics, name, price, value, customerEmail, card.cardId);
            cardModels.add(cardModel);
          }
        }
        //calculate the total price
        for (String price: prices) {
          double dPrice = Double.parseDouble(price);
          totalprice += dPrice;
        }
      }

      //Design layout
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
        getContext(), LinearLayoutManager.VERTICAL, false);
      recyclerView.setLayoutManager(linearLayoutManager);
      recyclerView.setItemAnimator(new DefaultItemAnimator());

      //Initialize adapter
      cartAdapter = new CartAdapter(cardModels, getContext(), this);
      recyclerView.setAdapter(cartAdapter);
      }
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