package com.example.AbacusTest_ArianMostashari.ViewModel;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Model.Cards;
import com.example.AbacusTest_ArianMostashari.Repository.CardsRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;

public class CardsViewModel extends AndroidViewModel {

  public CardsRepository repository;
  public List<Cards> getAllCards;

  public CardsViewModel(Application application) {
    super(application);

    repository = new CardsRepository(application);
    getAllCards = repository.getAllCards;
  }

  public void addCards(Cards cards){
    repository.addCards(cards);
  }

  public List<Cards> getCardsByUserId(int id){
    return repository.getCardsByUserId(id);
  }
}
