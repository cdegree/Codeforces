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
            int tt = in.nextInt();
            while (tt-- > 0) {
                int n = in.nextInt();
                int k = in.nextInt();
                StringBuilder ans = new StringBuilder("-1");
                for (int lastDigit = 0; lastDigit <= 9; ++lastDigit) {
                    int cnt9Max = n / 9;
                    if (lastDigit + k < 10) {
                        cnt9Max = 0;
                    }
                    for (int cnt9 = 0; cnt9 <= cnt9Max; ++cnt9) {
                        StringBuilder cur = new StringBuilder("");
                        cur.append(lastDigit);
                        for (int i = 0; i < cnt9; ++i) cur.append(9);
                        int sum = 0;
                        for (int i = 0; i <= k; ++i) {
                            int j = lastDigit + i;
                            if (j <= 9) {
                                sum += j + cnt9 * 9;
                            } else {
                                sum += j % 10 + j / 10;
                            }
                        }
                        int need = n - sum;
                        if (need < 0 || need % (k + 1) != 0) continue;
                        need /= (k + 1);
                        int maxNum = 9;
                        if (lastDigit + k >= 10) {
                            maxNum = 8;
                        }
                        while (need > 0) {
                            int d = Math.min(maxNum, need);
                            maxNum = 9;
                            need -= d;
                            cur.append(d);
                        }
                        cur.reverse();
                        updateAns(ans, cur);
                    }
                }
                out.println(ans);
            }
        }

        boolean updateAns(StringBuilder ans, StringBuilder cur) {
            if (ans.charAt(0) == '-') {
                ans.delete(0, ans.length());
                ans.append(cur);
                return true;
            } else {
                if (ans.length() > cur.length()) {
                    ans.delete(0, ans.length());
                    ans.append(cur);
                    return true;
                } else if (ans.length() == cur.length()) {
                    for (int i = 0; i < ans.length(); ++i) {
                        if (ans.charAt(i) > cur.charAt(i)) {
                            ans.delete(0, ans.length());
                            ans.append(cur);
                            return true;
                        } else if (ans.charAt(i) < cur.charAt(i)) {
                            return false;
                        }
                    }
                    return false;
                } else {
                    return false;
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

