package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaction {

  //made private final as once a trasnaction is added, we do not support any edit features
  private final double amount;
  private final String category;
  private final String timestamp;

  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  public double getAmount() {
    return amount; //these are pass by copy so we can just directly return here
  }

  public String getCategory() {
    return category; //these are pass by copy so we can just directly return here
  }
  
  public String getTimestamp() {
    return timestamp; //these are pass by copy so we can just directly return here
  }

  // removed setter functions


  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}