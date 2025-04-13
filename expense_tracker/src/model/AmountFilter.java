package model;

import java.util.ArrayList;
import java.util.List;

public class AmountFilter implements TransactionFilter {
    private final double maxAmount;

    public AmountFilter(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() <= maxAmount) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
}