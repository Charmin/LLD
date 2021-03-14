package flipkart.digitalwallet;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private Double balance;
    private final List<Transaction> transactions;

    public Wallet(Double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Minimum account balance is 0.00001");
        }
        this.balance = amount;
        transactions = new ArrayList<>();
    }

    public Double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addAmount(Double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance += amount;
        transactions.add(new Transaction(TransactionType.CREDIT, amount));
    }

    public void addAmount(Double amount, TransactionType transactionType) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance += amount;
        transactions.add(new Transaction(transactionType, amount));
    }

    public void debitAmount(Double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
        transactions.add(new Transaction(TransactionType.DEBIT, amount));
    }
}
