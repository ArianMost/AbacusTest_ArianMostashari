package com.example.AbacusTest_ArianMostashari.Repository;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Dao.CartDao;
import com.example.AbacusTest_ArianMostashari.Database.CardsDatabase;
import com.example.AbacusTest_ArianMostashari.Model.CartDb;

import java.util.List;

public class CartRepository {

  public CartDao cartDao;

  public List<CartDb> getAllCarts;

  public CartRepository(Application application){
    CardsDatabase database = CardsDatabase.getDatabaseInstance(application);
    cartDao = database.cartDao();
    getAllCarts = cartDao.getAllCarts();
  }

  public void addCarts(CartDb cartDb){
    cartDao.addToCart(cartDb);
  }
}