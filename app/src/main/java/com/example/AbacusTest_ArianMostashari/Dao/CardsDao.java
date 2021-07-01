package com.example.AbacusTest_ArianMostashari.Dao;

import com.example.AbacusTest_ArianMostashari.Model.Cards;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;


@androidx.room.Dao
public interface CardsDao {

  @Query("select * FROM cards_database")
  LiveData<List<Cards>> getAllCards();
  //List<Cards> getAllCards();

  @Insert
  void addToCards (Cards...cards);

}
