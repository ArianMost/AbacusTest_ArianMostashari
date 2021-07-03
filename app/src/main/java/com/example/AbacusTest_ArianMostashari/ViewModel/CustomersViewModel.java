package com.example.AbacusTest_ArianMostashari.ViewModel;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Model.Customers;
import com.example.AbacusTest_ArianMostashari.Repository.CustomersRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;

public class CustomersViewModel extends AndroidViewModel {

  public CustomersRepository repository;
  public List<Customers> getAllCustomers;

  public CustomersViewModel(Application application) {
    super(application);

    repository = new CustomersRepository(application);
    getAllCustomers = repository.getAllCustomers;
  }

  public void addCustomers(Customers customers){
    repository.addCustomers(customers);
  }

  public Customers getCustomerById(String email){
    return repository.getCustomerById(email);
  }
}