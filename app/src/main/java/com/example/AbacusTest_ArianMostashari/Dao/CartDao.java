package com.example.AbacusTest_ArianMostashari.Dao;

import com.example.AbacusTest_ArianMostashari.Model.CartDb;

import java.util.List;

import androidx.room.Insert;
import androidx.room.Query;


@androidx.room.Dao
public interface CartDao {

  @Query("select * FROM cart_database")
  List<CartDb> getAllCarts();

  @Insert
  void addToCart (CartDb...cartDbs);
}