/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|----------------------------------------------- MAAF KALAU KOMENNYA NORAK --------------------------------------------|
|------------------------------------------ BUTUH VISUAL BREAK BIAR TETAP WARAS ---------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/


/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

package assignments.assignment3;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

import java.util.Scanner;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------------------------------- CLASS -------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public class MainMenu {

/*====================================***=====================================*\
|---------------------------------- FIELDS ------------------------------------|
\*============================================================================*/

    private final Scanner in;
    private final LoginManager loginManager;

/*====================================***=====================================*\
|--------------------------------- CONSTRUCTOR --------------------------------|
\*============================================================================*/

    public MainMenu(Scanner in, LoginManager loginManager) {
        this.in = in;
        this.loginManager = loginManager;
    }

/*====================================***=====================================*\
|--------------------------------- IO METHODS ---------------------------------|
\*============================================================================*/

    public void outln(Object x) {                                               /* Shortcut untuk println statement   */
        System.out.println(x);
    }

    public void outf(String format, Object... args) {                           /* Shortcut untuk printf statement    */
        System.out.printf(format, args);
    }

    public void out(Object x) {                                                 /* Shorcut untuk print statement      */
        System.out.print(x);
    }

    public String inputNama() {                                                 /* Input nama                         */
        outln("Masukkan nama Anda:");
        return in.nextLine();
    }

    public String inputPassword() {                                             /* Input password                     */
        out("Masukan password Anda: ");
        return in.nextLine();
    }

    public String inputId() {                                                   /* Input id                           */
        out("Masukan ID Anda: ");
        return in.nextLine();
    }

    public String inputHp() {                                                   /* Input nomor hp                     */
        String hp;

        outln("Masukkan nomor handphone Anda:");
        do {
            hp = in.nextLine();

            if (!hp.matches("[0-9]+")) {
                outln("Nomor hp hanya menerima digit");
            }

        } while (!hp.matches("[0-9]+"));                                        /* Validasi input nomor hp            */

        return hp;
    }

    private void displayMenu() {
        outln("Selamat datang di CuciCuci System!");
        outf("Sekarang tanggal %s\n", fmt.format(cal.getTime()));
        outln("1. Login");
        outln("2. Register Member");
        outln("3. Tidur (Skip hari)");
        outln("4. Exit");
        out("Apa yang ingin Anda lakukan hari ini? ");
    }

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(
            new Scanner(System.in),
            new LoginManager(
                new EmployeeSystem(),
                new MemberSystem()
            )
        );
        mainMenu.run();
    }

    public void run() {
        mainloop: while (true) {
            displayMenu();

            switch (in.nextLine()) {
                case "1" -> login();
                case "2" -> register();
                case "3" -> toNextDay();
                case "4" -> {break mainloop;}
                default -> outln("Pilihan tidak valid, silakan coba lagi.");
            }
        }
        in.close();
    }

    private void toNextDay() {
        outln("Kamu tidur hari ini... zzz...");
        NotaManager.toNextDay();
    }

    void register() {
        String nama = inputNama();
        String noHp = inputHp();
        String password = inputPassword();

        Member registeredMember = loginManager.register(nama, noHp, password);

        if (registeredMember == null) {
            outf(
                "User dengan nama %s dan nomor hp %s sudah ada!\n\n",
                nama,
                noHp
            );
            return;
        }

        outf("Berhasil membuat user dengan ID %s!\n\n",
            registeredMember.getId()
        );
    }

    private void login() {
        String id = inputId();
        String password = inputPassword();

        SystemCLI systemCLI = loginManager.getSystem(id);

        if (systemCLI == null) {
            outln("ID atau password invalid.");
            return;
        }

        systemCLI.login(in, id, password);
    }
}