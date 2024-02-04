import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Lab8 {
    private static InputReader in;
    private static PrintWriter out;

    static int N;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        N = in.nextInt();
        int E = in.nextInt();
        Graph graph = new Graph();

        for (int i = 0; i < E; i++) {
            int A = in.nextInt();
            int B = in.nextInt();
            long W = in.nextLong();

            graph.addEdge(A, B, W);
        }

        int H = in.nextInt();
        ArrayList<Integer> treasureNodes = new ArrayList<Integer>();
        for (int i = 0; i < H; i++) {
            int K = in.nextInt();
            treasureNodes.add(K);
        }

        long[][] shortest = new long[N][N];

        for (int i : treasureNodes) {
            for (int j = 1; j <= N; j++) {
                shortest[i - 1][j - 1] = shortest[j - 1][i - 1] = graph.shortest(i - 1, j - 1);
            }
        }

        int Q = in.nextInt();
        int O = in.nextInt();
        while (Q-- > 0) {
            Long totalOxygenNeeded = (long) 0;

            int T = in.nextInt();
            int davePosition = 1;
            while (T-- > 0) {
                int D = in.nextInt();

                totalOxygenNeeded = totalOxygenNeeded + shortest[davePosition - 1][D - 1];
                davePosition = D;
            }
            totalOxygenNeeded = totalOxygenNeeded + shortest[davePosition - 1][0];

            out.println(O > totalOxygenNeeded ? 1 : 0);
        }

        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit
    // Exceeded caused by slow input-output (IO)
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

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}

class Graph {
    long[][] adjacencyMatrix;
    long[][] shortest;

    public Graph() {
        adjacencyMatrix = new long[Lab8.N][Lab8.N];
        shortest = new long[Lab8.N][Lab8.N];

        for (int i = 0; i < Lab8.N; i++) {
            shortest[i] = null;
        }
    }

    public void addEdge(int startNode, int toNode, long weight) {
        adjacencyMatrix[startNode - 1][toNode - 1] = adjacencyMatrix[toNode - 1][startNode - 1] = weight;
    }

    int minDistance(long dist[], Boolean sptSet[]) {
        long min = Long.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < Lab8.N; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    long[] dijkstra(long graph[][], int src) {
        long dist[] = new long[Lab8.N];
        Boolean sptSet[] = new Boolean[Lab8.N];
        for (int i = 0; i < Lab8.N; i++) {
            dist[i] = Long.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < Lab8.N - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < Lab8.N; v++)
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        return dist;
    }

    public long shortest(int startNode, int toNode) {
        if (shortest[startNode] != null) {
            return shortest[startNode][toNode];
        }

        shortest[startNode] = dijkstra(adjacencyMatrix, startNode);

        return shortest[startNode][toNode];
    }
}
