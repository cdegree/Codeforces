package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeMap;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = 26;
        int[] a = in.nextIntArray(n);
        char[] s = in.nextLine().toCharArray();
        long result = 0;
        for (int i = 0; i < n; ++i) {
            result += count(s, a, (char) (i + 'a'));
        }
        out.println(result);
    }

    long count(char[] s, int[] a, char ch) {
        TreeMap<Long, Integer> count = new TreeMap<>();
        int n = s.length;
        long[] sum = new long[n + 1];
        long ret = 0;
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + a[s[i - 1] - 'a'];
            if (ch == s[i - 1]) {
                ret += count.getOrDefault(sum[i - 1], 0);
                int cnt = count.getOrDefault(sum[i], 0);
                count.put(sum[i], cnt + 1);
            }
        }
        return ret;
    }
}
