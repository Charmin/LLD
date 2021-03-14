package flipkart.digitalwallet;

import java.util.Arrays;

public class Transaction {
    private final TransactionType transactionType;
    private final Double amount;

    public Transaction(TransactionType transactionType, Double amount) {
        this.amount = amount;
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", amount=" + amount +
                '}';
    }

    public TransactionType getTransactionType() {
        char[] b = new char[10];
        Arrays.fill(b, 'f');
        return transactionType;
    }
}
