package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;                                       /* Import project files               */
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {                      /* Page for the launcry staff         */
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {                             /* Creates an instance                */
        super(systemCLI);
    }


    @Override
    public String getPageName(){                                                /* Getters                            */
        return KEY;
    }

    @Override
    protected JButton[] createButtons() {                                       /* Creates widgets                    */
        return new JButton[]{
            new JButton("It's nyuci time!"),
            new JButton("Display list nota")
        };
    }

    @Override
    protected ActionListener[] createActionListeners() {                        /* Creates listeners                  */
        return new ActionListener[]{
            e -> cuci(),
            e -> displayNota(),
        };
    }

    private void displayNota() {                                                /* displays work is and isn't done    */
        String notaListString = "";

        for (Nota nota : NotaManager.notaList) if (nota != null){
            notaListString += "%s\n".formatted(nota.getNotaStatus());
        }

        JTextArea textArea = new JTextArea(notaListString);
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setPreferredSize(new Dimension(300, 300));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Nota Information",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    private void cuci() {                                                       /* displays work thats being done     */
        String notaStatusString = "";

        for (Nota nota: NotaManager.notaList) if (nota != null) {
            notaStatusString += "%s\n".formatted(nota.kerjakan());
        }

        JTextArea textArea = new JTextArea(notaStatusString);
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setPreferredSize(new Dimension(300, 300));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Nota Information",
            JOptionPane.PLAIN_MESSAGE
        );
    }
}
