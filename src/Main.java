import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author xwchen
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            long[] a = in.nextLongArray(n);

            TreeSet<Pair<Long, Integer>> pairs = new TreeSet<>();
            long[] cuts = new long[n + 1];
            long cost = 0;
            for (int i = 0; i < n; ++i) {
                cost += a[i] * a[i];
                cuts[i] = 1;
                pairs.add(new Pair<>(bestCutCost(a[i], cuts[i] + 1) - bestCutCost(a[i], cuts[i]), i));
            }
            for (int i = 0; i < k - n; ++i) {
                Pair<Long, Integer> pair = pairs.pollFirst();
                cost += pair.x;
                int id = pair.y;
                cuts[id]++;
                pairs.add(new Pair<>(bestCutCost(a[id], cuts[id] + 1) - bestCutCost(a[id], cuts[id]), id));
            }
            out.println(cost);
        }

        long bestCutCost(long x, long cut) {
            long perPile = x / cut;
            long r = x % cut;
            long cntMore = r;
            long cntLess = cut - cntMore;
            return perPile * perPile * cntLess + (perPile + 1) * (perPile + 1) * cntMore;
        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");

        public InputReader(InputStream inputStream) {
            this.reader = new BufferedReader(
                    new InputStreamReader(inputStream));
        }

        public String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
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

        public long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }

    }

    static class Pair<K, V> implements Comparable<Pair> {
        public K x;
        public V y;

        public Pair(K x, V y) {
            this.x = x;
            this.y = y;
        }

        public Pair() {

        }

        public int compareTo(Pair o) {
            if (compareNumber(x, o.x) != 0) {
                return compareNumber(x, o.x);
            } else {
                return compareNumber(y, o.y);
            }
        }

        private int compareNumber(Object o1, Object o2) {
            if (o1 instanceof Integer && o2 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2);
            } else if (o1 instanceof Long && o2 instanceof Long) {
                return ((Long) o1).compareTo((Long) o2);
            } else if (o1 instanceof Double && o2 instanceof Double) {
                return ((Double) o1).compareTo((Double) o2);
            } else {
                System.out.println("Unsupported Type");
                return 0;
            }
        }

    }
}

