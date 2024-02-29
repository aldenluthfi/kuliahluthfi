import java.awt.GridLayout;                                                     /* Imports needed                     */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class VendingMachine extends JFrame {

    private ProgramState state;                                                 /* to access the program state        */

    private JPanel mainPanel = new JPanel();                                    /* main panel for the widgets         */
    private JLabel moneyLabel;                                                  /* this is abstracted to be changed   */

    public VendingMachine (String title, ProgramState state) {                  /* creating and placing the main panel*/
        super(title);                                                           /* creating the window                */
        this.state = state;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        add(mainPanel);                                                         /* placing the main panel             */

        setLabelPanel();
        setButtonPanel();

        setVisible(true);                                                       /* showing the window                 */
        pack();                                                                 /* packing                            */
        setLocationRelativeTo(null);                                            /* centering the window               */
    }

    private void setLabelPanel() {
        JPanel labelPanel = new JPanel();                                       /* creating a panel for the label     */
        labelPanel.setBounds(0, 0, 400, 150);

        moneyLabel = new JLabel("Please select an option");                     /* creating a label for the panel     */

        moneyLabel.setHorizontalAlignment(JLabel.CENTER);
        moneyLabel.setVerticalAlignment(JLabel.CENTER);
        moneyLabel.setPreferredSize(labelPanel.getSize());                      /* centering the label                */

        labelPanel.add(moneyLabel);                                             /* placing the label                  */
        mainPanel.add(labelPanel);                                              /* placing the panel                  */
    }

    private void setButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));                  /* creating a panel for the label     */
        buttonPanel.setBounds(0, 0, 400, 20);

        JButton addMoney = new JButton("Add Money");                            /* creating the buttons for the panel */
        JButton beliItem = new JButton("Purchase Item");

        addMoney.addActionListener(                                             /* assigning button actions           */
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    state.moneyInput = new MoneyInput("Money Input", state);
                }
            }
        );

        beliItem.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    state.purchase = new Purchase("Purchase Product", state);
                }
            }
        );

        buttonPanel.add(addMoney);                                              /* placing the buttons to the panel   */
        buttonPanel.add(beliItem);
        mainPanel.add(buttonPanel);                                             /* placing the panel                  */
    }

    public void changeLabel(String text) {                                      /* changing the current balance label */
        moneyLabel.setText(text);
    }
}
