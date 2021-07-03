package com.example.AbacusTest_ArianMostashari.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cards_database")
public class Cards {

  @PrimaryKey(autoGenerate = true)
  public int cardId;

  public int customerId;

  @ColumnInfo(name = "card_name")
  public String cardName;

  @ColumnInfo(name = "card_image")
  public int cardImage;

  @ColumnInfo(name = "card_info")
  public String cardInfo;

  @ColumnInfo(name = "card_price")
  public String cardPrice;
}
