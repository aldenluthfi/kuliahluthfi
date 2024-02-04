import java.io.BufferedReader;                                                  /* Import buat I/O                    */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import java.util.ArrayDeque;                                                    /* Struktur data yang digunakan       */
import java.util.PriorityQueue;                                                 /* adalah priority queue dan deque    */

public class TP1 {

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------------------------------- FIELDS ------------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

    private static InputReader in;                                              /* Input                              */
    private static PrintWriter out;                                             /* Output                             */

    private static Wahana[] rides;                                              /* List wahana yang ada               */
    private static Pengunjung[] visitors;                                       /* List pengunjung yang ada           */
    private static ArrayDeque<Pengunjung> outList;

    private static O[][][] profits;                                             /* DP table untuk query O             */
    private static int[] costs;                                                 /* List harga wahana dengan index id-1*/
    private static int[] points;                                                /* List poin wahana dengan index id-1 */

    private static int CHEAPEST = Integer.MAX_VALUE;                            /* Harga wahana termurah              */
    private static int RICHEST = Integer.MIN_VALUE;                             /* Pengunjung dengan uang terbanyak   */

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|--------------------------------------------------- HELPER CLASSES ---------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

    static class O {                                                            /* Kelas solusi untuk query O         */
        public int points;                                                      /* Poin yang telah dicapai sejauh ini */
        public int cost;                                                        /* biaya yang dibutuhkan sejauh ini   */
        public ArrayDeque<Integer> rides;                                       /* wahana yang telah dimasukkan       */

        public O() {                                                            /* Constructor untuk solusi kosong    */
            points = 0;
            cost = 0;
            rides = new ArrayDeque<>();
        }

        public O(O other) {                                                     /* Constructor untuk copy             */
            points = other.points;
            cost = other.cost;
            rides = new ArrayDeque<>(other.rides);
        }

        public O add(int points, int cost, int i) {                             /* Memperbaharui solusi               */
            this.points += points;
            this.cost += cost;
            this.rides.addLast(i);
            return new O(this);
        }

        public static O max(O a, O b) {                                         /* Mencari maksimum antara 2 solusi   */

            if (a.points != b.points)                                           /* berdasarkan poin                   */
                return a.points > b.points ? a : b;                             /* poin lebih besar                   */
            if (a.cost != b.cost)                                               /* berdasarkan biaya                  */
                return a.cost < b.cost ? a : b;                                 /* biaya lebih kecil                  */

            ArrayDeque<Integer> aRides = new ArrayDeque<>(a.rides);
            ArrayDeque<Integer> bRides = new ArrayDeque<>(b.rides);

            while (!aRides.isEmpty() && !bRides.isEmpty()) {                    /* berdasarkan urutan wahana          */
                int aWahanaId = aRides.pollFirst();
                int bWahanaId = bRides.pollFirst();
                if (aWahanaId != bWahanaId)                                     /* urutan leksikografis               */
                    return aWahanaId < bWahanaId ? a : b;                       /* lebih kecil                        */
            }

            return a;
        }

        @Override
        public String toString() {                                              /* Override toString untuk output     */
            String result = points + "";                                        /* Poin                               */

            for (int ride : rides)                                              /* Wahana yang dikunjungi             */
                result += " " + ride;

            return result;
        }
    }

    static class Wahana {                                                       /* Kelas wahana                       */
        private static int idCounter = 1;

        public PriorityQueue<Calo> antrianPrioritas;                            /* Antrian pengunjung prioritas       */
        public PriorityQueue<Calo> antrian;                                     /* Antrian pengunjung biasa           */
        public int harga;                                                       /* Harga wahana                       */
        public int poin;                                                        /* Poin yang didapat dari wahana      */
        public int kapasitas;                                                   /* Kapasitas wahana                   */
        public int fastTrack;                                                   /* Jumlah fast track                  */
        public int id;                                                          /* ID wahana                          */

        public Wahana(int hi, int pi, int kpi, int fti) {                       /* Constructor wahana                 */
            id = idCounter++;                                                   /* ID wahana                          */

            antrianPrioritas = new PriorityQueue<>();                           /* Inisialisasi antrian               */
            antrian = new PriorityQueue<>();                                    /* pengunjung                         */

            harga = hi;                                                         /* Inisialisasi harga                 */
            poin = pi;                                                          /* Poin                               */
            kapasitas = kpi;                                                    /* Kapasitas                          */
            fastTrack = (int) Math.ceil(fti * kpi / 100.0);                     /* Kuota fastrack                     */
        }

        public int antri(Pengunjung pengunjung) {                               /* Method untuk antri pengunjung      */
            if (pengunjung.uang < CHEAPEST)                                     /* Jika uang pengunjung kurang dari   */
                return -1;                                                      /* harga wahana termurah              */
            if (pengunjung.uang < harga)                                        /* atau kurang dari harga wahana ini  */
                return -1;                                                      /* maka pengunjung tidak bisa antri   */
            if (pengunjung.isOut)                                               /* Jika pengunjung sudah out          */
                return -1;                                                      /* maka pengunjung tidak bisa antri   */

            if (pengunjung.fastTrack) {                                         /* Jika pengunjung fast track         */
                antrianPrioritas.add(new Calo(pengunjung));                     /* masukkan ke antrian prioritas      */
            } else {                                                            /* Jika pengunjung biasa              */
                antrian.add(new Calo(pengunjung));                              /* masukkan ke antrian biasa          */
            }

            return antrianPrioritas.size() + antrian.size();                    /* Jumlah pengunjung yang antri       */
        }

    }

    static class Calo implements Comparable<Calo> {                             /* Kelas calo (namanya calo karena    */
        public int id;                                                          /* cuman dipake untuk ngantri)        */
        public int totalBermain;

        public Calo(Pengunjung pengunjung) {                                    /* Constructor calo                   */
            id = pengunjung.id;
            totalBermain = pengunjung.totalBermain;
        }

        @Override
        public int compareTo(Calo o) {                                          /* Override compareTo untuk sorting   */
            int mainCompare = Integer.compare(totalBermain, o.totalBermain);    /* berdasarkan jumlah bermain         */

            if (mainCompare != 0)                                               /* Jika jumlah bermain berbeda        */
                return mainCompare;

            return Integer.compare(id, o.id);                                   /* Urutkan berdasarkan ID             */
        }
    }

    static class Pengunjung {                                                   /* Kelas pengunjung                   */

        private static int idCounter = 1;

        public int id;                                                          /* ID pengunjung                      */
        public int uang;                                                        /* Uang pengunjung                    */
        public int totalBermain;                                                /* Jumlah wahana yang telah dimainkan */
        public long poin;                                                       /* Poin yang telah didapat            */
        public boolean fastTrack;                                               /* Apakah pengunjung fast track       */
        public boolean isOut;                                                   /* Apakah pengunjung sudah out        */

        public Pengunjung(int uang, String fastTrack) {                         /* Constructor pengunjung             */
            id = idCounter++;

            this.fastTrack = fastTrack.equals("FT");                            /* Inisialisasi fast track            */
            this.uang = uang;                                                   /* Inisialisasi uang                  */
            this.poin = 0;                                                      /* Inisialisasi poin                  */
            this.isOut = false;                                                 /* Inisialisasi isOut                 */
            this.totalBermain = 0;                                              /* Inisialisasi totalBermain          */
        }
    }

    static class InputReader {                                                  /* Kelas untuk input                  */
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {                                                  /* Method untuk membaca token         */
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {                                                  /* Method untuk membaca integer       */
            return Integer.parseInt(next());
        }
    }

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|-------------------------------------------------- QUERY FUNCTIONS ---------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

    private static int A(int idPengunjung, int idWahana) {                      /* Method untuk query A               */
        return rides[idWahana - 1].antri(visitors[idPengunjung - 1]);           /* Panggil method antri               */
    }

    private static String E(int idWahana) {                                     /* Method untuk query E               */
        PriorityQueue<Calo> prior, biasa;                                       /* Inisialisasi antrian               */
        Wahana wahana = rides[idWahana - 1];                                    /* Inisialisasi wahana                */

        Pengunjung pengunjung = null;                                           /* Inisialisasi pengunjung            */
        String result = "";                                                     /* Inisialisasi hasil                 */

        prior = wahana.antrianPrioritas;                                        /* Inisialisasi antrian prioritas     */
        biasa = wahana.antrian;                                                 /* Inisialisasi antrian biasa         */

        if (prior.isEmpty() && biasa.isEmpty())                                 /* Jika antrian kosong                */
            return "-1";                                                        /* maka return -1                     */

        int i = 0;
        first: for (; i < wahana.fastTrack && !prior.isEmpty(); i++) {          /* Ambil pengunjung dari antrian      */
            do {                                                                /* prioritas                          */
                if (prior.isEmpty())                                            /* Jika antrian kosong                */
                    break first;                                                /* maka break                         */

                pengunjung = visitors[prior.poll().id - 1];
            } while (pengunjung.isOut || pengunjung.uang < wahana.harga);

            pengunjung.totalBermain++;                                          /* Jika pengunjung bisa main          */
            pengunjung.poin += wahana.poin;                                     /* maka update totalBermain, poin     */
            pengunjung.uang -= wahana.harga;                                    /* dan uang pengunjung                */
            result += pengunjung.id + " ";                                      /* Tambahkan ID pengunjung ke hasil   */

            if (pengunjung.uang <= 0 || pengunjung.uang < CHEAPEST) {           /* Jika uang pengunjung habis         */
                pengunjung.isOut = true;                                        /* atau kurang dari harga wahana      */
                if (pengunjung.uang <= 0)                                       /* termurah                           */
                    outList.add(pengunjung);                                    /* maka pengunjung out                */
            }
        }

        second: for (; i < wahana.kapasitas && !biasa.isEmpty(); i++) {         /* Ambil pengunjung dari antrian      */
            do {                                                                /* biasa                              */
                if (biasa.isEmpty())                                            /* Jika antrian kosong                */
                    break second;                                               /* maka break                         */

                pengunjung = visitors[biasa.poll().id - 1];
            } while (pengunjung.isOut || pengunjung.uang < wahana.harga);

            pengunjung.totalBermain++;                                          /* Jika pengunjung bisa main          */
            pengunjung.poin += wahana.poin;                                     /* maka update totalBermain, poin     */
            pengunjung.uang -= wahana.harga;                                    /* dan uang pengunjung                */
            result += pengunjung.id + " ";                                      /* Tambahkan ID pengunjung ke hasil   */

            if (pengunjung.uang <= 0 || pengunjung.uang < CHEAPEST) {           /* Jika uang pengunjung habis         */
                pengunjung.isOut = true;                                        /* atau kurang dari harga wahana      */
                if (pengunjung.uang <= 0)                                       /* termurah                           */
                    outList.add(pengunjung);                                    /* maka pengunjung out                */
            }
        }

        third: for (; i < wahana.kapasitas && !prior.isEmpty(); i++) {          /* Ambil pengunjung dari antrian      */
            do {                                                                /* prioritas                          */
                if (prior.isEmpty())                                            /* Jika antrian kosong                */
                    break third;                                                /* maka break                         */

                pengunjung = visitors[prior.poll().id - 1];
            } while (pengunjung.isOut || pengunjung.uang < wahana.harga);

            pengunjung.totalBermain++;                                          /* Jika pengunjung bisa main          */
            pengunjung.poin += wahana.poin;                                     /* maka update totalBermain, poin     */
            pengunjung.uang -= wahana.harga;                                    /* dan uang pengunjung                */
            result += pengunjung.id + " ";                                      /* Tambahkan ID pengunjung ke hasil   */

            if (pengunjung.uang <= 0 || pengunjung.uang < CHEAPEST) {           /* Jika uang pengunjung habis         */
                pengunjung.isOut = true;                                        /* atau kurang dari harga wahana      */
                if (pengunjung.uang <= 0)                                       /* termurah                           */
                    outList.add(pengunjung);                                    /* maka pengunjung out                */
            }
        }

        return result.isEmpty() ? "-1" : result;                                /* Jika tidak ada pengunjung yang     */
    }                                                                           /* bisa main, maka return -1          */

    private static int S(int idPengunjung, int idWahana) {                      /* Method untuk query S               */
        PriorityQueue<Calo> prior, biasa;                                       /* Inisialisasi antrian               */

        Wahana wahana = rides[idWahana - 1];                                    /* Inisialisasi wahana                */
        Pengunjung pengunjung = visitors[idPengunjung - 1];                     /* Inisialisasi pengunjung            */

        prior = wahana.antrianPrioritas;                                        /* Inisialisasi antrian prioritas     */
        biasa = wahana.antrian;                                                 /* Inisialisasi antrian biasa         */

        PriorityQueue<Calo> prior2 = new PriorityQueue<>(prior);                /* Copy antrian                       */
        PriorityQueue<Calo> biasa2 = new PriorityQueue<>(biasa);                /* untuk simulasi                     */

        if (pengunjung.isOut || pengunjung.uang < wahana.harga)                 /* Jika pengunjung tidak bisa main    */
            return -1;                                                          /* maka return -1                     */

        int index = 1;                                                          /* Inisialisasi index                 */
        while (!prior2.isEmpty() || !biasa2.isEmpty()) {                        /* Selama antrian tidak kosong        */
            int j = 0;
            first: for (; j < wahana.fastTrack && !prior2.isEmpty(); j++) {     /* Ambil pengunjung dari antrian      */
                do {                                                            /* prioritas                          */
                    if (prior2.isEmpty())
                        break first;

                    pengunjung = visitors[prior2.poll().id - 1];
                } while (pengunjung.isOut || pengunjung.uang < wahana.harga);

                if (idPengunjung == pengunjung.id)                              /* Jika pengunjung ditemukan          */
                    return index;                                               /* maka return index                  */

                index++;
            }

            second: for (; j < wahana.kapasitas && !biasa2.isEmpty(); j++) {    /* Ambil pengunjung dari antrian      */
                do {                                                            /* biasa                              */
                    if (biasa2.isEmpty())
                        break second;

                    pengunjung = visitors[biasa2.poll().id - 1];
                } while (pengunjung.isOut || pengunjung.uang < wahana.harga);

                if (idPengunjung == pengunjung.id)                              /* Jika pengunjung ditemukan          */
                    return index;                                               /* maka return index                  */

                index++;
            }
        }

        return -1;
    }

    private static long F(int p) {
        if (outList.isEmpty())                                                  /* Jika tidak ada pengunjung yang     */
            return -1;                                                          /* out, maka return -1                */

        return p == 0 ? outList.pollFirst().poin : outList.pollLast().poin;     /* yang diambil depan atau belakang   */
    }

    private static O O(int idPengunjung) {                                      /* Method untuk query O               */
        Pengunjung pengunjung = visitors[idPengunjung - 1];                     /* Inisialisasi pengunjung            */

        if (pengunjung.isOut || pengunjung.uang < CHEAPEST)                     /* Jika pengunjung tidak bisa main    */
            return new O();                                                     /* maka return solusi kosong          */

        int M = rides.length;                                                   /* Inisialisasi M                     */
        int uang = pengunjung.uang;                                             /* Inisialisasi uang                  */

        if (profits != null) {                                                  /* Jika sudah pernah dipanggil        */
            return O.max(profits[M][uang][0], profits[M][uang][1]);             /* maka return solusi yang tersimpan  */
        }

        profits = new O[M + 1][RICHEST + 1][2];                                 /* Inisialisasi DP table              */

        for (int j = 0; j <= RICHEST; j++) {                                    /* Inisialisasi baris 0               */
            profits[0][j][0] = new O();
            profits[0][j][1] = new O();
        }

        for (int i = 1; i <= M; i++) {                                          /* Inisialisasi kolom 0               */
            for (int j = 0; j <= RICHEST; j++) {                                /* dengan solusi kosong               */
                profits[i][j][i + 1 & 1] = new O(profits[i - 1][j][i + 1 & 1]); /* dan kolom 1 dengan solusi kolom 0  */

                if (j < costs[i - 1]) {                                         /* Jika uang kurang dari harga wahana */
                    profits[i][j][i & 1] = profits[i - 1][j][i & 1];            /* maka solusi sama dengan solusi     */
                } else {                                                        /* kolom sebelumnya                  */
                    O a, b;                                                     /* Jika uang cukup                    */

                    a = profits[i - 1][j][i & 1];                               /* maka solusi adalah                 */
                    b = profits[i - 1][j - costs[i - 1]][i + 1 & 1];            /* solusi kolom sebelumnya            */
                    b = b.add(points[i - 1], costs[i - 1], i);                  /* ditambah poin dan biaya wahana     */

                    profits[i][j][i & 1] = O.max(a, b);                         /* yang sedang diproses               */
                }
            }
        }

        return O.max(profits[M][uang][0], profits[M][uang][1]);                 /* Return solusi terbaik              */
    }

/*========================================================***=========================================================*\
->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-

|--------------------------------------------------- MAIN FUNCTION ----------------------------------------------------|

->->->->->->->->->->->->->->->->->->->->->->->->->->->->->-><-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-<-
\*====================================================================================================================*/

    public static void main(String[] args) {                                    /* Main program                       */
        InputStream inputStream = System.in;                                    /* Inisialisasi input                 */
        in = new InputReader(inputStream);                                      /* dan output                         */
        OutputStream outputStream = System.out;                                 /* dari stdin dan stdout              */
        out = new PrintWriter(outputStream);                                    /* ke file                            */

        int M = in.nextInt();                                                   /* Baca input                         */

        rides = new Wahana[M];                                                  /* Inisialisasi wahana                */
        costs = new int[M];                                                     /* Inisialisasi harga                 */
        points = new int[M];                                                    /* Inisialisasi poin                  */
        for (int m = 0; m < M; m++) {
            int hi = in.nextInt();
            int pi = in.nextInt();
            int kpi = in.nextInt();
            int fti = in.nextInt();

            if (hi < CHEAPEST)                                                  /* Cari harga wahana termurah         */
                CHEAPEST = hi;

            rides[m] = new Wahana(hi, pi, kpi, fti);
            costs[m] = hi;
            points[m] = pi;
        }

        int N = in.nextInt();                                                   /* Inisialisasi pengunjung            */

        visitors = new Pengunjung[N];                                           /* Inisialisasi pengunjung            */
        for (int n = 0; n < N; n++) {
            String ti = in.next();
            int ui = in.nextInt();

            if (ui > RICHEST)                                                   /* Cari pengunjung dengan uang        */
                RICHEST = ui;                                                   /* terbanyak                          */

            visitors[n] = new Pengunjung(ui, ti);
        }

        outList = new ArrayDeque<Pengunjung>();                                 /* Inisialisasi list pengunjung out   */

        int T = in.nextInt();                                                   /* Inisialisasi jumlah query          */

        while (T-- > 0) {                                                       /* Loop untuk query                   */
            String aktivitas = in.next();
            int idPengunjung, idWahana, p;

            command: switch (aktivitas) {                                       /* Switch case untuk query            */
                case "A": {                                                     /* Panggil method A                   */
                    idPengunjung = in.nextInt();
                    idWahana = in.nextInt();
                    out.println(A(idPengunjung, idWahana));
                    break command;
                }

                case "E": {                                                     /* Panggil method E                   */
                    idWahana = in.nextInt();
                    out.println(E(idWahana));
                    break command;
                }

                case "S": {                                                     /* Panggil method S                   */
                    idPengunjung = in.nextInt();
                    idWahana = in.nextInt();
                    out.println(S(idPengunjung, idWahana));
                    break command;
                }

                case "F": {                                                     /* Panggil method F                   */
                    p = in.nextInt();
                    out.println(F(p));
                    break command;
                }

                case "O": {                                                     /* Panggil method O                   */
                    idPengunjung = in.nextInt();
                    out.println(O(idPengunjung));
                    break command;
                }
            }
        }
        out.close();
    }
}