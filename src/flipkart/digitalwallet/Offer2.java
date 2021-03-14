package flipkart.digitalwallet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offer2 implements Offer {
    private final int COUNT = 3;
    static Map<Integer, Double> rewardMap = new HashMap<>();

    static {
        rewardMap.put(0, 10.0);
        rewardMap.put(1, 5.0);
        rewardMap.put(2, 2.0);
    }

    @Override
    public void apply(List<Wallet> wallet) {
        int i = 0;
        while (!wallet.isEmpty() && i < COUNT) {
            Wallet w = wallet.get(i);
            w.addAmount(rewardMap.get(i), TransactionType.OFFER);
            i++;
        }
    }
}
