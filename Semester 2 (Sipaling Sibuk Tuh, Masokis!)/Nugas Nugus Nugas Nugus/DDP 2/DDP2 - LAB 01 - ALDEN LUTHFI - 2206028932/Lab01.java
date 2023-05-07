import java.util.Scanner;

public class Lab01 {
    static int jumlahCustomer;
    static double total;

    public static void main(String[] args) {
        System.out.printf("Selamat datang di Toko Fotokopi Dek Depe!\n--------------------------------------------------------\n");
        System.out.printf("Masukkan jumlah mahasiswa yang ingin melakukan fotokopi: ");

        try (Scanner input = new Scanner(System.in)) {
            int maxCustomer = Integer.parseInt(input.nextLine());

            // Loop sesuai jumlah customer
            while (++jumlahCustomer <= maxCustomer) {
                System.out.printf("--------------------DATA MAHASISWA %d--------------------\n", jumlahCustomer);

                // Input nama
                System.out.printf("Nama: ");
                String name = input.nextLine();

                // Input ipk
                System.out.printf("IPK: ");
                Double ipk = Double.parseDouble(input.nextLine());

                // Input jumlah lambar
                System.out.printf("Jumlah lembar: ");
                int lembar = Integer.parseInt(input.nextLine());

                int diskon;

                // Menentukan besar diskon
                if (ipk <= 2.5) {diskon = 10;}
                else if (ipk <= 3.0) {diskon = 25;}
                else if (ipk <= 3.5) {diskon = 35;}
                else {diskon = 50;}

                // Menghitung subtotal dan menambahkan ke total
                double subTotal = lembar*555f*(100-diskon) / 100f;
                total += subTotal;

                System.out.printf("%s membayar seharga %.2f dengan diskon sebesar %d%%\n", name, subTotal, diskon);
            }
            System.out.printf("---------------------RINGKASAN DATA---------------------\n", jumlahCustomer);
            System.out.printf("Hasil pendapatan yang diperoleh Toko Fotokopi dari %d mahasiswa adalah %.2f\n", maxCustomer, total);
        }
    }
}
