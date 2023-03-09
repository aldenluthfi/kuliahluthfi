import java.util.Scanner;

public class Lab03 {

    /*================================================================================================================*\
    -----------------------------------------------FIELDS AND STRUCTS---------------------------------------------------
    \*================================================================================================================*/

    static Scanner input = new Scanner(System.in);                              /* Deklarasi scanner                  */
    static String[] mataKuliah;                                                 /* Array primitif nama matkul         */
    static Mahasiswa[] daftarOrang = new Mahasiswa[1];                          /* Array primitif untuk mahasiswa     */

    class Mahasiswa {                                                           /* Struct untuk seorang mahasiswa     */
        String name;
        int[] nilai;
    }

    /*================================================================================================================*\
    -------------------------------------------------HELPER METHODS-----------------------------------------------------
    \*================================================================================================================*/

    private static void out(Object x) {                                         /* Shortcut untuk print               */
        System.out.println(x);
    }

    private static void out(String format, Object... x) {                       /* Shortcut untuk print               */
        System.out.printf(format, x);
    }

    private static String sin(String message) {                                 /* String Input dengan pesan          */
        out("%s", message);
        return input.nextLine();
    }

    private static int iin(String message) {                                    /* Input int dengan pesan             */
        out("%s", message);
        return Integer.parseInt(input.nextLine());
    }

    private static int iin(String format, Object... x) {                        /* Input int dengan format pesan      */
        out(format, x);
        return Integer.parseInt(input.nextLine());
    }

    private static Mahasiswa[] append(Mahasiswa[] array, Mahasiswa element) {   /* Efficient dynamic array            */

        Mahasiswa[] newArray = new Mahasiswa[array.length];
        int idx;

        if (array[array.length - 1] != null) {                                  /* Kalau sudah penuh                  */
            newArray =  new Mahasiswa[array.length * 2];                        /* Create new array twice the size */
        }

        for (idx = 0; idx < array.length && array[idx] != null; idx++) {        /* Copy arrays                        */
            newArray[idx] = array[idx];                                         /* Inserting values                   */
        }

        newArray[idx] = element;                                                /* Adding the new element             */

        return newArray;
    }

    private static Mahasiswa[] pop(Mahasiswa[] array, String name) {            /* Method menghapus element           */

        Mahasiswa[] newArray = new Mahasiswa[array.length];
        int idx = 0;

        if (array.length > 2 && array[array.length / 2 + 1] == null) {
            newArray = new Mahasiswa[array.length / 2];                         /* Create new array half the size     */
        }

        for (Mahasiswa m: array) if (!m.name.equals(name)) newArray[idx++] = m; /* Adding all other elements          */

        return newArray;
    }

    private static void printMenu() {                                           /* Dari template untuk print menu     */
        out("==============Menu==============");
        out("[1] Menambah Mahasiswa");
        out("[2] Menghapus Mahasiswa");
        out("[3] Mencetak Tabel");
        out("[4] Mencetak Nilai");
        out("[0] Keluar");
    }

    /*================================================================================================================*\
    --------------------------------------------------MAIN METHODS------------------------------------------------------
    \*================================================================================================================*/

    public static void main(String[] args) {                                    /* Main methos                        */

        out("Selamat datang di BeJayNG!");
        out("==============Initial Input==============");

        int jumlah = iin("Masukkan jumlah mata kuliah: ");
        mataKuliah = new String[jumlah];                                        /* Initialize matkul array            */

        for (int i = -1; ++i < jumlah;) {                                       /* Funny for loop for every matkul    */
            mataKuliah[i] = sin("Masukkan nama mata kuliah: ");                 /* Input mata kuliah                  */
        }

        mainloop: while (true) {                                                /* Main program loop                  */

            printMenu();
            int pilihan = iin("Masukkan pilihan: ");                            /* Input pilihan                      */

            switch (pilihan) {                                                  /* Memilih pilihan menu               */
                case 0 -> {
                    out("Terima kasih telah menggunakan BeJayNG!");
                    break mainloop;
                }
                case 1 -> menambahMahasiswa();
                case 2 -> menghapusMahasiswa();
                case 3 -> mencetakTabel();
                case 4 -> mencetakNilai();
                default -> out("Pilihan tidak valid!");
            }
        }

        input.close();                                                          /* Menutup input stream               */
    }

    static void menambahMahasiswa() {                                           /* Method untuk menambah mahasisaw    */
        out("==============Menambah Mahasiswa==============");                  /* Set new array                      */

        Mahasiswa o =  (new Lab03()).new Mahasiswa();                           /* Instansiasi mahasiswa o            */

        o.name = sin("Masukkan nama mahasiswa: ");                              /* Input nama mahasiswa               */
        o.nilai = new int[mataKuliah.length];

        for (int i = 0; i < mataKuliah.length; i++) {                           /* Loop untuk input nilai             */
            o.nilai[i] = iin("Masukkan nilai %s: ", mataKuliah[i]);
        }

        daftarOrang = append(daftarOrang, o);                                   /* Menambah mahasiswa ke list         */

        out("Nilai mahasiswa bernama %s berhasil diinput ke BeJayNG\n", o.name);
    }

    static void menghapusMahasiswa() {                                          /* Method untuk menghapus mahasiswa   */
        out("==============Menghapus Mahasiswa==============");

        String targetName = sin("Masukkan nama mahasiswa: ");
        daftarOrang = pop(daftarOrang, targetName);                             /* Assigning new list                 */

        out("Mahasiswa bernama %s telah dihapus dari BeJayNG\n", targetName);
    }

    static void mencetakTabel() {                                               /* Method untuk mencetak tabel        */
        out("==============Mencetak Tabel==============");
        out("%-15s\t\t\t", "Nama");

        for (String matkul: mataKuliah) out("%-8s\t", matkul);                  /* Mencetak judul tabel               */

        for (Mahasiswa m: daftarOrang) if (m != null) {
            out("\n%-15s\t\t\t", m.name);
            for (int nilai: m.nilai) out("%-8d\t", nilai);                      /* Mengeprint nilai setiap mahaiswa   */
        }

        out("%s", "\n");
    }

    static void mencetakNilai() {                                               /* mencetak nilai                     */
        out("==============Mencetak Nilai==============");
        String nama = sin("Masukkan nama mahasiswa: ");                         /* Input nama mahasiswa               */
        String matkul = sin("Masukkan nama mata kuliah: ");                     /* Input nama matkul                  */

        int nilai = 0;
        Mahasiswa orang = null;

        for (Mahasiswa m: daftarOrang) if (m != null) {                         /* Mengambil objek mahasiswa          */
            if (m.name.equals(nama)) orang = m;
        }

        for (int i = 0; i < mataKuliah.length; i++) {                           /* Mengambil nilai                    */
            if (mataKuliah[i].equals(matkul)) nilai = orang.nilai[i];           /* Jika sudah ketemu matkulnya */
        }

        out("Nilai %s di mata kuliah %s adalah %d\n", nama, matkul, nilai);     /* Output cetak nilai                 */
    }
}
