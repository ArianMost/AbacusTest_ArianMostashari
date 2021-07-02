package com.example.AbacusTest_ArianMostashari;

import android.os.Bundle;
import android.util.Log;

import com.example.AbacusTest_ArianMostashari.Activity.Fragments.DemoFragment;
import com.example.AbacusTest_ArianMostashari.Activity.Item;
import com.example.AbacusTest_ArianMostashari.Database.ProductsData;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DemoFragmentAdapter extends FragmentStatePagerAdapter {

  int position;
  int number;
  Item item = new Item();

  public int getPosition() {
    return position;
  }

  public DemoFragmentAdapter(FragmentManager fm) {
    super(fm);
  }

  public DemoFragmentAdapter(FragmentManager fm, int number) {
    super(fm);
    this.number = number;
  }

  @Override
  public Fragment getItem(int position) {
    DemoFragment demoFragment = new DemoFragment();
    ProductsData productsData = new ProductsData();
    productsData.produceData();
    Bundle bundle = new Bundle();
    int pic = productsData.pics.get(number)[position];
    Log.i("Test", "" + pic);
    bundle.putInt("Picture",pic);
    //bundle.putIntArray(pic);
    bundle.putInt("Progress",position);
    this.position = position;
    //position = position + 1;
    demoFragment.setArguments(bundle);
    return demoFragment;
  }

  @Override
  public int getCount() {
    return 2;
  }
}
