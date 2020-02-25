package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            int[] p = in.nextIntArray(m);
            int[] psum = new int[n + 1];
            long[] press = new long[26];
            long[] cnt = new long[n + 1];
            for (int i = 0; i < m; ++i) {
                psum[p[i]]++;
            }
            long cur = 1;
            for (int i = n; i >= 1; --i) {
                cur += psum[i];
                cnt[i] += cur;
            }
            for (int i = 1; i <= n; ++i) {
                press[s[i - 1] - 'a'] += cnt[i];
            }
            for (int i = 0; i < 26; ++i) {
                out.print(press[i] + " ");
            }
            out.println();
        }
    }
}
