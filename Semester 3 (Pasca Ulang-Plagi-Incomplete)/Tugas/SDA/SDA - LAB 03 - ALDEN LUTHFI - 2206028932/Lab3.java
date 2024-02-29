import java.io.*;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ArrayDeque;

public class Lab3 {
    private static InputReader in;
    private static PrintWriter out;

    // Read input
    private static long T;
    private static long X;
    private static long C;
    private static long Q;

    private static ArrayDeque<LinkedList<Integer>> lantai = new ArrayDeque<LinkedList<Integer>>();

    private static boolean arah = true;

    // Metode GA
    static String GA() {
        if (arah) {
            arah = false;
            return "KIRI";
        } else {
            arah = true;
            return "KANAN";
        }
    }

    // Metode S
    static long S(int Si){
        if (lantai == null || lantai.isEmpty()) return -1;
        LinkedList<Integer> gedung = lantai.pollFirst();

        long poin = 0;

        for (int i = 0; i < Si && !gedung.isEmpty(); i++) {
            int tambahan = gedung.pop();
            poin += tambahan;
            T -= tambahan;
        }

        if (T <= 0 || (lantai.isEmpty() && gedung.isEmpty())) return -1;

        if (arah) {
            if (gedung.size() > 0) lantai.addLast(gedung);
        } else {
            if (gedung.size() > 0) lantai.addFirst(gedung);
            lantai.addFirst(lantai.pollLast());
        }

        return poin;
    }

    // Template
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        T = in.nextLong();
        X = in.nextInt();
        C = in.nextInt();
        Q = in.nextInt();

        for (int i = 0; i < X; i++) {
            LinkedList<Integer> gedung = new LinkedList<Integer>();
            lantai.add(gedung);

            for (int j = 0; j < C; j++) {
                int Ci = in.nextInt();
                gedung.add(Ci);
            }
        }

        // Process the query
        for (int k = 0; k < Q; k++) {
            String perintah = in.next();
            if (perintah.equals("GA")) {
                out.println(GA());
            } else if (perintah.equals("S")) {
                int Si = in.nextInt();
                long poin = S(Si);

                if (poin == -1) {
                    out.println("MENANG");
                    continue;
                }

                out.println(poin);
            }
        }

        out.close();
    }
    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
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

        public long nextLong(){
            return Long.parseLong(next());
        }

    }
}