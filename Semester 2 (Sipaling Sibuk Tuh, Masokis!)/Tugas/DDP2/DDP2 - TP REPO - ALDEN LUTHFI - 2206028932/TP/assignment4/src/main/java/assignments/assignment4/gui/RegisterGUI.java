package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;                                    /* Imports project files              */
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RegisterGUI extends JPanel {                                       /* Page for registering user          */
    public static final String KEY = "REGISTER";                                /* Key to go to this page             */
    private JPanel mainPanel;                                                   /* Widgets                            */
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout());
        this.loginManager = loginManager;

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();
        add(mainPanel, BorderLayout.CENTER);
    }

    private void initGUI() {                                                    /* Creating and placing widgets       */
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        nameLabel = new JLabel("Masukkan nama Anda:");
        constraints.gridy = 0;
        constraints.weighty = 0.5;
        mainPanel.add(nameLabel, constraints);

        nameTextField = new JTextField(50);
        constraints.gridy = 1;
        constraints.weighty = 0;
        mainPanel.add(nameTextField, constraints);

        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        constraints.gridy = 2;
        constraints.weighty = 0.5;
        mainPanel.add(phoneLabel, constraints);

        phoneTextField = new JTextField(50);
        constraints.gridy = 3;
        constraints.weighty = 0;
        mainPanel.add(phoneTextField, constraints);

        passwordLabel = new JLabel("Masukkan password Anda:");
        constraints.gridy = 4;
        constraints.weighty = 0.5;
        mainPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(50);
        constraints.gridy = 5;
        constraints.weighty = 0;
        mainPanel.add(passwordField, constraints);

        registerButton = new JButton("Register");
        constraints.gridy = 6;
        constraints.weighty = 0.5;
        mainPanel.add(registerButton, constraints);

        backButton = new JButton("Kembali");
        constraints.gridy = 7;
        constraints.weighty = 0.5;
        mainPanel.add(backButton, constraints);

        registerButton.addActionListener(e -> handleRegister());                /* Adds listener                      */
        backButton.addActionListener(e -> handleBack());
    }

    private void handleBack() {                                                 /* going back to the home page        */
        resetFields();
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    private void handleRegister() {                                             /* if the user presses register       */
        String password = String.valueOf(passwordField.getPassword());

        if (nameTextField.getText().equals("")) {
            errorMessage("Nama tidak boleh kosong");
        } else if (!phoneTextField.getText().matches("[0-9]+")) {
            errorMessage("Nomor HP tidak valid!");
        } else if (password.equals("")) {
            errorMessage("Password tidak boleh kosong");
        } else {
            Member member = loginManager.register(
                nameTextField.getText(),
                phoneTextField.getText(),
                password
            );

            if (member != null) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Berhasil register member dengan ID: %s"
                        .formatted(member.getId()),
                        "Berhasil",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    handleBack();
            } else {
                errorMessage("User dengan ID yang sama sudah ada!");
            }
        }

        resetFields();
    }

    private void resetFields() {                                                /* Empty out the fields               */
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }

    private void errorMessage(String message) {                                 /* Abstracts error message            */
        JOptionPane.showMessageDialog(
            this,
            message,
            "Gagal",
            JOptionPane.WARNING_MESSAGE
        );
    }
}
