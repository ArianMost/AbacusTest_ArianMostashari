package com.example.AbacusTest_ArianMostashari.ViewModel;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Model.Cards;
import com.example.AbacusTest_ArianMostashari.Repository.CardsRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CardsViewModel extends AndroidViewModel {

  public CardsRepository repository;
  LiveData<List<Cards>> getAllCards;

  public CardsViewModel(Application application) {
    super(application);

    repository = new CardsRepository(application);
    getAllCards = repository.getAllCards;
  }

  void addCards(Cards cards){
    repository.addCards(cards);
  }
}
