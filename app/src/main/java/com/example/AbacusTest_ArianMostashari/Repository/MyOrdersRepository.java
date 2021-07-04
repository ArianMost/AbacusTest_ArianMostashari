package com.example.AbacusTest_ArianMostashari.Repository;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Dao.MyOrdersDao;
import com.example.AbacusTest_ArianMostashari.Database.CardsDatabase;
import com.example.AbacusTest_ArianMostashari.Model.MyOrders;

import java.util.List;

public class MyOrdersRepository {

  public MyOrdersDao myOrdersDao;

  public List<MyOrders> getAllOrders;

  public MyOrdersRepository(Application application){
    CardsDatabase database = CardsDatabase.getDatabaseInstance(application);
    myOrdersDao = database.myOrdersDao();
    getAllOrders = myOrdersDao.getAllOrders();
  }

  public void addOrders(MyOrders myOrders){
    myOrdersDao.addToOrders(myOrders);
  }
}