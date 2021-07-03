package com.example.AbacusTest_ArianMostashari.Database.relations;


import com.example.AbacusTest_ArianMostashari.Model.Cards;
import com.example.AbacusTest_ArianMostashari.Model.Customers;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class CustomerWithCards {
  @Embedded
  public Customers customers;
  @Relation(
    parentColumn = "customerId",
    entityColumn = "customerId"
  )
  public List<Cards>  cards;

}
