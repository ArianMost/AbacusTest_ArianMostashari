package com.example.AbacusTest_ArianMostashari.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_database")
public class CartDb {

  @PrimaryKey(autoGenerate = true)
  public int cartId;

  @ColumnInfo(name = "customer_email")
  public String customerEmail;

  @ColumnInfo(name = "card_id")
  public String cardId;

  @ColumnInfo(name = "card_name")
  public String cardName;

  @ColumnInfo(name = "card_image")
  public String cardImage;

  @ColumnInfo(name = "card_info")
  public String cardInfo;
}
