package com.example.AbacusTest_ArianMostashari.Dao;

import com.example.AbacusTest_ArianMostashari.Model.MyOrders;

import java.util.List;

import androidx.room.Insert;
import androidx.room.Query;


@androidx.room.Dao
public interface MyOrdersDao {

  @Query("select * FROM myorders_database")
  List<MyOrders> getAllOrders();

  @Insert
  void addToOrders (MyOrders...myOrders);
}