package com.example.AbacusTest_ArianMostashari.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.ABase.BaseActivity;
import com.example.AbacusTest_ArianMostashari.DemoFragmentAdapter;
import com.example.AbacusTest_ArianMostashari.R;

import androidx.core.content.ContextCompat;
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
    adapter = new DemoFragmentAdapter(getSupportFragmentManager(), number);
    viewPager.setAdapter(adapter);
    dotsCounter = adapter.getCount();
    dots = new ImageView[dotsCounter];
    txtName.setText(name);
    txtPrice.setText(price);

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
    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

  }

  private void setComponents() {
    sliderDotsPanel = findViewById(R.id.sliderdots);
    txtName = findViewById(R.id.txt_name);
    txtPrice = findViewById(R.id.txt_price);
    viewPager = findViewById(R.id.pager);
    btnAdd = findViewById(R.id.btn_add);
  }
}