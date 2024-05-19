package assignments.assignment2;                                                /* Imports and packages               */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);                /* input stream untuk program         */
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");   /* Date system driver                 */
    private static Calendar cal = Calendar.getInstance();                       /* Calendar instance                  */

    private static Nota[] notaList;                                             /* List untuk nota                    */
    private static Member[] memberList;                                         /* list untuk Member                  */

    public static <T> int getLength(T[] arr){                                   /* Menghitung panjang elemen non-null */
        int count = 0;
        for(T el : arr) if (el != null) ++count;
        return count;
    }

    public static <T, E> T find(T[] array, E element) {                         /* mencari elemen dalam list          */
        for (T e : array) if (e != null && e.equals(element)) return e;
        return null;
    }

    public static void main(String[] args) {                                    /* Main methdo dari program           */
        mainloop: while (true) {                                                /* Main loop dari program             */

            printMenu();

            out("Pilihan : ");
            String command = input.nextLine();                                  /* Input pilihan                      */
            outln("================================");

            switch (command) {                                                  /* Percabangan pilihan menu           */
                case "0" -> {break mainloop;}
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                default -> {
                    outln("Perintah tidak diketahui, silakan periksa kembali.");
                }
            }
        }
        input.close();                                                          /* Meng-close input stream            */
        outln("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {                                  /* Membuat user baru                  */
        String[] namaHP = inputNamaHP();                                        /* Menghandle input nama dan nomor hp */
        String nama = namaHP[0], noHP = namaHP[1];

        Member newMember = new Member(nama, noHP);                              /* Membuat instance member baru       */
        if (memberList == null || !newMember.isIn(memberList)) {                /* Mengecek validitas member          */
            memberList = newMember.appendTo(memberList);
            outf("Berhasil membuat member dengan ID %s!\n", newMember.getID());
        } else {                                                                /* Kalau sudah ada member di list     */
            outf(
                "Member dengan nama %s dan nomor hp %s sudah ada!\n",
                nama,
                noHP
            );
        }
    }

    private static void handleGenerateNota() {                                  /* Membuat nota baru untuk user       */

        outln("Masukan ID member:");
        String id = input.nextLine();                                           /* Input id                           */
        Member member = MainMenu.<Member, String>find(memberList, id);

        if (member == null) {                                                   /* Jika ID tidak ditemukan            */
            outf("Member dengan ID %s tidak ditemukan!\n", id);
            return;
        }

        String[] paketBerat = inputPaketBerat();                                /* Menghandle input paket dan berat   */
        String paket = paketBerat[0];
        int berat = Integer.parseInt(paketBerat[1]);

        member.incrementBonus();                                                /* Menambah bonus counter pengguna    */
        Nota nota = new Nota(member, paket, berat, fmt.format(cal.getTime()));

        notaList = nota.appendTo(notaList);                                     /* Menambahkan nota ke list           */
        outf(
            "Berhasil menambahkan nota!\n[ID Nota = %d]\n%s\n",
            nota.getID(),
            nota.toVerboseString()
        );
    }

    private static void handleListNota() {                                      /* Mencetak list nota                 */
        outf(
            "Terdaftar %d nota dalam sistem.\n",
            notaList == null ? 0 : getLength(notaList));                        /* Menghitung jumlah nota             */

        if (notaList != null) {
            for (Nota n: notaList) if (n != null) outln(n.toString());
        }
    }

    private static void handleListUser() {                                      /* Mencetak list user                 */
        outf(
            "Terdaftar %d member dalam sistem.\n",
            memberList == null ? 0 : getLength(memberList));                    /* Menghitung jumlah user             */

        if (memberList != null) {
            for (Member m: memberList) if (m != null) outln(m.toString());
        }
    }

    private static void handleAmbilCucian() {

        String id;                                                              /* Input id                           */

        outln("Masukan ID nota yang akan diambil:");
        do {                                                                    /* Input nota id                      */
            id = input.nextLine();

            if (!id.matches("[0-9]+")) {
                outln("ID nota berbentuk angka!");
            }

        } while (!id.matches("[0-9]+"));                                        /* Validasi input nomor hp            */

        Nota nota = MainMenu.<Nota, Integer>find(notaList, Integer.parseInt(id));/* Mmencari nota dalam list nota     */

        if (nota == null) {
            outf("Nota dengan ID %s tidak ditemukan!\n", id);
        } else if (nota.getIsReady()) {
            outf("Nota dengan ID %s berhasil diambil!\n", id);
            notaList = nota.popFrom(notaList);
        } else {
            outf("Nota dengan ID %s gagal diambil!\n", id);
        }

    }

    private static void handleNextDay() {                                       /* Menghandle pergantian hari         */
        cal.add(Calendar.DATE, 1);                                              /* Increment tanggal                  */
        outln("Dek Depe tidur hari ini... zzz...");

        if (notaList != null)
        for (Nota n: notaList) if (n != null) {
            n.decrementSisa();

            if(n.getIsReady()) {                                                /* Mengecek sudah selesai apa belum   */
                outf(
                    "Laundry dengan nota ID %d sudah dapat diambil!\n",
                    n.getID()
                );
            }
        }

        outln("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {                                           /* Format menu                        */
        outln("\nSelamat datang di CuciCuci!");
        outf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        outln("==============Menu==============");
        outln("[1] Generate User");
        outln("[2] Generate Nota");
        outln("[3] List Nota");
        outln("[4] List User");
        outln("[5] Ambil Cucian");
        outln("[6] Next Day");
        outln("[0] Exit");
    }

}
