package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.ABase.BaseFragment;
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

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Cart extends BaseFragment {

  RecyclerView recyclerView;
  TextView txtIsEmpty;
  TextView txtTotalPrice;
  Button btnBuy;
  CartAdapter cartAdapter;
  ArrayList<CardModel> cardModels;
  String FILE_EMAIL = "Email.txt";
  String customerEmail = "";
  CustomersViewModel customersViewModel;
  CardsViewModel cardsViewModel;
  Handler handler;




  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

    customersViewModel = ViewModelProviders.of(this).get(CustomersViewModel.class);
    cardsViewModel = ViewModelProviders.of(this).get(CardsViewModel.class);
    initListValue(rootView);
    btnBuy.setOnClickListener(v -> {
      Intent intent = new Intent(getContext(), Payment.class);
      getActivity().startActivity(intent);
      });

    return rootView;
  }

  private void initListValue(View root) {
    recyclerView = root.findViewById(R.id.cartRecyclerView);
    txtIsEmpty = root.findViewById(R.id.txt_isEmpty);
    txtTotalPrice = root.findViewById(R.id.txt_totalPrice);
    btnBuy = root.findViewById(R.id.btn_buy);

    readEmail(FILE_EMAIL);
    if (!customerEmail.isEmpty()){
      txtTotalPrice.setText("0$");
      //run these functions every 200millis
      handler = new Handler();
      Thread thread = new Thread(() -> {
        while (true) {
          try {
            handler.post(() -> {
              cardModels = new ArrayList<>();
              //Initialize ArrayList
              List<Cards> cards = cardsViewModel.getCardsByUserId(customersViewModel.getCustomerById(customerEmail).customerId);
              if (cards.isEmpty()){
                txtIsEmpty.setText("The cart is empty!");
                txtTotalPrice.setText("0$");
              } else {
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
                double totalprice = 0;
                for (String price: prices) {
                  double dPrice = Double.parseDouble(price);
                  totalprice += dPrice;
                  txtTotalPrice.setText(totalprice + "$");
                }
              }

              //Design layout
              LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false);
              recyclerView.setLayoutManager(linearLayoutManager);
              recyclerView.setItemAnimator(new DefaultItemAnimator());

              //Initialize adapter
              cartAdapter = new CartAdapter(cardModels, getContext(), this, getActivity());
              cartAdapter.notifyDataSetChanged();
              recyclerView.setAdapter(cartAdapter);
            });
            Thread.sleep(300);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
      thread.start();

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