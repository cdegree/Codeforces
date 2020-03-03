import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            long[][] a = new long[n][n];
            for (int i = 0; i < n; ++i) {
                a[i] = in.nextLongArray(n);
            }
            if (n == 1) {
                out.println(1);
                return;
            }
            long sum = 0;
            for (int i = 0; i < n; ++i) {
                boolean OK = true;
                for (int j = 0; j < n; ++j) {
                    if (a[i][j] == 0) {
                        OK = false;
                        break;
                    }
                }
                if (OK) {
                    for (int j = 0; j < n; ++j) {
                        sum += a[i][j];
                    }
                    break;
                }
            }
            long target = sum;
            long res = -1;
            for (int i = 0; i < n; ++i) {
                boolean OK = true;
                int idx = -1;
                for (int j = 0; j < n; ++j) {
                    if (a[i][j] == 0) {
                        OK = false;
                        idx = j;
                        break;
                    }
                }
                if (!OK) {
                    for (int j = 0; j < n; ++j) {
                        if (j != idx) {
                            sum -= a[i][j];
                        }
                    }
                    res = a[i][idx] = sum;
                }
            }
            boolean OK = true;
            for (int i = 0; i < n; ++i) {
                sum = 0;
                for (int j = 0; j < n; ++j) sum += a[i][j];
                if (sum != target) OK = false;
            }
            for (int j = 0; j < n; ++j) {
                sum = 0;
                for (int i = 0; i < n; ++i) sum += a[i][j];
                if (sum != target) OK = false;
            }
            sum = 0;
            for (int i = 0; i < n; ++i) {
                sum += a[i][i];
            }
            if (sum != target) OK = false;
            sum = 0;
            for (int i = 0; i < n; ++i) {
                sum += a[i][n - i - 1];
            }

            if (sum != target) OK = false;
            if (res <= 0) OK = false;
            out.println(OK ? res : -1);

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
}

