package assignments.assignment4;                                                /* plethora of imports                */

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;

import assignments.assignment4.gui.HomeGUI;                                     /* Imports other files                */
import assignments.assignment4.gui.LoginGUI;
import assignments.assignment4.gui.RegisterGUI;
import assignments.assignment4.gui.member.Loginable;
import assignments.assignment4.gui.member.employee.EmployeeSystemGUI;
import assignments.assignment4.gui.member.member.CreateNotaGUI;
import assignments.assignment4.gui.member.member.MemberSystemGUI;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private static MainFrame instance;                                          /* Widgets and instances              */
    private final Loginable[] loginablePanel;
    private final MemberSystem memberSystem = new MemberSystem();
    private final EmployeeSystem employeeSystem = new EmployeeSystem();
    private final CardLayout cards = new CardLayout();
    private final JPanel mainPanel = new JPanel(cards);
    private final LoginManager loginManager = new LoginManager(employeeSystem, memberSystem);
    private final HomeGUI homeGUI = new HomeGUI();
    private final RegisterGUI registerGUI = new RegisterGUI(loginManager);
    private final LoginGUI loginGUI = new LoginGUI();
    private final EmployeeSystemGUI employeeSystemGUI = new EmployeeSystemGUI(employeeSystem);
    private final MemberSystemGUI memberSystemGUI = new MemberSystemGUI(memberSystem);
    private final CreateNotaGUI createNotaGUI = new CreateNotaGUI(memberSystemGUI);

    private MainFrame(){
        super("CuciCuciSystem");                                                /* the title of the window            */

        employeeSystem.addEmployee(new Employee[] {
                new Employee("delta Epsilon Huha Huha", "ImplicitDiff"),
                new Employee("Regret", "FansBeratKanaArima")
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 432);
        setVisible(true);

        loginablePanel = new Loginable[]{                                       /* initializes both systems in a list */
                employeeSystemGUI,
                memberSystemGUI,
        };

        initGUI();

        cards.show(mainPanel, HomeGUI.KEY);                                     /* switches to the home menu          */
        setLocationRelativeTo(null);
        add(mainPanel);
    }

    private void initGUI() {                                                    /* adds the pages to the main panel   */
        mainPanel.add(homeGUI, HomeGUI.KEY);
        mainPanel.add(registerGUI, RegisterGUI.KEY);
        mainPanel.add(loginGUI, LoginGUI.KEY);
        mainPanel.add(employeeSystemGUI, EmployeeSystemGUI.KEY);
        mainPanel.add(memberSystemGUI, MemberSystemGUI.KEY);
        mainPanel.add(createNotaGUI, CreateNotaGUI.KEY);
    }

    public static MainFrame getInstance(){                                      /* so only one instance is occuring   */
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public void navigateTo(String page){                                        /* changes to card based on the key   */
        cards.show(mainPanel, page);
    }

    public boolean login(String id, String password){                           /* used to log in members             */
        boolean loginStatus = false;
        for (Loginable panel : loginablePanel) {
            loginStatus = panel.login(id, password);
            if (loginStatus) {
                navigateTo(panel.getPageName());
                break;
            }
        }
        return loginStatus;
    }

    public void logout(){
        for (Loginable panel : loginablePanel) {                                /* used to log out members             */
            panel.logout();
        }
        navigateTo(HomeGUI.KEY);
    }

    public static void main(String[] args) {                                    /* Main Method                        */
        SwingUtilities.invokeLater(MainFrame::getInstance);
    }
}
