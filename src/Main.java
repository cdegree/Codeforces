import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.LinkedList;
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
                TreeMap<Integer, Integer> primeDivisors = MathUtil.getPrimeDivisors(n);
                ArrayList<Integer> divisors = MathUtil.getDivisors(n);
                Utils.pf("divisors = %d primeD = %d", divisors.size(), primeDivisors.size());
                LinkedList<Integer>[] adj = new LinkedList[divisors.size()];
                for (int i = 0; i < adj.length; ++i) adj[i] = new LinkedList<>();
                for (int prime : primeDivisors.keySet()) {
                    boolean[] con = new boolean[divisors.size()];
                    for (int i = 0; i < divisors.size(); ++i) {
                        if (divisors.get(i) % prime == 0) {
                            con[i] = true;
                        }
                    }
                    for (int i = 0; i < divisors.size(); ++i) {
                        if (con[i]) {
                            for (int j = i + 1; j < divisors.size(); ++j) {
                                if (con[j]) {
                                    adj[i].addLast(j);
                                    adj[j].addLast(i);
                                }
                            }
                        }
                    }
                }


            }
        }

    }

    static class MathUtil {
        public static ArrayList<Integer> getDivisors(int n) {
            ArrayList<Integer> r = new ArrayList<>();
            for (int i = 1; i * i <= n; ++i) {
                if (n % i == 0) {
                    r.add(i);
                    if (n / i != i) {
                        r.add((n / i));
                    }
                }
            }

            return r;
        }

        public static TreeMap<Integer, Integer> getPrimeDivisors(int n) {
            TreeMap<Integer, Integer> r = new TreeMap<>();
            for (int i = 2; (long) i * i <= n; ++i) {
                if (n % i == 0) {
                    r.put(i, 0);
                    int cnt = 0;
                    while (n % i == 0) {
                        ++cnt;
                        n /= i;
                    }
                    r.put(i, cnt);
                }
            }
            if (n > 1) {
                r.put(n, 1);
            }
            return r;
        }

    }

    static class Utils {
        public static void pf(String format, Object... obj) {
            System.out.println(String.format(format, obj));
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

    }
}

