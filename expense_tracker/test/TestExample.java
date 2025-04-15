
// package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ExpenseTrackerController;
import model.*;
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
        // assertTrue(false);
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
    public void testAddMultipleTransactions() {

        assertEquals(0, model.getTransactions().size());

        // assertEquals(0.0, getTotalCost()) is depricated, need to add delta parameter
        assertEquals(0.0, getTotalCost(), 0.01);

        controller.addTransaction(50, "food");
        controller.addTransaction(837.5, "travel");
        controller.addTransaction(79.99, "entertainment");
        controller.addTransaction(50, "bills");

        // expected sum is 1017.49
        assertEquals(1017.49, getTotalCost(), 0.01);

        assertEquals(4, model.getTransactions().size());
    }

    @Test
    public void testInvalidInputHandling() {
        assertEquals(0, model.getTransactions().size());
        assertEquals(0.0, getTotalCost(), 0.01);

        // negative number not allowed
        assertFalse(controller.addTransaction(-50.00, "food"));

        // size should still be 0 and total cost should still be zero
        assertEquals(0, model.getTransactions().size());
        assertEquals(0.0, getTotalCost(), 0.01);

        // bad category should also return false
        assertFalse(controller.addTransaction(50.00, "trumpets"));

        // size should still be 0 and total cost should still be zero
        assertEquals(0, model.getTransactions().size());
        assertEquals(0.0, getTotalCost(), 0.01);

    }

    @Test
    public void testFilterByMaxAmount() {
        assertEquals(0, model.getTransactions().size());
        assertEquals(0.0, getTotalCost(), 0.01);

        controller.addTransaction(50, "food");
        controller.addTransaction(837.5, "travel");
        controller.addTransaction(79.99, "entertainment");
        controller.addTransaction(50, "bills");

        AmountFilter test = new AmountFilter(100);
        List<Transaction> filteredTransactions = test.filter(model.getTransactions());

        assertEquals("food", filteredTransactions.get(0).getCategory());
        assertEquals(50, filteredTransactions.get(0).getAmount(), 0.01);

        assertEquals("entertainment", filteredTransactions.get(1).getCategory());
        assertEquals(79.99, filteredTransactions.get(1).getAmount(), 0.01);

        assertEquals("bills", filteredTransactions.get(2).getCategory());
        assertEquals(50, filteredTransactions.get(2).getAmount(), 0.01);

        assertEquals(3, filteredTransactions.size());

    }

}