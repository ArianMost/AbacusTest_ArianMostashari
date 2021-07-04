package com.example.AbacusTest_ArianMostashari.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.Activity.Item;
import com.example.AbacusTest_ArianMostashari.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {

  ArrayList<CardModel> cardModels;
  Context context;
  Intent intent;

  public AllProductAdapter(ArrayList<CardModel> cardModels, Context context) {
    this.cardModels = cardModels;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //create View
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);
    viewHolder.rowItems.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                               intent = new Intent(context, Item.class);
                                               intent.putExtra("Image", cardModels.get(viewHolder.getAdapterPosition()).picture);
                                               intent.putExtra("Number", viewHolder.getAdapterPosition());
                                               intent.putExtra("Name", cardModels.get(viewHolder.getAdapterPosition()).name);
                                               intent.putExtra("Price", cardModels.get(viewHolder.getAdapterPosition()).price);
                                               context.startActivity(intent);
                                             }
                                           });
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolder viewHolder, int position) {
    //give value to variables
    viewHolder.imgCard.setImageResource(cardModels.get(position).picture[0]);
    viewHolder.txtPrice.setText(cardModels.get(position).price + "$");
    viewHolder.txtName.setText(cardModels.get(position).name);
  }

  @Override
  public int getItemCount() {
    return cardModels.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    //Initialize variables
    ConstraintLayout rowItems;
    ImageView imgCard;
    TextView txtPrice;
    TextView txtName;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      //Assign variables
      rowItems = itemView.findViewById(R.id.rowItems);
      imgCard = itemView.findViewById(R.id.img_card);
      txtPrice = itemView.findViewById(R.id.txt_price);
      txtName = itemView.findViewById(R.id.txt_name);
    }
  }
}
