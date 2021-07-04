package com.example.AbacusTest_ArianMostashari.RecyclerView;

public class CardModel {
  int [] picture;
  String name;
  String price;
  String email;
  int value;
  int cardId;

  public CardModel(int[] picture, String name, String price, int value, String email, int cardId) {
    this.picture = picture;
    this.name = name;
    this.price = price;
    this.value = value;
    this.email = email;
    this.cardId = cardId;
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

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
