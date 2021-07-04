package com.example.AbacusTest_ArianMostashari.Model;

import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.ViewModel.CardsViewModel;
import com.example.AbacusTest_ArianMostashari.ViewModel.CustomersViewModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customers_database")
public class Customers {

  @PrimaryKey(autoGenerate = true)
  public int customerId;

  @ColumnInfo(name = "customer_name")
  public String customerName;

  @ColumnInfo(name = "customer_surname")
  public String customerSurname;

  @ColumnInfo(name = "customer_phoneNumber")
  public String customerPhoneNumber;

  @ColumnInfo(name = "customer_email")
  public String customerEmail;

  @ColumnInfo(name = "customer_password")
  public String customerPassword;

  @ColumnInfo(name = "customer_address")
  public String customerAddress;

  public static void addingToCart(String email, TextView txtName, TextView txtPrice, int[] pics, CustomersViewModel customersViewModel, CardsViewModel cardsViewModel){
    String productName = txtName.getText().toString();
    String productPrice;
    if (txtPrice.getText().toString().contains("$")){
      productPrice = txtPrice.getText().toString().replace("$", "");
    }else {
      productPrice = txtPrice.getText().toString();
    }
    int productPicture = pics[0];
    creatCard(email, productName, productPrice, productPicture, customersViewModel, cardsViewModel);
  }
  private static void creatCard(String email, String productName, String productPrice, int productPicture, CustomersViewModel customersViewModel, CardsViewModel cardsViewModel) {
    Cards cards = new Cards();

    cards.cardName = productName;
    cards.cardPrice = productPrice;
    cards.cardImage = productPicture;
    cards.customerId = customersViewModel.getCustomerById(email).customerId;

    cardsViewModel.addCards(cards);
  }

  public static void removingFromCart(int id, CardsViewModel cardsViewModel){
    cardsViewModel.deleteCard(id);
  }
}
