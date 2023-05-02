package card;

import item.Item;

public class ElectronicCard extends Card implements Topupable{

    public ElectronicCard(String companyName, double balance) {
        super(companyName, balance, "Electronic");
    }

    @Override
    public void pay(Item item) {                                                /* Bayar tanpa diskon                 */
        setBalance(getBalance() - item.getPrice());
    }

    @Override
    public void topup(double val) {                                             /* Topup saldo                         */
        setBalance(getBalance() + val);
    }
}
