package com.example.AbacusTest_ArianMostashari.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myorders_database")
public class MyOrders {

  @PrimaryKey(autoGenerate = true)
  public int cartId;

  public int customerId;

  @ColumnInfo(name = "card_id")
  public int cardId;

  @ColumnInfo(name = "card_name")
  public String cardName;

  @ColumnInfo(name = "card_image")
  public int cardImage;

  @ColumnInfo(name = "card_info")
  public String cardInfo;

  @ColumnInfo(name = "card_price")
  public String cardPrice;
}
