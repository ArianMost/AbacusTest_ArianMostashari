package com.example.AbacusTest_ArianMostashari.RecyclerView;

public class CardModel {
  int [] picture;
  String name;
  String price;

  public CardModel(int[] picture, String name, String price) {
    this.picture = picture;
    this.name = name;
    this.price = price;
  }

  public int[] getPicture() {
    return picture;
  }

  public String getName() {
    return name;
  }

  public String getPrice() {
    return price;
  }

  public void setPicture(int[] picture) {
    this.picture = picture;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
