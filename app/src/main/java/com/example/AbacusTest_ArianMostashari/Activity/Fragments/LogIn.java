package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.ABase.BaseActivity;
import com.example.AbacusTest_ArianMostashari.ABase.BaseFragment;
import com.example.AbacusTest_ArianMostashari.R;



public class LogIn extends BaseFragment {


  EditText edtEmail;
  EditText  edtPass;
  Button btnEnter;
  CheckBox cbRememberMe;
  TextView edtForgotPass;
  TextView txtCreateNewAccount;
  ScrollView scrollbar;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_log_in, container, false);

    BaseActivity baseActivity = new BaseActivity();
    //baseActivity.editStatusBar();
    //baseActivity.actionbarSetUp("");
    setTheComponents(rootView);
    //baseActivity.preventAutoKeyboard();


    btnEnter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (edtEmail.getText().toString().equals("1") && edtPass.getText().toString().equals("1")){
          changeFragmentTo(new HomePage());
        }
      }
    });

    txtCreateNewAccount.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changeFragmentTo(new Register());
      }
    });


    return rootView;
  }

  private void setTheComponents(View root) {
    edtEmail = root.findViewById(R.id.edt_email);
    btnEnter = root.findViewById(R.id.btn_enter);
    cbRememberMe = root.findViewById(R.id.cb_rememberMe);
    edtForgotPass = root.findViewById(R.id.txt_forgotPassword);
    scrollbar = root.findViewById(R.id.scrollBar);
    txtCreateNewAccount = root.findViewById(R.id.txt_createAccount);
  }
}