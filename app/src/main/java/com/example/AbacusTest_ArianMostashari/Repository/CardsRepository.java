package com.example.AbacusTest_ArianMostashari.Repository;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Dao.CardsDao;
import com.example.AbacusTest_ArianMostashari.Database.CardsDatabase;
import com.example.AbacusTest_ArianMostashari.Model.Cards;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CardsRepository {

  public CardsDao cardsDao;

  public LiveData<List<Cards>> getAllCards;

  public CardsRepository(Application application){
    CardsDatabase database = CardsDatabase.getDatabaseInstance(application);
    cardsDao = database.cardsDao();
    getAllCards = cardsDao.getAllCards();
  }

  public void addCards(Cards cards){
    cardsDao.addToCards(cards);
  }
}