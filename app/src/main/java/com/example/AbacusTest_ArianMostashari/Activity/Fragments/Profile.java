package com.example.AbacusTest_ArianMostashari.Activity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.AbacusTest_ArianMostashari.R;
import com.example.AbacusTest_ArianMostashari.ViewModel.CustomersViewModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class Profile extends Fragment {



TextView txtName;
TextView txtPhone;
TextView txtEmail;
TextView txtAddress;
String FILE_EMAIL = "Email.txt";
String customerEmail = "";
CustomersViewModel customersViewModel;
String name = "";
String surName = "";
String phoneNumber = "";
String email = "";
String address = "";


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_profile, container, false);


    setComponents(rootView);
    customersViewModel = ViewModelProviders.of(getActivity()).get(CustomersViewModel.class);
    readEmail(FILE_EMAIL);
    if (customerEmail.isEmpty()){
      name = "Name";
      surName = "";
      phoneNumber = "Phone number";
      email = "";
      address = "";
    }else {
      name = customersViewModel.getCustomerById(customerEmail).customerName;
      surName = customersViewModel.getCustomerById(customerEmail).customerSurname;
      phoneNumber = customersViewModel.getCustomerById(customerEmail).customerPhoneNumber;
      email = customersViewModel.getCustomerById(customerEmail).customerEmail;
      address = customersViewModel.getCustomerById(customerEmail).customerAddress;
    }

    txtName.setText(name + " " + surName);
    txtPhone.setText(phoneNumber);
    txtEmail.setText(email);
    txtAddress.setText(address);



    return rootView;
  }

  private void setComponents(View root) {
    txtName = root.findViewById(R.id.txt_name);
    txtPhone = root.findViewById(R.id.txt_phone);
    txtEmail = root.findViewById(R.id.txt_email);
    txtAddress = root.findViewById(R.id.txt_address);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  //creating Edit option on top pf the screen
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    // TODO Add your menu entries here
    inflater.inflate(R.menu.edit, menu);
    super.onCreateOptionsMenu(menu, inflater);
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