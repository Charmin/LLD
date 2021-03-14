package flipkart.digitalwallet;


import java.util.*;
import java.util.stream.Collectors;

public class DigitalBank {
    private static DigitalBank bank;
    private static final int OFFER2_COUNT = 3;

    private final Map<String, Account> accounts = new HashMap<>();

    private final PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
        int size1 = Math.toIntExact(accounts.get(a).getWallet().getTransactions().stream().filter(f -> f.getTransactionType() != TransactionType.OFFER).count());
        int size2 = Math.toIntExact(accounts.get(b).getWallet().getTransactions().stream().filter(f -> f.getTransactionType() != TransactionType.OFFER).count());

        if (size1 == size2) {
            double balance1 = accounts.get(a).getWallet().getBalance();
            double balance2 = accounts.get(b).getWallet().getBalance();
            if (balance1 == balance2) {
                if (accounts.get(a).getCreatedAt() > accounts.get(b).getCreatedAt()) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return (int) (balance2 - balance1);
        }

        return size2 - size1;
    });

    private DigitalBank() {
    }

    public static DigitalBank getInstance() {
        if (bank == null) {
            bank = new DigitalBank();
        }
        return bank;
    }

    public void createWallet(String name, Double amount) {
        if (accounts.containsKey(name)) {
            throw new IllegalArgumentException("Account with same name already exists");
        }
        Account account = new Account(name, amount);
        accounts.put(name, account);
        pq.add(name);
    }

    public void transferMoney(String a, String b, Double amount) {
        if (!accounts.containsKey(a)) {
            throw new IllegalArgumentException("Account for " + a + " does not exist");
        }
        if (!accounts.containsKey(b)) {
            throw new IllegalArgumentException("Account for " + b + " does not exist");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount for transaction");
        }
        if (accounts.get(a).getWallet().getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient amount for transfer");
        }

        accounts.get(a).getWallet().debitAmount(amount);
        accounts.get(b).getWallet().addAmount(amount);

        applyOfferIfEligible(a, b);
    }

    public List<String> getStatement(String a) {
        if (accounts.get(a).getWallet().getTransactions().isEmpty()) {
            return new ArrayList<>();
        }
        return accounts.get(a).getWallet().getTransactions()
                .stream().map(Transaction::toString).collect(Collectors.toList());
    }

    public void applyOffer2() {
        Offer offer = new Offer2();
        List<Wallet> wallets = new ArrayList<>();
        int i = 0;
        while (!pq.isEmpty() && i < OFFER2_COUNT) {
            String acc = pq.remove();
            wallets.add(accounts.get(acc).getWallet());
            i++;
        }
        offer.apply(wallets);
    }

    public String getOverview(String a) {
        return "Current Balance for "+a+": " + accounts.get(a).getWallet().getBalance();
    }

    private void applyOfferIfEligible(String a, String b) {
        if (accounts.get(a).getWallet().getBalance().equals(accounts.get(b).getWallet().getBalance())) {
            List<Wallet> walletList = new ArrayList<>();
            walletList.add(accounts.get(a).getWallet());
            walletList.add(accounts.get(b).getWallet());
            Offer offer = new Offer1();
            offer.apply(walletList);
        }
    }
}
