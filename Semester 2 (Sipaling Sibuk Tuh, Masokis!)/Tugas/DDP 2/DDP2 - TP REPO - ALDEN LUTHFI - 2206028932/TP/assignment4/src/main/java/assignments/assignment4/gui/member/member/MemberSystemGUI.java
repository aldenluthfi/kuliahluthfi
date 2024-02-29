package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;                                       /* Import project files               */
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {                        /* page for members                   */
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(MemberSystem memberSystem) {                         /* to create an instance              */
        super(memberSystem);
    }

    @Override
    public String getPageName(){                                                /* Getters                            */
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    @Override
    protected JButton[] createButtons() {                                       /* creating widgets                   */
        return new JButton[]{
            new JButton("Saya ingin laundry"),
            new JButton("Lihat detail nota saya")
        };
    }

    @Override
    protected ActionListener[] createActionListeners() {                        /* Creating listeners                 */
        return new ActionListener[] {
            e -> createNota(),
            e -> showDetailNota(),
        };
    }

    private void showDetailNota() {                                             /* displays nota list                 */
        String notaListString = "";

        for (Nota nota : loggedInMember.getNotaList()) if (nota != null){
            notaListString += "%s\n\n".formatted(nota.toString());
        }

        JTextArea textArea = new JTextArea(notaListString);
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "Nota Information",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    private void createNota() {                                                 /* Goes to the create nota page       */
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}
