import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {

    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();

      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);

      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    view.getFilterCategoryBtn().addActionListener(e -> {
      String category = view.getCategoryFilterField();
      System.out.println(category);
      boolean filtered = controller.applyFilter("category", category);
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "Invalid category entered");
        view.toFront();
      }
    });

    view.getFilterAmountBtn().addActionListener(e -> {
      double maxAmount = view.getAmountFilterField();
      System.out.println(maxAmount);
      boolean filtered = controller.applyFilter("amount", String.valueOf(maxAmount));
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "Invalid amount entered");
        view.toFront();
      }
    });

  }

}