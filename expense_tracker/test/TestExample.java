// package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
 
import java.util.List;

import org.junit.Before; 
import org.junit.Test;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel; 
import model.Transaction;
import view.ExpenseTrackerView;


public class TestExample {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private ExpenseTrackerController controller;

  @Before
  public void setup() {
    model = new ExpenseTrackerModel();
    view = new ExpenseTrackerView();
    controller = new ExpenseTrackerController(model, view);
  }

    public double getTotalCost() {
        double totalCost = 0.0;
        List<Transaction> allTransactions = model.getTransactions(); // Using the model's getTransactions method
        for (Transaction transaction : allTransactions) {
            totalCost += transaction.getAmount();
        }
        return totalCost;
    }
    


    @Test
    public void testAddTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(50.00, "food"));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Check the contents of the list
        assertEquals(50.00, getTotalCost(), 0.01);
    }


    @Test
    public void testRemoveTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "Groceries");
        model.addTransaction(addedTransaction);
    
        // Pre-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Perform the action: Remove the transaction
        model.removeTransaction(addedTransaction);
    
        // Post-condition: List of transactions is empty
        List<Transaction> transactions = model.getTransactions();
        assertEquals(0, transactions.size());
    
        // Check the total cost after removing the transaction
        double totalCost = getTotalCost();
        assertEquals(0.00, totalCost, 0.01);
    }

    @Test
public void testAddTransactionNew() {
    // Setup: Ensure list is empty
    assertEquals(0, model.getTransactions().size());

    // Execution: Add transaction
    assertTrue(controller.addTransaction(50.00, "food"));

    // Validation: Check transaction is added and total is updated
    assertEquals(1, model.getTransactions().size());
    assertEquals(50.00, getTotalCost(), 0.01);
}

@Test
public void testInvalidInputHandling() {
    // Setup: Initial size and total
    assertEquals(0, model.getTransactions().size());
    double beforeTotal = getTotalCost();

    // Execution: Try to add invalid transactions
    boolean result1 = controller.addTransaction(-100.0, "food");   // Invalid amount
    boolean result2 = controller.addTransaction(25.0, "");         // Invalid category

    // Validation: Ensure neither transaction was added
    assertFalse(result1);
    assertFalse(result2);
    assertEquals(0, model.getTransactions().size());
    assertEquals(beforeTotal, getTotalCost(), 0.01);
}

@Test
public void testFilterByAmount() {
    // Setup: Add multiple transactions
    controller.addTransaction(20.00, "food");
    controller.addTransaction(50.00, "transport");
    controller.addTransaction(20.00, "misc");

    // Execution: Filter by amount
    List<Transaction> filtered = model.filterByAmount(20.00);

    // Validation: Expect 2 transactions with amount 20.00
    assertEquals(2, filtered.size());
    for (Transaction t : filtered) {
        assertEquals(20.00, t.getAmount(), 0.01);
    }
}

@Test
public void testFilterByCategory() {
    // Setup: Add multiple transactions
    controller.addTransaction(10.00, "food");
    controller.addTransaction(40.00, "travel");
    controller.addTransaction(25.00, "food");

    // Execution: Filter by category
    List<Transaction> filtered = model.filterByCategory("food");

    // Validation: Expect 2 transactions in "food" category
    assertEquals(2, filtered.size());
    for (Transaction t : filtered) {
        assertEquals("food", t.getCategory());
    }
}

    
}
