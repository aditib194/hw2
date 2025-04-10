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
    return amount;
  }

  public String getCategory() {
    return category;
  }
  
  public String getTimestamp() {
    return timestamp;
  }

  // removed setter functions

  
  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}