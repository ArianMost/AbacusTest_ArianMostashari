package com.example.AbacusTest_ArianMostashari.ABase;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.example.AbacusTest_ArianMostashari.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class BaseActivity extends AppCompatActivity {

  public void startActivityBase(Class<?> className) {
    Intent intent = new Intent(this, className);
    startActivity(intent);
  }

  public void actionbarSetUp(String title) {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setTitle(title);
    actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2d3748")));
  }

  public void startActivityWithBoolean(Class<?> className, boolean bool) {
    Intent intent = new Intent(this, className);
    intent.putExtra("boolean", bool);
    startActivity(intent);
  }

  public void startActivityBaseWithData(Class<?> className, boolean isPast, int formId) {
    Intent intent = new Intent(this, className);
    intent.putExtra("isPast", isPast);
    intent.putExtra("formId", formId);
    startActivity(intent);
  }

  public void preventAutoKeyboard() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
      this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
  }

  public void editStatusBar() {
    //getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
    //  WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    Window window = getWindow();

    // clear FLAG_TRANSLUCENT_STATUS flag:
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    // finally change the color
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.status_bar_color));
    }
  }

}
