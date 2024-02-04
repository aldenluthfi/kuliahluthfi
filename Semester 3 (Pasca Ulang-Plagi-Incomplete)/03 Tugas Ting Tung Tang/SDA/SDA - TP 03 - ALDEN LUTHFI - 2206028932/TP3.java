import java.io.*;
import java.util.*;

class TP3 {

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------------ MEDAN -----------------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    private static Pencatatan catat;
    private static PrintWriter cetak;

    static int V, E, A, B, Q, H;
    static long N;
    static Labirin labirin;

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------ STRUKTUR DATA ---------------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    static class Timbunan {
        private long[] timbunan;
        private int ukuran;

        Timbunan() {
            this.ukuran = 0;

            timbunan = new long[V + 1];
            timbunan[0] = Long.MIN_VALUE;
        }

        void masuk(long elemen) {
            timbunan[++ukuran] = elemen;
            int unjuk = ukuran;

            if (ukuran >= timbunan.length - 1)
                timbunan = Arrays.copyOf(timbunan, timbunan.length << 1);

            long bocah = timbunan[unjuk] & 0x000000FFFFFFFFL;
            long sepuh = timbunan[unjuk >>> 1] & 0xFFFFFFFFL;
            long tukar;

            while (bocah < sepuh) {
                tukar = timbunan[unjuk];

                timbunan[unjuk] = timbunan[unjuk >>> 1];
                timbunan[unjuk >>> 1] = tukar;

                unjuk = unjuk >>> 1;

                bocah = timbunan[unjuk] & 0x000000FFFFFFFFL;
                sepuh = timbunan[unjuk >>> 1] & 0xFFFFFFFFL;
            }
        }

        long pilih() {
            long keluar = timbunan[1], tukar;
            timbunan[1] = timbunan[ukuran--];

            int esa = 1;

            while (esa <= ukuran >>> 1) {
                int dwi = esa;

                long kidal = timbunan[esa << 1] & 0x000FFFFFFFFL;
                long kanan = timbunan[2 * esa + 1] & 0xFFFFFFFFL;

                if (2 * esa + 1 <= ukuran)
                    dwi = kidal < kanan ? esa << 1 : 2 * esa + 1;
                else
                    dwi = esa << 1;

                if (timbunan[esa] > kidal || timbunan[esa] > kanan) {
                    tukar = timbunan[esa];

                    timbunan[esa] = timbunan[dwi];
                    timbunan[dwi] = tukar;
                }

                esa = dwi;
            }

            return keluar;
        }
    }

    static class Labirin {
        int[] indeks;
        int[] hadiah;

        long[][] kekang;
        long[][] pintas;

        Timbunan antre;

        Labirin() {
            kekang = new long[V][V - 1];
            pintas = new long[V][V];

            hadiah = new int[100];
            indeks = new int[V];

            Arrays.fill(hadiah, -1);
            Arrays.fill(pintas, null);

            for (int v = 0; v < V; v++) {
                Arrays.fill(kekang[v], -1);
            }

            antre = new Timbunan();
        }

        long[] dijkstra(int verteks) {
            long[] jarak = new long[V];

            for (int v = 0; v < V; v++)
                jarak[v] = Long.MAX_VALUE;

            antre.masuk((long) verteks << 32L | 0L);
            jarak[verteks] = 0;

            while (antre.ukuran != 0) {
                int x = (int) (antre.pilih() >>> 32);

                for (long y : kekang[x]) {
                    if (y == -1)
                        break;

                    int simpul = (int) (y >>> 32);

                    long bobot = y & 0x0000000000FFFFFFFFL;
                    long sulit = Math.max(jarak[x], bobot);

                    if (jarak[simpul] > sulit) {
                        jarak[simpul] = sulit;
                        antre.masuk((long) simpul << 32L | jarak[simpul]);
                    }
                }
            }

            return jarak;
        }
    }

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|------------------------------- MASUKAN/KELUARAN -----------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    static class Pencatatan {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public Pencatatan(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
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
    }

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|---------------------------------- PERINTAH ----------------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    static int M() {

        int hitung = 0;
        long korban = Long.parseLong(catat.next());

        for (int h : labirin.hadiah) if (h == -1)
            break;
        else if (labirin.pintas[h][0] <= korban)
            hitung++;

        return hitung;
    }

    static long S() {
        long hitung = Long.MAX_VALUE;
        int mulai = Integer.parseInt(catat.next()) - 1;

        for (int h : labirin.hadiah) if (h == -1)
            break;
        else if (labirin.pintas[h][mulai] <= hitung)
            hitung = labirin.pintas[h][mulai];

        return hitung;
    }

    static char T() {

        int esa = Integer.parseInt(catat.next()) - 1;
        int dwi = Integer.parseInt(catat.next()) - 1;
        int tri = Integer.parseInt(catat.next()) - 1;
        long korban = Long.parseLong(catat.next());

        if (labirin.pintas[esa] == null)
            labirin.pintas[esa] = labirin.dijkstra(esa);

        if (labirin.pintas[dwi] == null)
            labirin.pintas[dwi] = labirin.dijkstra(dwi);

        if (labirin.pintas[esa][dwi] > korban)
            return 'N';
        if (labirin.pintas[dwi][tri] > korban)
            return 'H';

        return 'Y';
    }

/*====================================***=====================================*\
->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------- FUNGSI UTAMA --------------------------------|

->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*============================================================================*/

    public static void main(String[] args) {
        catat = new Pencatatan(System.in);
        cetak = new PrintWriter(System.out);

        V = Integer.parseInt(catat.next());
        E = Integer.parseInt(catat.next());

        labirin = new Labirin();

        for (int v = 0; v < V; v++)
            if (catat.next().equals("S"))
                labirin.hadiah[H++] = v;

        for (int e = 0; e < E; e++) {
            A = Integer.parseInt(catat.next()) - 1;
            B = Integer.parseInt(catat.next()) - 1;
            N = Long.parseLong(catat.next());

            labirin.kekang[A][labirin.indeks[A]++] = (long) B << 32L | N;
            labirin.kekang[B][labirin.indeks[B]++] = (long) A << 32L | N;
        }

        for (int h : labirin.hadiah) if (h == -1)
            break;
        else
            labirin.pintas[h] = labirin.dijkstra(h);

        Q = Integer.parseInt(catat.next());

        for (int q = 0; q < Q; q++) {
            String perintah = catat.next();

            if (perintah.equals("M"))
                cetak.println(M());
            else if (perintah.equals("S"))
                cetak.println(S());
            else if (perintah.equals("T"))
                cetak.println(T());
        }

        cetak.close();
    }
}