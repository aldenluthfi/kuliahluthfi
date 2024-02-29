import java.util.Scanner;


public class Nilai {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            
            System.out.print("Masukkan Nama Mahasiswa: ");
            String nama = input.nextLine();
            System.out.print("Masukkan Nilai Asli: ");
            int nilaiAsli = input.nextInt();
            System.out.print("Masukkan Durasi: ");
            int durasi = input.nextInt();

            double indeks;

            if (durasi < 60) {
                indeks = 1.2;
            } else if (durasi <= 70) {
                indeks = 1;
            } else if (durasi < 90) {
                indeks = 0.75;
            } else if (durasi <= 100) {
                indeks = 0.5;
            } else {
                indeks = 0.2;
            }

            System.out.printf("%s mendapatkan nilai akhir: %f\n", nama, nilaiAsli * indeks);
        }
    }
    
}
