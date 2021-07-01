package com.example.AbacusTest_ArianMostashari.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cards_database")
public class Cards {

  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "cards_title")
  public String cardsTitle;

  @ColumnInfo (name = "cards_info")
  public String cardsInfo;

  @ColumnInfo (name = "cards_pic")
  public int cardspic;
}
