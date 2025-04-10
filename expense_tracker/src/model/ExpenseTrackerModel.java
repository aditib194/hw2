package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerModel {

  private List<Transaction> transactions; //made private

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  public List<Transaction> getTransactions() {
    return new ArrayList<>(transactions); //return copy so that main data is unchanged
}

}