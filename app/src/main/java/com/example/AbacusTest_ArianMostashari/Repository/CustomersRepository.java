package com.example.AbacusTest_ArianMostashari.Repository;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Dao.CustomersDao;
import com.example.AbacusTest_ArianMostashari.Database.CardsDatabase;
import com.example.AbacusTest_ArianMostashari.Model.Customers;

import java.util.List;

public class CustomersRepository {

  public CustomersDao customersDao;

  public List<Customers> getAllCustomers;

  public CustomersRepository(Application application){
    CardsDatabase database = CardsDatabase.getDatabaseInstance(application);
    customersDao = database.customersDao();
    getAllCustomers = customersDao.getAllCustomers();
  }

  public void addCustomers(Customers customers){
    customersDao.addToCustomers(customers);
  }

  public Customers getCustomerById(String email){
    return customersDao.getCustomerByEmail(email);

  }


}