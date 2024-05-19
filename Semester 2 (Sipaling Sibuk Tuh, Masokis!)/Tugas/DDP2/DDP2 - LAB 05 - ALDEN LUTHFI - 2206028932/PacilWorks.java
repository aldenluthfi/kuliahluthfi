import java.util.ArrayList;                                                     /* Import necessary utilities         */
import java.util.Scanner;

public class PacilWorks {
    private static ArrayList<Employee> employees = new ArrayList<>();           /* Employee masterlist                */
    private static Scanner in = new Scanner(System.in);                         /* Main program scanner               */

    public static void outln(Object x) {                                        /* Shortcut untuk println statement   */
        System.out.println(x);
    }

    public static void outf(String format, Object... args) {                    /* Shortcut untuk printf statement    */
        System.out.printf(format, args);
    }

    public static void out(Object x) {                                          /* Shorcut untuk print statement      */
        System.out.print(x);
    }

    private static String sep() {                                               /* String separator                   */
        return "=".repeat(64);
    }

    private static void nextYears(int n) {                                      /* Fungsi driver next years pekerja   */
        employees.forEach(e -> e.nextYears(n));
    }

    private static String daftarEmployee() {                                    /* Mencetak daftar pegawai            */

        if (employees.size() == 0) {                                            /* Kalau listnya kososng              */
            return "PacilWorks tidak memiliki karyawan :(\n%s"
                .formatted(
                    sep()
                );
        }

        String[] result = {"%s\nPacilWorks memiliki %d total karyawan:\n"       /* String[] biar mutable              */
                .formatted(
                    sep(),
                    employees.size()
                )
            };

        employees.forEach(e -> result[0] += "%s".formatted(e));
        return result[0];                                                       /* Return formatted string            */
    }

    private static void handleAddEmployee(){

        String role, nama;

        out("Masukkan role employee: ");
        role = in.nextLine();                                                   /* Input dijamin valid                */

        out("Nama: ");
        nama = in.nextLine();                                                   /* Input dijamin valid                */

        switch (role.toLowerCase()) {                                           /* Menyesuaikan dengan role           */
            case "engineer" -> {
                out("Banyak Side Jobs: ");
                int sideJob = Integer.parseInt(in.nextLine());

                employees.add(new Engineer(nama, sideJob));                     /* Menambahkan seorang engineer       */
            }
            case "secretary" -> {
                out("Banyak Tunjangan: ");
                double tunjangan = Double.parseDouble(in.nextLine());;

                employees.add(new Secretary(nama, tunjangan));                  /* Menambahkan seorang secretary      */
            }
            case "manager" -> employees.add(new Manager(nama, 1.25));           /* Menambahkan seorang manager        */
            default -> outln("Masukkan tidak sesuai. Silahkan coba lagi!");
        }
    }

    public static void main(String[] args) {                                    /* Main method dari program           */
        out("Selamat datang di PacilWorks!\n");

        mainloop: while(true) {
            outf(
                "Silakan pilih salah satu opsi berikut:\n" +
                "[1] Daftar Karyawan\n" +
                "[2] Tambah Karyawan\n" +
                "[3] Simulasi n-tahun berikutnya\n" +
                "[*] Keluar\n" +
                "%s\n",
                sep()
            );

            out("Input: ");
            String pilihan = in.nextLine();                                     /* input pilihan menu                 */

            switch (pilihan) {
                case "1" -> outln(daftarEmployee());                            /* Mencetak daftar pegawai            */
                case "2" -> handleAddEmployee();                                /* Menambah pegawai                   */
                case "3" -> {                                                   /* Mensimulasikan pergantian tahun    */
                    out("Masukkan banyak tahun yang ingin disimulasikan: ");

                    int banyakTahun = Integer.parseInt(in.nextLine());
                    nextYears(banyakTahun);

                    outf("%d tahun telah berlalu...\n%s\n", banyakTahun, sep());
                }
                default -> {                                                    /* Mengakhiri program                 */
                    outln(
                        "Terima kasih telah menggunakan layanan PacilWorks ~ !"
                    );
                    break mainloop;
                }
            }
        }

        in.close();                                                             /* Menutup input stream               */
    }
}