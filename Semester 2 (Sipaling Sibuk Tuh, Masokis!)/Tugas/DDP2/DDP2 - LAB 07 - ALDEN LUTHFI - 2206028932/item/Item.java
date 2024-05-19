package item;

public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s - Rp %d", name, price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
