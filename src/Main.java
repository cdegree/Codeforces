import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        long total;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int T = in.nextInt();
            while (T-- > 0) {
                long m = in.nextLong();
                long d = in.nextLong();
                total = m * d;
                long w = in.nextLong();
                long min = Math.min(m, d);
                long res = 0;
                for (int i = 1; i <= min; ++i) {
                    for (int j = i + 1; j <= min; ++j) {
                        System.out.println(String.format("%d %d %% %d = %d", i, j, w, weekOfDay(i, j, d, w)));
                        System.out.println(String.format("%d %d %% %d = %d", j, i, w, weekOfDay(j, i, d, w)));
                        if (weekOfDay(i, j, d, w) == weekOfDay(j, i, d, w)) {
                            ++res;
                        }
                    }
                }
                out.println(res);
            }
        }

        long weekOfDay(long m, long d, long D, long w) {
            long sum = D * (m - 1) + d;
            if (sum > total) sum = total;
            return sum % w;
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

    }
}

