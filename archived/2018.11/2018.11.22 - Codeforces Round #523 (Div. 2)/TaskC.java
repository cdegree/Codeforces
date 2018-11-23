package current;

import algorithms.MathUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskC {
    long res;
    int mod = 1000000007;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(0, n);
        final int N = 1000006;

        int[] cnt = new int[N];
        res = 0;
        cnt[0] = 1;
        for (int i = 0; i < n; ++i) {
            int m = a[i];
            Vector<Integer> r = MathUtil.getDivisors(m);
            Collections.sort(r);
            Collections.reverse(r);
            for (int k : r) {
                cnt[k] = (cnt[k] + cnt[k - 1]) % mod;
            }
        }
        for (int i = 1; i <= n; ++i) {
            res += cnt[i];
        }
        res %= mod;
        out.println(res);
    }

}
