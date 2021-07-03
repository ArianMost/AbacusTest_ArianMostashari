package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.List;

import androidx.lifecycle.ViewModelProviders;


public class LogIn extends BaseFragment {


  EditText edtEmail;
  EditText  edtPass;
  Button btnEnter;
  CheckBox cbRememberMe;
  TextView edtForgotPass;
  TextView txtCreateNewAccount;
  ScrollView scrollbar;
  CustomersViewModel customersViewModel;
  String FILE_EMAIL = "Email.txt";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_log_in, container, false);

    customersViewModel = ViewModelProviders.of(getActivity()).get(CustomersViewModel.class);
    setTheComponents(rootView);
    List<Customers> customers = customersViewModel.getAllCustomers;


    btnEnter.setOnClickListener(v -> {
      for (Customers customer: customers) {
        Log.i("Test", "the email is: " + customer.customerEmail);
        Log.i("Test", "the pass is: " + customer.customerPassword);
        String email = customer.customerEmail;
        String pass = customer.customerPassword;
        if (edtEmail.getText().toString().equals(email) && edtPass.getText().toString().equals(pass)){
          saveEmail(email);
          changeFragmentTo(new HomePage());
        }else {
          Toast.makeText(getContext(), "Wrong email or password!", Toast.LENGTH_SHORT).show();
        }
      }
    });

    txtCreateNewAccount.setOnClickListener(v -> changeFragmentTo(new Register()));


    return rootView;
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

  private void setTheComponents(View root) {
    edtEmail = root.findViewById(R.id.edt_email);
    btnEnter = root.findViewById(R.id.btn_enter);
    cbRememberMe = root.findViewById(R.id.cb_rememberMe);
    edtForgotPass = root.findViewById(R.id.txt_forgotPassword);
    scrollbar = root.findViewById(R.id.scrollBar);
    edtPass = root.findViewById(R.id.edt_pass);
    txtCreateNewAccount = root.findViewById(R.id.txt_createAccount);
  }
}