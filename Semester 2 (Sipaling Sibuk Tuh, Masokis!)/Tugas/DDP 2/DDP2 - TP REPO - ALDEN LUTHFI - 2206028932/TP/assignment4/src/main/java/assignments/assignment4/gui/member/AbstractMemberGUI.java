package assignments.assignment4.gui.member;

import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AbstractMemberGUI extends JPanel implements Loginable {   /* member ui abstraction              */
    private JLabel welcomeLabel;
    private JLabel loggedInAsLabel;
    protected Member loggedInMember;
    private final SystemCLI systemCLI;

    public AbstractMemberGUI(SystemCLI systemCLI) {                             /* not for creating an instance       */
        super(new BorderLayout());
        this.systemCLI = systemCLI;

        welcomeLabel = new JLabel("", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        loggedInAsLabel = new JLabel("", SwingConstants.CENTER);
        add(loggedInAsLabel, BorderLayout.SOUTH);

        JPanel buttonsPanel = initializeButtons();
        add(buttonsPanel, BorderLayout.CENTER);
    }

    protected JPanel initializeButtons() {                                      /* creating and assigning buttons     */
        JButton[] buttons = createButtons();
        ActionListener[] listeners = createActionListeners();

        if (buttons.length != listeners.length) {
            throw new IllegalStateException(
                "Number of buttons and listeners must be equal."
            );
        }

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            button.addActionListener(listeners[i]);
            buttonsPanel.add(button, gbc);
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> MainFrame.getInstance().logout());
        buttonsPanel.add(logoutButton, gbc);
        return buttonsPanel;
    }

    public boolean login(String id, String password) {                          /* validate user login credentials    */
        loggedInMember = systemCLI.login(id, password);

        if (loggedInMember != null) {
            welcomeLabel.setText("Welcome! %s"
                .formatted(loggedInMember.getNama())
            );

            loggedInAsLabel.setText("Logged in as %s"
                .formatted(loggedInMember.getId())
            );
        }

        return (loggedInMember == null) ? false : true;
    }

    public void logout() {
        loggedInMember = null;
    }

    protected abstract JButton[] createButtons();                               /* Implemented in its subclasses      */
    protected abstract ActionListener[] createActionListeners();
}
