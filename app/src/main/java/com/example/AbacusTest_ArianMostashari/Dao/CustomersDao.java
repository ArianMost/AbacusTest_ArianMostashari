package com.example.AbacusTest_ArianMostashari.Dao;

import com.example.AbacusTest_ArianMostashari.Model.Customers;

import java.util.List;

import androidx.room.Insert;
import androidx.room.Query;

@androidx.room.Dao
public interface CustomersDao {

  @Query("select * FROM customers_database")
  List<Customers> getAllCustomers();

  @Insert
  void addToCustomers (Customers...customers);
}