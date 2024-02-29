public class Tea extends Beverage {
    private boolean hasMilk;

    public Tea(String nama, String size, boolean isCold) {                      /* Constructor template               */
        super(nama, size, isCold);
        calculatePrice();
    }

    @Override
    public void calculatePrice() {                                              /* Overriding calculate method        */
        int result = 0;

        switch (getSize().toUpperCase()) {
            case "TALL" -> result += 15_000;
            case "GRANDE" -> result += 20_000;
            case "VENTI" -> result += 25_000;
        }

        if (hasMilk) result += 7000;

        setPrice(result);
    }

    public void addMilk() {                                                     /* Add milk                           */
        hasMilk = true;
        calculatePrice();
    }

    @Override
    public String toString() {                                                  /* Override toString                  */
        String res = super.toString();

        if (hasMilk) {
          res += " with Milk";
        }

        res += " Rp. " + this.getPrice() + ",-";

        return res;
    }
}