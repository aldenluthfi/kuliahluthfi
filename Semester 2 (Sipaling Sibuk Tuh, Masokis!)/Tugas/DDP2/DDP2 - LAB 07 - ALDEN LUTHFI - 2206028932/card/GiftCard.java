package card;

import item.Item;

public class GiftCard extends Card{

    public GiftCard(String companyName, double balance) {
        super(companyName, balance, "Gift");
    }

    @Override
    public void pay(Item item) {                                                /* Bayar dengan diskon                */
        setBalance(getBalance() - item.getPrice() * 0.9);
    }
}
