public class Coffee extends Beverage {
    private boolean hasWhipCream;

    public Coffee(String nama, String size, boolean isCold) {                   /* Constructor template               */
        super(nama, size, isCold);
        calculatePrice();
    }

    @Override
    public void calculatePrice() {                                              /* Overriding calculate method        */
        int result = 0;

        switch (getSize().toUpperCase()) {
            case "TALL" -> result += 20_000;
            case "GRANDE" -> result += 25_000;
            case "VENTI" -> result += 30_000;
        }

        if (hasWhipCream) result += 5000;

        setPrice(result);
    }

    public void addWhipCream() {                                                /* Add whip cream                     */
        hasWhipCream = true;
        calculatePrice();
    }

    public String toString() {                                                  /* To string method                   */
        String res = super.toString();

        if (hasWhipCream) {
            res += " with Whip Cream";
        }
        res += " Rp. " + super.getPrice() + ",-";

        return res;
    }
}