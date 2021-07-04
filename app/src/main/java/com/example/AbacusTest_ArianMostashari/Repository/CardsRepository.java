package com.example.AbacusTest_ArianMostashari.Repository;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Dao.CardsDao;
import com.example.AbacusTest_ArianMostashari.Database.CardsDatabase;
import com.example.AbacusTest_ArianMostashari.Model.Cards;

import java.util.List;

public class CardsRepository {

  public CardsDao cardsDao;

  public List<Cards> getAllCards;

  public CardsRepository(Application application){
    CardsDatabase database = CardsDatabase.getDatabaseInstance(application);
    cardsDao = database.cardsDao();
    getAllCards = cardsDao.getAllCards();
  }

  public void addCards(Cards cards){
    cardsDao.addToCards(cards);
  }

  public List<Cards> getCardsByUserId(int id){
    return cardsDao.getCardsByUserId(id);
  }

  public void deleteCard(int id){
    cardsDao.deleteCard(id);
  }

  public void deleteAllCards(){
    cardsDao.deleteAllCards();
  }
}