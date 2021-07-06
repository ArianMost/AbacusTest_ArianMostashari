package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.Database.ProductsData;
import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.RecyclerView.AllProductAdapter;
import com.example.AbacusTest_ArianMostashari.RecyclerView.CardModel;
import com.example.AbacusTest_ArianMostashari.RecyclerView.NewestAdapter;
import com.example.AbacusTest_ArianMostashari.RecyclerView.TopRatedAdapter;
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


public class HomePage extends Fragment {

  RecyclerView allProducts;
  RecyclerView topRated;
  RecyclerView newest;
  ArrayList<CardModel> cardModels;
  AllProductAdapter allProductAdapter;
  TopRatedAdapter topRatedAdapter;
  NewestAdapter newestAdapter;
  ScrollView scrollbar;
  CustomersViewModel customersViewModel;
  TextView txtWelcome;
  String FILE_EMAIL = "Email.txt";
  String customerEmail = "";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);

    setTheComponents(rootView);

    readEmail(FILE_EMAIL);
    if (customerEmail.isEmpty()){
      txtWelcome.setVisibility(View.GONE);
    }else {
      customersViewModel = ViewModelProviders.of(this).get(CustomersViewModel.class);
      String name= customersViewModel.getCustomerById(customerEmail).customerName;
      txtWelcome.setVisibility(View.VISIBLE);
      txtWelcome.setText("Welcome " + name);
    }

    //create int Array
    ProductsData productsData = new ProductsData();
    productsData.produceData();
    List<int[]> pics = new ArrayList<>();
    pics = productsData.pics;

    //create string Arrays
    String []names = productsData.names;
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
    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(
      getContext(), LinearLayoutManager.HORIZONTAL, false);
    topRated.setLayoutManager(linearLayoutManager1);
    topRated.setItemAnimator(new DefaultItemAnimator());
    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(
      getContext(), LinearLayoutManager.HORIZONTAL, false);
    newest.setLayoutManager(linearLayoutManager2);
    newest.setItemAnimator(new DefaultItemAnimator());


    //Initialize adapter
    allProductAdapter = new AllProductAdapter(cardModels, getContext());
    allProducts.setAdapter(allProductAdapter);
    topRatedAdapter = new TopRatedAdapter(cardModels, getContext());
    topRated.setAdapter(topRatedAdapter);
    newestAdapter = new NewestAdapter(cardModels, getContext());
    newest.setAdapter(newestAdapter);

    return rootView;
  }

  private void setTheComponents(View root) {
    allProducts = root.findViewById(R.id.recyclerview_allProducts);
    topRated = root.findViewById(R.id.recyclerview_topRatedProducts);
    newest = root.findViewById(R.id.recyclerview_newestProducts);
    scrollbar = root.findViewById(R.id.scrBar);
    txtWelcome = root.findViewById(R.id.txt_welcome);
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