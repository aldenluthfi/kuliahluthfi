import java.util.HashMap;

public class ProgramState {                                                     /* Stores the current program state   */
    protected int balance;                                                      /* the current balance of the user    */
    protected HashMap<String, Integer> itemList;                                /* items in the vending machine       */

    protected VendingMachine vendingMachine;                                    /* main window                        */
    protected Purchase purchase;                                                /* purchase item window               */
    protected MoneyInput moneyInput;                                            /* money input window                 */

    public ProgramState() {

        itemList = new HashMap<String, Integer>();                              /* initializing the hashmap           */

        itemList.put("Akua", 5000);                                             /* adding the items                   */
        itemList.put("Fruti Apel", 8000);
        itemList.put("Palpi Jeruk", 7500);
        itemList.put("Neskafe Latte", 11000);
        itemList.put("Koka Kola", 9500);

        vendingMachine = new VendingMachine("Vending Machine", this);           /* creating the main window           */
    }

    public static void main(String[] args) {                                    /* main driver                        */
        new ProgramState();
    }
}
