package com.example.AbacusTest_ArianMostashari.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.ABase.BaseActivity;
import com.example.AbacusTest_ArianMostashari.DemoFragmentAdapter;
import com.example.AbacusTest_ArianMostashari.Model.Customers;
import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.ViewModel.CardsViewModel;
import com.example.AbacusTest_ArianMostashari.ViewModel.CustomersViewModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class Item extends BaseActivity {


  LinearLayout sliderDotsPanel;
  private int dotsCounter;
  private ImageView[] dots;
  private ViewPager viewPager;
  private DemoFragmentAdapter adapter;
  public int[] pics;
  TextView txtName;
  TextView txtPrice;
  Button btnAdd;
  String FILE_EMAIL = "Email.txt";
  String customerEmail = "";
  CustomersViewModel customersViewModel;
  CardsViewModel cardsViewModel;
  String productName, productPrice;
  int productPicture;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_item);

    editStatusBar();
    actionbarSetUp("");
    Intent intent = getIntent();
    pics = intent.getIntArrayExtra("Image");
    String name = intent.getStringExtra("Name");
    String price = intent.getStringExtra("Price");
    int number = intent.getIntExtra("Number" ,0);
    setComponents();
    //initialize cardViewModel
    cardsViewModel = ViewModelProviders.of(this).get(CardsViewModel.class);
    customersViewModel = ViewModelProviders.of(this).get(CustomersViewModel.class);
    //prepare adapter for viewPager
    adapter = new DemoFragmentAdapter(getSupportFragmentManager(), number);
    viewPager.setAdapter(adapter);
    //prepare dots counter
    dotsCounter = adapter.getCount();
    dots = new ImageView[dotsCounter];
    txtName.setText(name);
    txtPrice.setText(price + "$");

    //create dot layout
    for (int i = 0; i < dotsCounter; i++) {
      dots[i] = new ImageView(this);
      dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
      params.setMargins(5, 0, 5, 0);
      sliderDotsPanel.addView(dots[i], params);
    }
    dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int i, float v, int i1) {

      }

      @Override
      public void onPageSelected(int position) {
        for (int i = 0; i < dotsCounter; i++) {
          dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
        }
        dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
      }

      @Override
      public void onPageScrollStateChanged(int i) {

      }
    });

    btnAdd.setOnClickListener(v -> {
      readEmail(FILE_EMAIL);
      Customers.addingToCart(customerEmail, txtName, txtPrice, pics, customersViewModel, cardsViewModel);
      //productName = txtName.getText().toString();
      //productPrice = txtPrice.getText().toString();
      //productPicture = pics[0];
      //creatCard(productName, productPrice, productPicture);
    });

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
  private void setComponents() {
    sliderDotsPanel = findViewById(R.id.sliderdots);
    txtName = findViewById(R.id.txt_name);
    txtPrice = findViewById(R.id.txt_price);
    viewPager = findViewById(R.id.pager);
    btnAdd = findViewById(R.id.btn_add);
  }
}