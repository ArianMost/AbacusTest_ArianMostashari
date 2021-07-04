package com.example.AbacusTest_ArianMostashari.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

  ArrayList<CardModel> cardModels;
  Context context;
  String email;
  int[] pics;
  int id;

  public MyOrdersAdapter(ArrayList<CardModel> cardModels, Context context) {
    this.cardModels = cardModels;
    this.context = context;
  }

  @NonNull
  @Override
  public MyOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //create View
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_myorders, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
    //give value to variables
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

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      //Assign variables
      imgCard = itemView.findViewById(R.id.img_card);
      txtPrice = itemView.findViewById(R.id.txt_price);
      txtName = itemView.findViewById(R.id.txt_productName);
      txtValue = itemView.findViewById(R.id.txt_value);
    }
  }
}
