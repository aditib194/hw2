package controller;

import view.ExpenseTrackerView;

import java.util.List;

import model.TransactionFilter;
import model.AmountFilter;
import model.CategoryFilter;

import model.ExpenseTrackerModel;
import model.Transaction;

public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }

    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[] { t.getAmount(), t.getCategory(), t.getTimestamp() });
    refresh();
    return true;
  }

  // Other controller methods

  // the call to this function would look like applyFilter("amount", "100")
  public boolean applyFilter(String filterType, String filterValue) {
    List<Transaction> filteredTransactions = model.getTransactions();
    if (filterType.equals("amount")) {
      double maxAmount = Double.parseDouble(filterValue);
      if (!InputValidation.isValidAmount(maxAmount)) {
        return false;
      }
      TransactionFilter filter = new AmountFilter(maxAmount);
      filteredTransactions = filter.filter(model.getTransactions());
    } else if (filterType.equals("category")) {
      if (!InputValidation.isValidCategory(filterValue)) {
        return false;
      }
      TransactionFilter filter = new CategoryFilter(filterValue);
      filteredTransactions = filter.filter(model.getTransactions());
    }
    view.refreshTable(filteredTransactions);
    return true;
  }
}