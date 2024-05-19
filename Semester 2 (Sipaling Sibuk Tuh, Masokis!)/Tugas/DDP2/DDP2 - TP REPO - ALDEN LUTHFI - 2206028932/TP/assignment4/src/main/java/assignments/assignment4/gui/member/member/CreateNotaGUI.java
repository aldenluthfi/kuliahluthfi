package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;                                       /* Import project files               */
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {                                     /* Page to create nota                */
    public static final String KEY = "CREATE_NOTA";                             /* Key to access the page             */
    private JPanel mainPanel;                                                   /* Widgets                            */
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;                                         /* Calendar formatting                */
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;                              /* To get the logged in member        */

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {                     /* to create an instance              */
        super(new BorderLayout());

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();
        add(mainPanel, BorderLayout.CENTER);

        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;
    }

    private void initGUI() {
        GridBagConstraints constraints = new GridBagConstraints();              /* Creating and placing widgets       */
        constraints.fill = GridBagConstraints.BOTH;

        paketLabel = new JLabel("Paket Laundry:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(paketLabel, constraints);

        paketComboBox = new JComboBox<>(new String[] {
            "Reguler",
            "Fast",
            "Express"
        });
        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(paketComboBox, constraints);

        showPaketButton = new JButton("Show Paket");
        constraints.gridx = 2;
        constraints.gridy = 0;
        mainPanel.add(showPaketButton, constraints);

        beratLabel = new JLabel("Berat Cucian (Kg):");
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(beratLabel, constraints);

        beratTextField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(beratTextField, constraints);

        setrikaCheckBox = new JCheckBox(
            "Tambah Setrika Service (1000/Kg)"
        );
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(setrikaCheckBox, constraints);

        antarCheckBox = new JCheckBox(
            "Tambah Antar Service" +
            "(2000/4Kg pertama, kemudian 500/Kg)"
        );
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPanel.add(antarCheckBox, constraints);

        createNotaButton = new JButton("Buat Nota");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        mainPanel.add(createNotaButton, constraints);

        backButton = new JButton("Kembali");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        mainPanel.add(backButton, constraints);

        showPaketButton.addActionListener(e -> showPaket());                    /* Assigning listeners                */
        createNotaButton.addActionListener(e -> createNota());
        backButton.addActionListener(e -> handleBack());
    }

    private void showPaket() {                                                  /* Displaying pakey options           */
        String paketInfo = """
            \r+-------------Paket-------------+
            \r| Express | 1 Hari | 12000 / Kg |
            \r| Fast    | 2 Hari | 10000 / Kg |
            \r| Reguler | 3 Hari |  7000 / Kg |
            \r+-------------------------------+
            """;

        JTextArea label = new JTextArea(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(
            this,
            label,
            "Paket Information",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    private void createNota() {                                                 /* validates and displays new nota    */
        if (validateItems()) {

            int berat = Integer.parseInt(beratTextField.getText());
            if (berat < 2) {
                berat = 2;
                JOptionPane.showMessageDialog(
                    this,
                    "Berat kurang dari 2kg, namun terhitung 2kg...",
                    "Minimum berat",
                    JOptionPane.WARNING_MESSAGE
                );
            }

            Nota newNota = new Nota(
                memberSystemGUI.getLoggedInMember(),
                berat,
                (String) paketComboBox.getSelectedItem(),
                fmt.format(cal.getTime())
            );

            if (setrikaCheckBox.isSelected()) {
                newNota.addService(new SetrikaService());
            }

            if (antarCheckBox.isSelected()) {
                newNota.addService(new AntarService());
            }

            memberSystemGUI.getLoggedInMember().addNota(newNota);
            NotaManager.addNota(newNota);

            resetFields();

            MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);            /* goes back to member page           */
        }
    }

    private void handleBack() {                                                 /* goes back to member page           */
        resetFields();
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }

    private void resetFields() {                                                /* Resets the text fields and boxes   */
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }

    private boolean validateItems() {                                           /* validates user input               */
        if (!beratTextField.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(
                this,
                "Tolong input berat berbentuk angka",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
            );

            return false;
        }
        return true;
    }
}
