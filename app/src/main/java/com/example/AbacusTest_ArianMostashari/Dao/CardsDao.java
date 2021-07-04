package com.example.AbacusTest_ArianMostashari.Dao;

import com.example.AbacusTest_ArianMostashari.Model.Cards;

import java.util.List;

import androidx.room.Insert;
import androidx.room.Query;


@androidx.room.Dao
public interface CardsDao {

  @Query("select * FROM cards_database")
  List<Cards> getAllCards();
  //List<Cards> getAllCards();

  @Query("select * FROM cards_database WHERE customerId = :id")
  List<Cards> getCardsByUserId(int id);

  @Insert
  void addToCards (Cards...cards);

  @Query("DELETE FROM cards_database WHERE cardId = :id")
  void deleteCard(int id);

  @Query("DELETE FROM cards_database")
  void deleteAllCards();
}
