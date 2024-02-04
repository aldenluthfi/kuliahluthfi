import java.awt.GridLayout;                                                     /* Imports needed                     */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class MoneyInput extends JFrame {

    private ProgramState state;                                                 /* to access the program state        */

    private JPanel mainPanel = new JPanel();                                    /* main panel for the widgets         */
    private JTextField moneyEntry;                                              /* this is abstracted to be accessed  */

    public MoneyInput(String title, ProgramState state) {
        super(title);                                                           /* creating the window                */
        this.state = state;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        add(mainPanel);                                                         /* placing the main panel             */

        setLabelPanel();
        setEntryPanel();
        setButtonPanel();

        setVisible(true);                                                       /* showing the window                 */
        pack();                                                                 /* packing                            */
        setLocationRelativeTo(null);                                            /* centering the window               */
    }

    private void setLabelPanel() {
        JPanel labelPanel = new JPanel();                                       /* creating a panel for the label     */
        labelPanel.setBounds(0, 0, 400, 20);

        JLabel moneyLabel = new JLabel("Enter the amount of money:");           /* creating a label for the panel     */

        moneyLabel.setHorizontalAlignment(JLabel.LEFT);                         /* aligning  the label                */
        moneyLabel.setPreferredSize(labelPanel.getSize());

        labelPanel.add(moneyLabel);                                             /* placing the label                  */
        mainPanel.add(labelPanel);                                              /* placing the panel                  */
    }

    private void setEntryPanel() {
        JPanel entryPanel = new JPanel(new GridLayout(1, 1));                   /* creating a panel for the textfield */
        entryPanel.setBounds(0, 0, 400, 50);

        moneyEntry = new JTextField();                                          /* creating a textfield for the panel */
        moneyEntry.setPreferredSize(entryPanel.getSize());

        entryPanel.add(moneyEntry);                                             /* placing the text field             */
        mainPanel.add(entryPanel);                                              /* placing the panel                  */
    }

    private void setButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));                  /* creating a panel for the buttons   */
        buttonPanel.setBounds(0, 0, 400, 20);

        JButton submit = new JButton("Submit");                                 /* creating a button for the panel    */

        submit.addActionListener(                                               /* assigning action to the button     */
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    state.balance += Integer.parseInt(moneyEntry.getText());    /* adding to the current balance      */

                    String labelText = "Total money: Rp.%d,0"
                        .formatted(state.balance);

                    state.vendingMachine.changeLabel(labelText);
                    dispose();
                }
            }
        );

        buttonPanel.add(submit);                                                /* placing the button                 */
        mainPanel.add(buttonPanel);                                             /* placing the panel                  */
    }
}
