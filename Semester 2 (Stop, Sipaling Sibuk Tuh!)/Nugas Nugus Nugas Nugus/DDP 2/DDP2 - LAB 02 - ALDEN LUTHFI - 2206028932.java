import java.util.Scanner;

public class Lab02 {

    private static Scanner input = new Scanner(System.in);                      /* Scanner yang akan digunakan          */

    private static String checksumDriver(String kode) {                         /* Method untuk membuat string checksum */

        char first = (char) (checksum(kode) % 26 + 'A');
        char second = (char) (checksum(kode + first) % 26 + 'A');

        return kode + first + second;
    }

    private static int checksum(String kode) {                                  /* Method rekursi untuk checksum        */
        return kode.charAt(0) - 'A' + (kode.length() == 1 ?
            0 :                                                                 /* Base case                            */
            checksum(kode.substring(1)));                                       /* Recursive case                       */
    }

    private static boolean checkChecksum(String checksumString) {               /* Methid untuk mengecek checksum       */

        String kode = checksumString.substring(0, checksumString.length() - 2);
        return checksumString.equals(checksumDriver(kode));
    }

    public static void main(String args[]) {

        mainloop: while (true) {                                                /* Loop utama program                   */

            System.out.print(
                """
                Halo! Apa yang ingin kamu lakukan?
                [1] Buat kupon
                [2] Validasi kupon
                [3] Keluar
                """
            );
            System.out.print("Pilihan: ");
            int pilihan = Integer.parseInt(input.nextLine());                   /* Input pilihan                        */

            switch (pilihan) {

                case 1 -> {                                                     /* Membuat kupon                        */
                    System.out.print("Nama kupon: ");
                    String kode = input.nextLine();
                    System.out.printf(
                        "Kode kupon adalah: %s\n\n",
                        checksumDriver(kode)
                    );
                }

                case 2 -> {                                                     /* Validasi kupon                       */
                    System.out.print("kupon: ");
                    String kupon = input.nextLine();
                    System.out.print(
                        checkChecksum(kupon) ?
                        "Kupon yang diberikan valid\n\n" :
                        "Kupon yang diberikan tidak valid\n\n"
                    );
                }

                case 3 -> {                                                     /* Keluar dari program                  */
                    break mainloop;
                }
            }
        }
    }
}
