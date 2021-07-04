package com.example.AbacusTest_ArianMostashari.Dao;

import com.example.AbacusTest_ArianMostashari.Model.Customers;

import java.util.List;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@androidx.room.Dao
public interface CustomersDao {

  @Query("select * FROM customers_database")
  List<Customers> getAllCustomers();

  @Query("select * FROM customers_database WHERE customer_email = :email")
  Customers getCustomerByEmail(String email);

  @Insert
  void addToCustomers (Customers... customers);

  @Update
  void updateCustomer (Customers customers);
}