package assignments.assignment1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    public static void outln(Object x) {                                               /* Shortcut untuk println statement   */
        System.out.println(x);
    }

    public static void outf(String format, Object... args) {                           /* Shortcut untuk printf statement    */
        System.out.printf(format, args);
    }

    public static void out(Object x) {                                                 /* Shorcut untuk print statement      */
        System.out.print(x);
    }

    public static void main(String[] args) {

        int pilihan;                                                            /* Deklarasi variabel pilihan         */

        mainloop: while (true) {                                                /* Loop utama                         */

            printMenu();                                                        /* Mengeprint menu                    */
            out("Pilihan : ");

            try {
                pilihan = Integer.parseInt(input.nextLine());                   /* Input pilihan menu                 */

                if (pilihan < 0 || pilihan > 2) {                               /* Validasi input pilihan manu        */
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException $) {                                 /* Tangkap exception                  */
                outln("Perintah tidak diketahui, silakan periksa kembali.");
                continue;
            }

            outln("================================");

            switch (pilihan) {
                case 0 -> {                                                     /* Fungsi exit dari program           */
                    outln("Terima kasih telah menggunakan NotaGenerator!");
                    break mainloop;
                }
                case 1 -> {
                    String[] namaHP = inputNamaHP();                            /* Array string yang berisi nama dan  */
                    outf("ID Anda : %s\n", generateId(namaHP[0], namaHP[1]));   /* nomor hp                           */
                }
                case 2 -> {
                    String[] namaHP = inputNamaHP();
                    String id = generateId(namaHP[0], namaHP[1]);

                    String[] paketBerat = inputPaketBerat();

                    String paket = paketBerat[0], tanggalTerima;
                    int berat = Integer.parseInt(paketBerat[1]);

                    outln("Masukkan tanggal terima:");
                    tanggalTerima = input.nextLine();                           /* Input tanggal terima tanpa validasi*/

                    outf("Nota Laundry\n%s\n",
                        generateNota(id, paket, berat, tanggalTerima)
                    );
                }
            }
        }
    }

    private static void printMenu() {                                           /* Mencetak menu                      */
        outln("Selamat datang di NotaGenerator!");
        outln("==============Menu==============");
        outln("[1] Generate ID");
        outln("[2] Generate Nota");
        outln("[0] Exit");
    }

    private static void showPaket() {                                           /* Mencetak bantuan paket             */
        outln("+-------------Paket-------------+");
        outln("| Express | 1 Hari | 12000 / Kg |");
        outln("| Fast    | 2 Hari | 10000 / Kg |");
        outln("| Reguler | 3 Hari |  7000 / Kg |");
        outln("+-------------------------------+");
    }

    public static String[] inputNamaHP() {
        String nama, hp;

        outln("Masukkan nama Anda:");
        nama = input.nextLine();

        outln("Masukkan nomor handphone Anda:");

        do {                                                                    /* Input nomor hp                     */
            hp = input.nextLine();

            if (!hp.matches("[0-9]+")) {
                outln("Nomor hp hanya menerima digit");
            }

        } while (!hp.matches("[0-9]+"));                                        /* Validasi input nomor hp            */

        String[] result = { nama, hp };
        return result;
    }

    public static String[] inputPaketBerat() {

        String paket;
        int berat;

        do {
            outln("Masukkan paket laundry:");
            paket = input.nextLine();

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
                berat = Integer.parseInt(input.nextLine());

                if (berat <= 0) {                                               /* jika negatif                       */
                    throw new NumberFormatException();
                } else if (berat == 1 && ++berat == 2) {                        /* jika < 2, pake lazy evaluation     */
                    outln("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                }
                break;

            } catch (NumberFormatException $) {                                 /* Validasi angka di input            */
                outln("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            }
        }

        String[] result = { paket, Integer.toString(berat) };
        return result;
    }

    public static String generateId(String nama, String nomorHP) {

        int checkSum = 0;
        String firstName = "";

        for (int i = 0; i < nama.length() && nama.charAt(i) != ' '; i++) {      /* Mengambil nama pertama dari nama   */
            firstName += (nama.charAt(i) + "").toUpperCase();
        }

        for (char c : (firstName + '-' + nomorHP).toCharArray()) {              /* Menghitung checksum                */
            switch (c) {
                case                                                            /* Kasus karakter huruf               */
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                    'Y', 'Z' -> checkSum += (c - 'A') + 1;
                case                                                            /* Kasus karakter angka               */
                    '0', '1', '2', '3', '4',
                    '5', '6', '7', '8', '9' -> checkSum += c - '0';
                default -> checkSum += 7;                                       /* Selain huruf atau angka            */
            }
        }

        return "%s-%s-%02d".formatted(firstName, nomorHP, checkSum % 100);      /* Return string terformat            */
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {

        SimpleDateFormat formatOne = new SimpleDateFormat("dd/mm/yyyy");        /* format tanggal untuk string        */
        SimpleDateFormat formatTwo = new SimpleDateFormat("yyyy-mm-dd");        /* format tanggal untuk di-parse      */
        String result = "ID    : %s\n".formatted(id);                           /* Memulai pemformattan output        */
        String tanggalSelesai;                                                  /* String tanggal selesai terformat   */
        Date terima, selesai;                                                   /* Object Date yang bisa di format    */

        try {
            terima = formatOne.parse(tanggalTerima);
        } catch (ParseException $) {
            return null;
        }

        result += "Paket : %s\n".formatted(paket.toLowerCase());                /* mMnambah jenis paket               */
        tanggalTerima = formatTwo.format(terima);                               /* Format string tanggal agar di parse*/

        switch (paket.toLowerCase()) {
            case "express" -> {                                                 /* pilihan paket express              */
                result += "Harga :\n%d kg x 12000 = %d\n"
                        .formatted(berat, berat * 12000);
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(1)
                        .toString();
            }
            case "fast" -> {                                                    /* pilihan paket fast                 */
                result += "Harga :\n%d kg x 10000 = %d\n"
                        .formatted(berat, berat * 10000);
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(2)
                        .toString();
            }
            case "reguler" -> {                                                 /* pilihan paket reguler              */
                result += "Harga :\n%d kg x 7000 = %d\n"
                        .formatted(berat, berat * 7000);
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(3)
                        .toString();
            }
            default -> tanggalSelesai = "IMPOSSIBLE";                           /* Kasus tak mungkin terjadi karena   */
        }                                                                       /* input dijamin valid                */

        try {
            selesai = formatTwo.parse(tanggalSelesai);                          /* Format tanggal selesai             */
        } catch (ParseException $) {
            return null;
        }

        result += "Tanggal Terima  : %s\n".formatted(formatOne.format(terima));
        result += "Tanggal Selesai : %s".formatted(formatOne.format(selesai));

        return result;                                                          /* Mereturn string yang sudah diformat*/
    }

        public static String generateNota(String id, String paket, int berat, String tanggalTerima, int diskon) {

        SimpleDateFormat formatOne = new SimpleDateFormat("dd/mm/yyyy");        /* format tanggal untuk string        */
        SimpleDateFormat formatTwo = new SimpleDateFormat("yyyy-mm-dd");        /* format tanggal untuk di-parse      */
        String result = "ID    : %s\n".formatted(id);                           /* Memulai pemformattan output        */
        String tanggalSelesai;                                                  /* String tanggal selesai terformat   */
        Date terima, selesai;                                                   /* Object Date yang bisa di format    */

        try {
            terima = formatOne.parse(tanggalTerima);
        } catch (ParseException $) {
            return null;
        }

        result += "Paket : %s\n".formatted(paket.toLowerCase());                /* mMnambah jenis paket               */
        tanggalTerima = formatTwo.format(terima);                               /* Format string tanggal agar di parse*/

        switch (paket.toLowerCase()) {
            case "express" -> {                                                 /* pilihan paket express              */
                result += "Harga :\n%d kg x 12000 = %d%s"
                        .formatted(
                            berat,
                            berat * 12000,
                            diskon == 2 ?
                            " = %d ((Discount member 50%%!!!))\n"
                            .formatted(berat * 12000 / diskon) : "\n"
                        );
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(1)
                        .toString();
            }
            case "fast" -> {                                                    /* pilihan paket fast                 */
                result += "Harga :\n%d kg x 10000 = %d%s"
                        .formatted(
                            berat,
                            berat * 10000,
                            diskon == 2 ?
                            " = %d ((Discount member 50%%!!!))\n"
                            .formatted(berat * 10000 / diskon) : "\n"
                        );
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(2)
                        .toString();
            }
            case "reguler" -> {                                                 /* pilihan paket reguler              */
                result += "Harga :\n%d kg x 7000 = %d%s"
                        .formatted(
                            berat,
                            berat * 7000,
                            diskon == 2 ?
                            " = %d ((Discount member 50%%!!!))\n"
                            .formatted(berat * 7000 / diskon) : "\n"
                        );
                tanggalSelesai = LocalDate
                        .parse(tanggalTerima)
                        .plusDays(3)
                        .toString();
            }
            default -> tanggalSelesai = "IMPOSSIBLE";                           /* Kasus tak mungkin terjadi karena   */
        }                                                                       /* input dijamin valid                */

        try {
            selesai = formatTwo.parse(tanggalSelesai);                          /* Format tanggal selesai             */
        } catch (ParseException $) {
            return null;
        }

        result += "Tanggal Terima  : %s\n".formatted(formatOne.format(terima));
        result += "Tanggal Selesai : %s".formatted(formatOne.format(selesai));

        return result;                                                          /* Mereturn string yang sudah diformat*/
    }
}