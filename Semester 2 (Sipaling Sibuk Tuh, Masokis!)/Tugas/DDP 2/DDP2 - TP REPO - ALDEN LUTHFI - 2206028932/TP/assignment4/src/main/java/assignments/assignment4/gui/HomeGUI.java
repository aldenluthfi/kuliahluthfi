package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;                                /* Imports project files              */
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";                                    /* the code to go to this page        */
    private JLabel titleLabel;                                                  /* widgets                            */
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){                                                           /* Creates the page                   */
        super(new BorderLayout());

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();
        add(mainPanel, BorderLayout.CENTER);
    }

    private void initGUI() {                                                    /* Creating and placing widgets       */

        GridBagConstraints constraints = new GridBagConstraints();

        titleLabel = new JLabel("Selamat Datang di CuciCuci System");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        constraints.gridy = 0;
        mainPanel.add(titleLabel, constraints);

        loginButton = new JButton("Login");
        constraints.gridy = 1;
        constraints.weighty = 1.0;
        mainPanel.add(loginButton, constraints);

        registerButton = new JButton("Register");
        constraints.gridy = 2;
        mainPanel.add(registerButton, constraints);

        toNextDayButton = new JButton("Next Day");
        constraints.gridy = 3;
        mainPanel.add(toNextDayButton, constraints);

        dateLabel = new JLabel("Hari ini: %s".formatted(
            NotaManager.fmt.format(NotaManager.cal.getTime()))
        );
        constraints.gridy = 4;
        constraints.weighty = 0;
        mainPanel.add(dateLabel, constraints);

        loginButton.addActionListener(e -> handleToLogin());                    /* Adds the listener                  */
        registerButton.addActionListener(e -> handleToRegister());
        toNextDayButton.addActionListener(e -> handleNextDay());
    }

    private static void handleToRegister() {                                    /* Opens the register page            */
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
    }

    private static void handleToLogin() {                                       /* Opens the login page               */
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    private void handleNextDay() {                                              /* Goes to the next day               */
        toNextDay();

        dateLabel.setText("Hari ini: %s".formatted(
            NotaManager.fmt.format(NotaManager.cal.getTime()))
        );

        JOptionPane.showMessageDialog(
                this,
                "Hari telah berganti...",
                "Zzz...",
                JOptionPane.INFORMATION_MESSAGE
            );
    }
}
