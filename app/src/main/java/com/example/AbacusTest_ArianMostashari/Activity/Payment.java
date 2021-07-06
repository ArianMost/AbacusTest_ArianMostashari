package com.example.AbacusTest_ArianMostashari.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.AbacusTest_ArianMostashari.ABase.BaseActivity;
import com.example.AbacusTest_ArianMostashari.MainActivity;
import com.example.AbacusTest_ArianMostashari.Model.Cards;
import com.example.AbacusTest_ArianMostashari.Model.MyOrders;
import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.ViewModel.CardsViewModel;
import com.example.AbacusTest_ArianMostashari.ViewModel.CustomersViewModel;
import com.example.AbacusTest_ArianMostashari.ViewModel.MyOrdersViewModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import androidx.lifecycle.ViewModelProviders;

public class Payment extends BaseActivity {

  Button btnPay;
  CardsViewModel cardsViewModel;
  MyOrdersViewModel myOrdersViewModel;
  CustomersViewModel customersViewModel;
  String FILE_EMAIL = "Email.txt";
  String customerEmail = "";
  List<MyOrders> myOrders;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_payment);


    editStatusBar();
    actionbarSetUp("Home page");

    btnPay = findViewById(R.id.btn_pay);
    myOrdersViewModel = ViewModelProviders.of(this).get(MyOrdersViewModel.class);
    customersViewModel = ViewModelProviders.of(this).get(CustomersViewModel.class);
    cardsViewModel = ViewModelProviders.of(this).get(CardsViewModel.class);

    btnPay.setOnClickListener( v -> {
      //move data from card db to myOrders db
      readEmail(FILE_EMAIL);
      List<Cards> cards = cardsViewModel.getCardsByUserId(customersViewModel.getCustomerById(customerEmail).customerId);
      for (Cards card: cards) {
        String productName = card.cardName;
        String productPrice = card.cardPrice;
        int productPicture = card.cardImage;
        creatCard(productName, productPrice, productPicture);
      }
      //remove data from card db
      cardsViewModel.deleteAllCards();

      Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show();
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
    });

  }

  private void creatCard(String productName, String productPrice, int productPicture) {
    MyOrders myOrders = new MyOrders();

    myOrders.cardName = productName;
    myOrders.cardPrice = productPrice;
    myOrders.cardImage = productPicture;
    myOrders.customerId = customersViewModel.getCustomerById(customerEmail).customerId;

    myOrdersViewModel.addOrders(myOrders);
  }

  public void readEmail(String fileName) {
    FileInputStream fis = null;
    try {
      fis = openFileInput(fileName);
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