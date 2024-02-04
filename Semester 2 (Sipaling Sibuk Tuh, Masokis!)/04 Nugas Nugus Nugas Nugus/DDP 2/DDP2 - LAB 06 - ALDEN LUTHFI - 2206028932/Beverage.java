public abstract class Beverage {                                                /* abstract because no instantiation  */
    private String name;                                                        /* gadi ganti dari template           */
    private String size;
    private boolean isCold;
    private int price;
    abstract public void calculatePrice();                                      /* Abstract method to be overriden    */

    public Beverage(String name, String size, boolean isCold) {                 /* Constructor dari template juga     */
        this.name = name;
        this.size = size;
        this.isCold = isCold;
    }

    public String getPrice() {                                                  /* Price getter                       */
        return Integer.toString(this.price);
    }

    public void setPrice(int value) {                                           /* Price getter                       */
        this.price = value;
    }

    public String getName() {                                                   /* Some getters                       */
        return this.name;
    }

    public String getSize() {
        return this.size;
    }

    public String toString() {                                                  /* To string method                   */
        String output = "";

        if (isCold) {
            output += "COLD ";
        } else {
            output += "HOT ";
        }

        output += this.size + " " + this.name;
        return output;
    }
}