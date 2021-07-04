package com.example.AbacusTest_ArianMostashari.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.Activity.Fragments.Cart;
import com.example.AbacusTest_ArianMostashari.Model.Customers;
import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.ViewModel.CardsViewModel;
import com.example.AbacusTest_ArianMostashari.ViewModel.CustomersViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

  ArrayList<CardModel> cardModels;
  Context context;
  String email;
  int[] pics;
  CustomersViewModel customersViewModel;
  CardsViewModel cardsViewModel;
  Cart cart;
  int id;

  public CartAdapter(ArrayList<CardModel> cardModels, Context context, Cart cart) {
    this.cardModels = cardModels;
    this.context = context;
    this.cart = cart;
  }

  @NonNull
  @Override
  public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //create View
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);
    //set the increase button
    viewHolder.btnIncrease.setOnClickListener(v -> {
      //adding data to the database
      customersViewModel = ViewModelProviders.of(cart).get(CustomersViewModel.class);
      cardsViewModel = ViewModelProviders.of(cart).get(CardsViewModel.class);
      Customers.addingToCart(email, viewHolder.txtName, viewHolder.txtPrice, pics, customersViewModel, cardsViewModel);
      //increase the value on the list
      int val = Integer.parseInt(viewHolder.txtValue.getText().toString()) +1;
      viewHolder.txtValue.setText(val+ "");
    });
    //set the decrease button
    viewHolder.btnDecrease.setOnClickListener(v -> {
      //removing data from the database
      customersViewModel = ViewModelProviders.of(cart).get(CustomersViewModel.class);
      cardsViewModel = ViewModelProviders.of(cart).get(CardsViewModel.class);
      Customers.removingFromCart(id, cardsViewModel);
      //decrease the value on the list
      int val = Integer.parseInt(viewHolder.txtValue.getText().toString()) -1;
      viewHolder.txtValue.setText(val+ "");
    });
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
    //give value to variables
    email = cardModels.get(position).email;
    id = cardModels.get(position).cardId;
    pics = new int[]{cardModels.get(position).picture[0]};
    viewHolder.imgCard.setImageResource(cardModels.get(position).picture[0]);
    viewHolder.txtPrice.setText(cardModels.get(position).price);
    viewHolder.txtName.setText(cardModels.get(position).name);
    viewHolder.txtValue.setText(cardModels.get(position).getValue() +"");
  }

  @Override
  public int getItemCount() {
    return cardModels.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    //Initialize variables
    ImageView imgCard;
    TextView txtPrice;
    TextView txtName;
    TextView txtValue;
    TextView btnIncrease;
    TextView btnDecrease;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      //Assign variables
      imgCard = itemView.findViewById(R.id.img_card);
      txtPrice = itemView.findViewById(R.id.txt_price);
      txtName = itemView.findViewById(R.id.txt_productName);
      txtValue = itemView.findViewById(R.id.txt_value);
      btnDecrease = itemView.findViewById(R.id.btn_decrease);
      btnIncrease = itemView.findViewById(R.id.btn_increase);
    }
  }
}
