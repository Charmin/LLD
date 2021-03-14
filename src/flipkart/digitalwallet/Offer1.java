package flipkart.digitalwallet;

import java.util.List;

public class Offer1 implements Offer {
    @Override
    public void apply(List<Wallet> wallet) {
         wallet.forEach(w -> w.addAmount(10.00, TransactionType.OFFER));
    }
}
