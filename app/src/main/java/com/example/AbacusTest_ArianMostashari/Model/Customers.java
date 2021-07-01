package com.example.AbacusTest_ArianMostashari.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customers_database")
public class Customers {

  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "customer_name")
  public String customerName;

  @ColumnInfo(name = "customer_surname")
  public String customerSurname;

  @ColumnInfo(name = "customer_phoneNumber")
  public String customerPhoneNumber;

  @ColumnInfo(name = "customer_email")
  public String customerEmail;

  @ColumnInfo(name = "customer_password")
  public String customerPassword;

  @ColumnInfo(name = "customer_address")
  public String customerAddress;
}
