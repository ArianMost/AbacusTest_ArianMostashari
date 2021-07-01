package com.example.AbacusTest_ArianMostashari.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


  private List<String> ids = new ArrayList<>();
  private List<String> names = new ArrayList<>();
  private List<Float> prices = new ArrayList<>();
  private List<Integer> imgs = new ArrayList<>();
  private Context aContext;

  public CartAdapter(Context aContext, List<String> ids, List<String> names, List<Integer> imgs, List<Float> prices) {
    this.ids = ids;
    this.names = names;
    this.prices = prices;
    this.imgs = imgs;
    this.aContext = aContext;
  }

  @NonNull
  @Override
  public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cart, parent, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.txtName.setText(names.get(position));
    holder.txtPrice.setText(prices.get(position).toString());
    holder.imgProduct.setImageResource(imgs.get(position));
    holder.txtValue.setText("1");
  }

  @Override
  public int getItemCount() {
    return ids.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtName;
    TextView txtPrice;
    TextView txtValue;
    ImageView imgProduct;
    TextView btnIncrease;
    TextView btnDecrease;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      txtName = itemView.findViewById(R.id.txt_productName);
      txtPrice = itemView.findViewById(R.id.txt_price);
      txtValue = itemView.findViewById(R.id.txt_value);
      imgProduct = itemView.findViewById(R.id.img_card);
      btnDecrease = itemView.findViewById(R.id.btn_decrease);
      btnIncrease = itemView.findViewById(R.id.btn_increase);
    }
  }
}
