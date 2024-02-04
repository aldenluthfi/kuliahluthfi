import java.io.*;
import java.util.StringTokenizer;

public class Lab2 {
    private static InputReader in;
    private static PrintWriter out;

    static long maxOddEvenSubSum(int[] a, boolean even) {

        long max = Integer.MIN_VALUE;
        long currentMax = 0;

        for (int index = 0; index < a.length; index++) {
            if (even) {
                if (a[index] % 2 == 0) {
                    currentMax += a[index];
                    max = Math.max(max, currentMax);
                } else {
                    currentMax = 0;
                }
            } else {
                if (a[index] % 2 != 0) {
                    currentMax += a[index];
                    max = Math.max(max, currentMax);
                } else {
                    currentMax = 0;
                }
            }

            if (currentMax < 0) currentMax = 0;
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        // Read value of N
        int N = in.nextInt();

        // Read value of x
        int[] x = new int[N];
        for (int i = 0; i < N; ++i) {
            x[i] = in.nextInt();
        }

        long ans = maxOddEvenSubSum(x, (N % 2 == 0));
        out.println(ans);

        // don't forget to close/flush the output
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

    }
}