import java.io.*;
import java.util.StringTokenizer;

public class Diskonpedia {

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static Barang[] listBarang;
    private static Pembeli[] listPembeli;

    public static void main(String[] args) {                                    /* Main method                        */

        int jumlahBarang = in.nextInt();
        listBarang = new Barang[jumlahBarang];

        for (int i = 0; i < jumlahBarang; i++) {                                /* Initialisasi stok barang           */
            String namaBarang = in.next();
            long harga = in.nextLong();
            int stok = in.nextInt();

            listBarang[i] = new Barang(namaBarang, harga, stok);
        }

        int jumlahPembeli = in.nextInt();
        listPembeli = new Pembeli[jumlahPembeli];

        for (int i = 0; i < jumlahPembeli; i++) {                               /* Inisialisasi pembeli               */
            String namaPembeli = in.next();
            long jumlahUang = in.nextLong();

            listPembeli[i] = new Pembeli(namaPembeli, jumlahUang);
        }

        int jumlahPerintah = in.nextInt();                                      /* Mengambil jumlah perinat           */

        for (int i = 0; i < jumlahPerintah; i++) {                              /* Memproses masing masing perintah   */
            String perintah = in.next();
            switch (perintah) {
                case "PESAN" -> {
                    String namaPembeli = in.next();
                    String namaBarang = in.next();
                    int jumlah = in.nextInt();
                    pesan(namaPembeli, namaBarang, jumlah);
                }
                case "BAYAR" -> {
                    String namaPembeli = in.next();
                    bayar(namaPembeli);
                }
                case "DISKON" -> {
                    String namaPembeli = in.next();
                    diskon(namaPembeli);
                }
                case "RESTOCK" -> {
                    String namaBarang = in.next();
                    int jumlah = in.nextInt();
                    restock(namaBarang, jumlah);
                }
            }
        }
        out.close();
    }

    public static <T> T find(T[] array, String element) {                       /* Why not use generic types?         */
        for (T e : array) if (e != null && e.equals(element)) return e;
        return null;
    }

    public static void pesan(String customer, String namaBarang, int jumlah) {  /* Menambah pesanan                   */
        Pembeli pembeli = Diskonpedia.<Pembeli>find(listPembeli, customer);
        Barang barang = Diskonpedia.<Barang>find(listBarang, namaBarang);

        out.write(pembeli.tambahPesanan(barang, jumlah));
    }

    public static void bayar(String customer) {                                 /* Membayar pesanan                   */
        Pembeli pembeli = Diskonpedia.<Pembeli>find(listPembeli, customer);
        long total = pembeli.kumulatifHarga();
        long disc = total * pembeli.kumulatifJumlah() / 100;                    /* Menghitung potingan harga          */

        out.write("""
            \r%s berhasil melakukan pembelian barang dan pembayaran!
            \r########## Detail Pembayaran ##########%s
            \r_____________________________________
            \rTotal harga = %d
            \rDiskon = (%d)
            \rHarga bayar = %d
            \rSisa uang = %d
            \r#######################################
        """.formatted(                                                          /* Regular formatting                 */
                pembeli.getNama(),
                pembeli.pesananString(),
                pembeli.kumulatifHarga(),
                disc,
                total - disc,
                pembeli.getUang() - total + disc
            )
        );
        pembeli.setUang(pembeli.getUang() - total + disc);                      /* Mengurangin uang                   */
        pembeli.resetPesanan();
    }

    public static void restock(String namaBarang, int jumlah) {                 /* Restock barang                     */

        if (jumlah <= 0) {
            out.write("Maaf, stok tambahan yang dimasukkan tidak valid\n");
            return;
        }

        Barang barang = Diskonpedia.<Barang>find(listBarang, namaBarang);
        barang.setStok(barang.getStok() + jumlah);

        out.write(
            ("Berhasil menambahkan stok barang %s." +
            " Sisa stok sekarang = %d\n")
            .formatted(barang.getNama(), barang.getStok())
        );
    }

    public static void diskon(String customer) {                                /* menghandle diskon                  */
        Pembeli pembeli = Diskonpedia.<Pembeli>find(listPembeli, customer);

        out.write(
            "%s mendapatkan diskon sebesar %d%%\n"
            .formatted(customer, pembeli.kumulatifJumlah())
        );
    }

    static class InputReader {                                                  /* CP level input for some reason...  */
        public BufferedReader reader;                                           /* A bit overkill...                  */
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
