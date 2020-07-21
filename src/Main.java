import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            int T = in.nextInt();
            while (T-- > 0) {
                int n = in.nextInt();
                int m = n + n;
                int[] p = in.nextIntArray(m);
                Vector<Integer> cnt = new Vector<>();
                for (int i = 0; i < m; ++i) {
                    int j = i;
                    while (j + 1 < m && p[j + 1] < p[i]) ++j;
                    cnt.add(j - i + 1);
                    i = j;
                }
                boolean[] available = new boolean[n + 1];
                available[0] = true;
                for (int i = 0; i < cnt.size(); ++i) {
                    for (int j = n; j - cnt.get(i) >= 0; --j) {
                        available[j] = available[j] | available[j - cnt.get(i)];
                    }
                }
                out.println(available[n] ? "YES" : "NO");
            }
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

        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

    }
}

