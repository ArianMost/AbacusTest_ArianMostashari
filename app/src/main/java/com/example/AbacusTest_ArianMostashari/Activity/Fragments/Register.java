package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.AbacusTest_ArianMostashari.ABase.BaseActivity;
import com.example.AbacusTest_ArianMostashari.ABase.BaseFragment;
import com.example.AbacusTest_ArianMostashari.R;


public class Register extends BaseFragment {

  EditText edtName;
  EditText edtSurname;
  EditText edtPhone;
  EditText edtEmail;
  EditText edtAddress;
  EditText  edtPass;
  EditText  edtPassRepeat;
  ImageView imgOkPass;
  Button btnRegister;
  ScrollView scrollbar;
  Boolean emailIsOkay =false;
  Boolean emailRepeatIsOkay =false;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_register, container, false);

    BaseActivity baseActivity = new BaseActivity();
    //baseActivity.editStatusBar();
    //baseActivity.actionbarSetUp("");
    setTheComponents(rootView);
    //baseActivity.preventAutoKeyboard();


    btnRegister.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (emailIsOkay && emailRepeatIsOkay){
          changeFragmentTo(new HomePage());
        }
      }
    });


    return rootView;
  }

  private void setTheComponents(View root) {
    edtName = root.findViewById(R.id.edt_name);
    edtSurname = root.findViewById(R.id.edt_surname);
    edtPhone = root.findViewById(R.id.edt_phone);
    edtEmail = root.findViewById(R.id.edt_email);
    edtAddress = root.findViewById(R.id.edt_address);
    edtPass = root.findViewById(R.id.edt_pass);
    edtPassRepeat = root.findViewById(R.id.edt_passRepeat);
    btnRegister = root.findViewById(R.id.btn_register);
    imgOkPass = root.findViewById(R.id.img_okPass);
    scrollbar = root.findViewById(R.id.scrollBar);
  }
}