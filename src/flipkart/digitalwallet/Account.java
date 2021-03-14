package flipkart.digitalwallet;

import java.util.Random;

public class Account {
    private Wallet wallet;
    private String accountId;
    private String name;
    private long createdAt;
    final Random random = new Random();

    public Account(String name, Double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.wallet = new Wallet(amount);
        this.accountId = generateId(name);
        this.name = name;
        createdAt = System.currentTimeMillis();
    }

    public long getCreatedAt() {
        return createdAt;
    }

    private String generateId(String name) {
        return createdAt + name.substring(0, 1);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String getAccountId() {
        return accountId;
    }
}
