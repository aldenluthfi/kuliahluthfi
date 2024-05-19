package assignments.assignment3.user.menu;

/*====================================***=====================================*\
|---------------------------------- IMPORTS -----------------------------------|
\*============================================================================*/

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------------------------------- CLASS -------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

public class MemberSystem extends SystemCLI {

/*====================================***=====================================*\
|--------------------------------- IO METHODS ---------------------------------|
\*============================================================================*/

    @Override
    protected void displaySpecificMenu() {
        outln("1. Saya ingin laundry");
        outln("2. Lihat detail nota saya");
        outln("3. Logout");
    }

    private void showPaket() {                                                  /* Mencetak bantuan paket             */
        outln("+-------------Paket-------------+");
        outln("| Express | 1 Hari | 12000 / Kg |");
        outln("| Fast    | 2 Hari | 10000 / Kg |");
        outln("| Reguler | 3 Hari |  7000 / Kg |");
        outln("+-------------------------------+");
    }

    private String[] inputPaketBerat() {

        String paket;
        int berat;

        do {
            outln("Masukkan paket laundry:");
            paket = in.nextLine();

            if (paket.equals("?")) {                                            /* Menampilkan bantuan                */
                showPaket();
            } else if (!paket.matches("(?i)reguler|fast|express")) {            /* Validasi input paket               */
                outf("Paket %s tidak diketahui\n", paket);
                outln("[ketik ? untuk mencari tahu jenis paket]");
            }

        } while (!paket.matches("(?i)reguler|fast|express"));

        outln("Masukkan berat cucian Anda [Kg]:");
        while (true) {                                                          /* Input berat cucian                 */
            try {
                berat = Integer.parseInt(in.nextLine());

                if (berat <= 0) {                                               /* jika negatif                       */
                    throw new NumberFormatException();
                } else if (berat == 1 && ++berat == 2) {                        /* jika < 2, pake lazy evaluation     */
                    outln(
                        "Cucian kurang dari 2 kg, maka cucian akan " +
                        "dianggap sebagai 2 kg"
                    );
                }
                break;

            } catch (NumberFormatException $) {                                 /* Validasi angka di input            */
                outln(
                    "Harap masukkan berat cucian Anda dalam " +
                    "bentuk bilangan positif."
                );
            }
        }

        String[] result = { paket, Integer.toString(berat) };
        return result;
    }

/*====================================***=====================================*\
|-------------------------------- FUNCTIONALITY -------------------------------|
\*============================================================================*/

    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;

        switch (choice) {
            case 1 -> newNota();
            case 2 -> displayListNota();
            case 3 -> logout = true;
        }

        return logout;
    }

    public void addMember(Member member) {
        Member[] newArray;
        int idx;

        if (memberList[memberList.length - 1] != null) {                        /* Kalau sudah penuh                  */
            newArray = new Member[memberList.length * 2];                       /* Create new array twice the size */
        } else {
            newArray = new Member[memberList.length];
        }

        for (idx = 0; idx < memberList.length; idx++) {                         /* Copy arrays                        */
            if (memberList[idx] == null) break;
            newArray[idx] = memberList[idx];                                    /* Inserting values                   */
        }

        newArray[idx] = member;                                                 /* Adding the new element             */
        memberList = newArray;
    }

    public Member newMember(Member member) {
        addMember(member);
        return member;
    }

    private void displayListNota() {
        for (Nota nota : loginMember.getNotaList()) outln(nota);
    }

    private void newNota() {

        String paketBerat[] = inputPaketBerat(), paket = paketBerat[0];
        int berat = Integer.parseInt(paketBerat[1]);

        Nota newNota = new Nota(
            loginMember,
            berat,
            paket,
            fmt.format(cal.getTime())
        );

        out(
            "Apakah kamu ingin cucianmu disetrika oleh " +
            "staff professional kami? Hanya tambah 1000/kg\n" +
            "[Ketik x untuk tidak mau]: "
        );

        boolean setrika = !in.nextLine().equals("x");

        out(
            "Mau diantar oleh kurir kami? " +
            "Dijamin aman dan cepat sampai tujuan!\n" +
            "[Ketik x untuk tidak mau]: "
        );

        boolean antar = !in.nextLine().equals("x");

        if (setrika) {
            newNota.addService(new SetrikaService());
        }

        if (antar) {
            newNota.addService(new AntarService());
        }

        loginMember.addNota(newNota);
        NotaManager.addNota(newNota);

        outln("Nota berhasil dibuat!");
    }
}