package card;

import item.Item;

public abstract class Card implements Comparable<Card> {
    private static int idCounter = 0;
    private double balance;
    private String companyName;
    private int id;
    private String type;

    protected Card(String companyName, double balance, String type) {
        this.companyName = companyName;
        this.balance = balance;
        this.type = type;
        id = idCounter++;
    }

    public abstract void pay(Item item);                                        /* abstract method            */

    @Override                                                                   /* < -> -1, > -> 1, = -> 0            */
    public int compareTo(Card o) {
        if (o.getBalance() == this.balance) return o.getId() < this.id ? -1 : 1;
        else return o.getBalance() < this.balance ? -1 : 1;
    }

    @Override
    public String toString() {
        return String.format("Card %s %s - id: %d, balance: %.2f",
            companyName, type, id, balance);
    }

    public double getBalance() {                                                /* Getter dan setter                  */
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
