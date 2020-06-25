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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            char[] t = in.nextLine().toCharArray();
            int[] mark = new int[n];
            for (int i = 0; i < n; ++i) {
                if (s[i] != t[i]) {
                    mark[i] = (s[i] == '0') ? 1 : 2;
                }
            }
            int cnt1 = 0;
            int cnt2 = 0;
            for (int i = 0; i < n; ++i) {
                if (mark[i] == 1) ++cnt1;
                if (mark[i] == 2) ++cnt2;
            }
            if (cnt1 != cnt2) {
                out.println(-1);
            } else {
                int n1 = 0;
                int n2 = 0;
                int res = 0;
                for (int i = 0; i < n; ++i) {
                    if (mark[i] == 1) {
                        if (n2 > 0) {
                            --n2;
                            ++n1;
                        } else {
                            ++n1;
                        }
                    } else if (mark[i] == 2) {
                        if (n1 > 0) {
                            --n1;
                            ++n2;
                        } else {
                            ++n2;
                        }
                    }
                    res = Math.max(res, n1 + n2);
                }
                out.println(res);
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

        public String nextLine() {
            String ret = "";
            try {
                ret = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

