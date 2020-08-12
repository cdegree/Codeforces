import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Comparator;
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
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int T = in.nextInt();
            while (T-- > 0) {
                int n = in.nextInt();
                char[] A = in.nextLine().toCharArray();
                char[] B = in.nextLine().toCharArray();
                TreeSet<String> set = new TreeSet<>(new Comparator<String>() {

                    public int compare(String o1, String o2) {
                        if (o1.charAt(1) != o2.charAt(1)) return o1.charAt(1) - o2.charAt(1);
                        else {
                            return o1.charAt(0) - o2.charAt(0);
                        }
                    }
                });
                boolean OK = true;
                for (int i = 0; i < n; ++i) {
                    if (A[i] > B[i]) {
                        OK = false;
                    } else if (A[i] < B[i]) {
                        set.add(String.valueOf(A[i]) + String.valueOf(B[i]));
                    }
                }
                Vector<char[]> pairs = new Vector<>();
                for (String s : set) {
                    pairs.add(new char[]{s.charAt(0), s.charAt(1)});
                }
                boolean[] reachable = new boolean[22];
                int cnt = 0;
                for (int i = 0; i < pairs.size(); ++i) {
                    char[] s = pairs.get(i);
                    if (s[0] < s[1]) {
                        for (int j = i + 1; j < pairs.size(); ++j) {
                            char[] t = pairs.get(j);
                            if (t[0] == s[0] && s[1] <= t[1]) {
                                t[0] = s[1];
                            }
                        }
                        s[0] = s[1];
                        ++cnt;
                    }
//                for (int k = 0; k < pairs.size(); ++k) {
//                    System.out.println(String.format("%c %c", pairs.get(k)[0], pairs.get(k)[1]));
//                }
//                System.out.println();
                }
                for (int i = 0; i < 22; ++i) {
                    if (reachable[i]) {
                        ++cnt;
                    }
                }
                if (!OK) {
                    out.println(-1);
                } else {
                    out.println(cnt);
                }
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

