import java.util.Scanner;

public class PangkatFactorial {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            
            System.out.print("masukkan bilangan utama (n): ");
            int n = input.nextInt();
            System.out.print("masukkan pemangkatan (m): ");
            int m = input.nextInt();

            int hasilFact = 1, hasilPangkat = 1, tempN = n;

            while (tempN > 0) {
                hasilFact *= tempN--;
            }

            for (int i = 0; i++ < m;) {
                hasilPangkat *= n;
            }

            System.out.printf("n pangkat m = %d\n", hasilPangkat);
            System.out.printf("n factorial = %d\n", hasilFact);
        }
    }
}