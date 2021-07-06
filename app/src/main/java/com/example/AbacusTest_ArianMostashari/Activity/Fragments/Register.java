package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AbacusTest_ArianMostashari.ABase.BaseFragment;
import com.example.AbacusTest_ArianMostashari.Model.Customers;
import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.ViewModel.CustomersViewModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.lifecycle.ViewModelProviders;


public class Register extends BaseFragment {

  TextView txtOkPass;
  EditText edtName;
  EditText edtSurname;
  EditText edtPhone;
  EditText edtEmail;
  EditText edtAddress;
  EditText  edtPass;
  EditText  edtPassRepeat;
  ImageView imgOkPass;
  ImageView imgOkPassRepeat;
  Button btnRegister;
  ScrollView scrollbar;
  Boolean passIsOkay =false;
  Boolean passRepeatIsOkay =false;
  Boolean emailIsOkay =false;
  Handler handler;
  String customerName, customerSurname, customerEmail, customerPhone, customerAddress, customerPass;
  CustomersViewModel customersViewModel;
  String FILE_EMAIL = "Email.txt";


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_register, container, false);

    setTheComponents(rootView);
    customersViewModel = ViewModelProviders.of(getActivity()).get(CustomersViewModel.class);

    //run these functions every 200millis
      handler = new Handler();
      Thread thread = new Thread(() -> {
        while (btnRegister.getText().equals("Register")) {
          try {
            handler.post(() -> {
              emailIsOkay = checkEmailValidity(edtEmail.getText().toString());
              passIsOkay = checkPassValidity(edtPass.getText().toString());
              passRepeatIsOkay = checkPassRepeatValidity(edtPassRepeat.getText().toString(), edtPass.getText().toString());
            });
            Thread.sleep(200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
      thread.start();

      //do not accept empty fields
    btnRegister.setOnClickListener(v -> {
      if (edtName.getText().length() > 0 && edtSurname.getText().length() > 0 && edtPhone.getText().length() > 0
        && edtEmail.getText().length() > 0 && edtAddress.getText().length() > 0 && edtPass.getText().length() > 0
        && edtPassRepeat.getText().length() > 0){
        if (passIsOkay && passRepeatIsOkay && emailIsOkay){
          //save customers data
          customerName = edtName.getText().toString();
          customerSurname = edtSurname.getText().toString();
          customerEmail = edtEmail.getText().toString();
          customerPhone = edtPhone.getText().toString();
          customerAddress = edtAddress.getText().toString();
          customerPass = edtPass.getText().toString();
          creatCustomer(customerName, customerSurname, customerEmail, customerPhone, customerAddress, customerPass);
          saveEmail(edtEmail.getText().toString());
          Toast.makeText(getContext(), "Your account has been successfully created.", Toast.LENGTH_SHORT).show();
          changeFragmentTo(new HomePage());
        }
        else {
          Toast.makeText(getContext(), "Please check your email or password", Toast.LENGTH_SHORT).show();
        }
      }else
      {
        Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
      }
    });


    return rootView;
  }



  private boolean checkEmailValidity(String email) {
    if (email == null) {
      return false;
    } else {
      return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
  }

  private boolean checkPassValidity(String pass) {
                if (pass.length()>5) {
                  imgOkPass.setVisibility(View.VISIBLE);
                  txtOkPass.setVisibility(View.GONE);
                  return true;
                } else {
                  imgOkPass.setVisibility(View.GONE);
                  txtOkPass.setVisibility(View.VISIBLE);
                  return false;
                }
  }

  public boolean checkPassRepeatValidity(String passRepeat, String pass) {
                if (passRepeat.equals(pass) && passRepeat.length() > 0) {
                  imgOkPassRepeat.setVisibility(View.VISIBLE);
                  return true;
                } else {
                  imgOkPassRepeat.setVisibility(View.GONE);
                  return false;
                }
  }

  private void creatCustomer(String customerName, String customerSurname, String customerEmail, String customerPhone, String customerAddress, String customerPass) {
    Customers customers = new Customers();
    customers.customerName = customerName;
    customers.customerSurname = customerSurname;
    customers.customerEmail = customerEmail;
    customers.customerPhoneNumber = customerPhone;
    customers.customerAddress = customerAddress;
    customers.customerPassword = customerPass;

    customersViewModel.addCustomers(customers);
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
    imgOkPassRepeat = root.findViewById(R.id.img_okPassRepeat);
    scrollbar = root.findViewById(R.id.scrollBar);
    txtOkPass = root.findViewById(R.id.txt_okPass);
  }


  private void saveEmail(String email) {
    FileOutputStream fos = null;
    try {
      fos = getActivity().openFileOutput(FILE_EMAIL, Context.MODE_PRIVATE);
      fos.write(email.getBytes());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if (fos != null){
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}