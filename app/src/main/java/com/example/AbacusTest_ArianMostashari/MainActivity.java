package com.example.AbacusTest_ArianMostashari;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.AbacusTest_ArianMostashari.ABase.BaseActivity;
import com.example.AbacusTest_ArianMostashari.Activity.Fragments.Cart;
import com.example.AbacusTest_ArianMostashari.Activity.Fragments.HomePage;
import com.example.AbacusTest_ArianMostashari.Activity.Fragments.LogIn;
import com.example.AbacusTest_ArianMostashari.Activity.Fragments.MyOrders;
import com.example.AbacusTest_ArianMostashari.Activity.Fragments.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener  {


  //setting up the bottom navigation
  private DrawerLayout adrawerLayout;
  private ActionBarDrawerToggle aToggle;
  @SuppressLint("NonConstantResourceId")
  private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
    menuItem -> {
      Fragment selectedFragment = null;
      switch (menuItem.getItemId()) {
        case R.id.nav_home:
          actionbarSetUp("Home page");
          selectedFragment = new HomePage();
          break;
        case R.id.nav_cart:
          actionbarSetUp("Cart");
          selectedFragment = new Cart();
          break;
        case R.id.nav_myorders:
          actionbarSetUp("My orders");
          selectedFragment = new MyOrders();
          break;
      }
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack("fragment_container").commit();
      return true;
    };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);

    editStatusBar();
    actionbarSetUp("Home page");
    setUpTheDrawerLayout();
    setUpTheNavigationItems();
  }

  private void setUpTheNavigationItems() {
    BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
    bottomNav.setOnNavigationItemSelectedListener(navListener);
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomePage()).commit();
  }

  //setting up the drawer
  private void setUpTheDrawerLayout() {
    adrawerLayout = findViewById(R.id.drawer);
    aToggle = new ActionBarDrawerToggle(this, adrawerLayout, R.string.open, R.string.close);
    adrawerLayout.addDrawerListener(aToggle);
    aToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (aToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    Fragment selectedFragment = null;
    switch (menuItem.getItemId()) {
      case R.id.profile:
        actionbarSetUp("My profile");
        selectedFragment = new Profile();
        break;
      case R.id.logIn:
        actionbarSetUp("Log in");
        selectedFragment = new LogIn();
        break;
      case R.id.announcements:
        actionbarSetUp("Home Page");
        selectedFragment = new HomePage();
        break;
    }
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack("fragment_container").commit();
    adrawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }


  @Override
  public void onBackPressed() {
    if (adrawerLayout.isDrawerOpen(GravityCompat.START)) {
      adrawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

}