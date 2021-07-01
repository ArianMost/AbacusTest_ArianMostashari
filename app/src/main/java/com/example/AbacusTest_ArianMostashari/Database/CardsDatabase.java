package com.example.AbacusTest_ArianMostashari.Database;


import android.content.Context;

import com.example.AbacusTest_ArianMostashari.Dao.CardsDao;
import com.example.AbacusTest_ArianMostashari.Dao.CartDao;
import com.example.AbacusTest_ArianMostashari.Dao.CustomersDao;
import com.example.AbacusTest_ArianMostashari.Model.Cards;
import com.example.AbacusTest_ArianMostashari.Model.CartDb;
import com.example.AbacusTest_ArianMostashari.Model.Customers;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cards.class, CartDb.class, Customers.class}, version = 1)
public abstract class CardsDatabase extends RoomDatabase {

  public abstract CardsDao cardsDao();

  public abstract CustomersDao customersDao();

  public abstract CartDao cartDao();

  public static CardsDatabase INSTANCE;

  public static CardsDatabase getDatabaseInstance(Context context){
    if (INSTANCE == null){
      INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CardsDatabase.class, "cards_database").build();
    }
    return INSTANCE;
  }
}
