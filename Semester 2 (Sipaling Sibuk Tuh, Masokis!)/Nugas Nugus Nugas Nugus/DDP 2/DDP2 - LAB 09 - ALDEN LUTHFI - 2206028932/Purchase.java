import java.awt.Dimension;                                                      /* Imports needed                     */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Purchase extends JFrame {

    private ProgramState state;
    private JPanel mainPanel = new JPanel();
    private JTextField amountEntry;
    private int grandTotal;

    public Purchase (String title, ProgramState state) {
        super(title);                                                           /* creating the window                */
        this.state = state;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        add(mainPanel);                                                         /* placing the main panel             */

        setEntryPanel();
        setButtonPanel();

        setVisible(true);                                                       /* showing the window                 */
        pack();                                                                 /* packing                            */
        setLocationRelativeTo(null);                                            /* centering the window               */
    }

    private void setEntryPanel() {
        JPanel entryPanel = new JPanel(new GridLayout(4, 2));                   /* creating a panel for the entry     */
        entryPanel.setPreferredSize(new Dimension(400, 150));

        Vector<String> items = new Vector<String>();                            /* initializing a vector              */

        for (String key: state.itemList.keySet()) items.add(key);               /* putting the items in the vector    */

        amountEntry = new JTextField();                                         /* creating a textfield for the panel */
        amountEntry.setText("0");

        JComboBox<String> chosen = new JComboBox<String>(items);                /* creating a combobox for the panel  */

        int itemPrice = state.itemList.get(chosen.getSelectedItem());
        int itemAmount = Integer.parseInt(amountEntry.getText());
        String priceText = "Rp.%d,0".formatted(itemPrice);
        String totalPriceText = "Rp.%d,0".formatted(itemAmount * itemPrice);

        grandTotal = itemAmount * itemPrice;

        JLabel product = new JLabel("Product:");                                /* creating the labels                */
        JLabel quantity = new JLabel("Quantity:");
        JLabel price = new JLabel("Price:");
        JLabel totalPrice = new JLabel("Total Price:");
        JLabel itemPriceLabel = new JLabel(priceText);
        JLabel totalPriceLabel = new JLabel(totalPriceText);

        ActionListener listener = new ActionListener() {                        /* abstracting the listener           */
            @Override
            public void actionPerformed(ActionEvent e) {

                int price = state.itemList.get(chosen.getSelectedItem());
                int amount;

                try {
                    amount = Integer.parseInt(amountEntry.getText());
                } catch (NumberFormatException ex) {
                    amount = 0;
                }

                String priceText = "Rp.%d,0".formatted(price);
                String totalPrice = "Rp.%d,0".formatted(amount * price);

                itemPriceLabel.setText(priceText);
                totalPriceLabel.setText(totalPrice);

                grandTotal = amount * price;
            }
        };

        chosen.addActionListener(listener);                                     /* assigning actions to the widgets   */
        Timer timer = new Timer(100, listener);
        timer.start();

        entryPanel.add(product);                                                /* placing the widgets                */
        entryPanel.add(chosen);
        entryPanel.add(quantity);
        entryPanel.add(amountEntry);
        entryPanel.add(price);
        entryPanel.add(itemPriceLabel);
        entryPanel.add(totalPrice);
        entryPanel.add(totalPriceLabel);

        mainPanel.add(entryPanel);
    }

    private void setButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));                  /* creating a panel for a button      */
        buttonPanel.setPreferredSize(new Dimension(400, 30));

        JButton purchase = new JButton("Submit");                               /* creating a button for the panel    */

        purchase.addActionListener(                                             /* assigning an action for the button */
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    validateAmount();
                }
            }
        );

        buttonPanel.add(purchase);                                              /* placing the button                 */
        mainPanel.add(buttonPanel);                                             /* placing the panel                  */
    }

    private void messageDialog(String message, int messageType) {               /* shows message dialog               */
        JOptionPane.showMessageDialog(
            state.purchase,
            message,
            "Info",
            messageType
        );
    }

    private void validateAmount() {                                             /* validate the amount entry          */
        try {
            int amount = Integer.parseInt(amountEntry.getText());
            if (state.balance < grandTotal) {                                   /* if not enough money                */
                messageDialog("Maaf, Uang Anda tidak cukup!", 0);
            } else if (amount <= 0){                                            /* invalid amount                     */
                throw new NumberFormatException();
            } else {
                state.balance -= grandTotal;                                    /* substracting from current balance  */

                String infoText = "Berhasil! Kembalian Anda sebesar Rp.%d,0"
                    .formatted(state.balance);
                String labelText = "Total money: Rp.%d,0"
                    .formatted(state.balance);

                messageDialog(infoText, 1);

                state.vendingMachine.changeLabel(labelText);

                dispose();
            }
        } catch (NumberFormatException ex) {
            messageDialog("Jumlah barang yang Anda masukkan tidak valid!", 0);
        }
    }
}
