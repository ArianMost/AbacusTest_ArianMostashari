package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.AbacusTest_ArianMostashari.Database.ProductsData;
import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.RecyclerView.AllProductAdapter;
import com.example.AbacusTest_ArianMostashari.RecyclerView.CardModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomePage extends Fragment {

  RecyclerView allProducts;
  RecyclerView topRated;
  RecyclerView newest;
  ArrayList<CardModel> cardModels;
  AllProductAdapter allProductAdapter;
  ListView lv;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);

    setTheComponents(rootView);

    //create integer Array
    ProductsData productsData = new ProductsData();
    productsData.produceData();
    List<int[]> pics = new ArrayList<>();
    pics = productsData.pics;

    //create string Array
    String []names = productsData.names;

    //create float Array
    String [] prices = productsData.prices;

    //Initialize ArrayList
    cardModels = new ArrayList<>();
    for (int i = 0 ; i < names.length ; i++){
      CardModel cardModel = new CardModel(pics.get(i), names[i], prices[i], 0, "", 0);
      cardModels.add(cardModel);
    }

    //Design horizontal layout
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    getContext(), LinearLayoutManager.HORIZONTAL, false);
    allProducts.setLayoutManager(linearLayoutManager);
    allProducts.setItemAnimator(new DefaultItemAnimator());


    //Initialize adapter
    allProductAdapter = new AllProductAdapter(cardModels, getContext());
    allProducts.setAdapter(allProductAdapter);


    return rootView;
  }

  private void setTheComponents(View root) {
    allProducts = root.findViewById(R.id.recyclerview_allProducts);
    topRated = root.findViewById(R.id.recyclerview_topRatedProducts);
    newest = root.findViewById(R.id.recyclerview_newestProducts);
  }
}