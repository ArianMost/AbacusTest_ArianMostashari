package com.example.AbacusTest_ArianMostashari.ViewModel;

import android.app.Application;

import com.example.AbacusTest_ArianMostashari.Model.MyOrders;
import com.example.AbacusTest_ArianMostashari.Repository.MyOrdersRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;

public class MyOrdersViewModel extends AndroidViewModel {

  public MyOrdersRepository repository;
  public List<MyOrders> getAllOrders;

  public MyOrdersViewModel(Application application) {
    super(application);

    repository = new MyOrdersRepository(application);
    getAllOrders = repository.getAllOrders;
  }

  public void addOrders(MyOrders myOrders){
    repository.addOrders(myOrders);
  }
}
