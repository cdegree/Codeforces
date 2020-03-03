package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = in.nextLongArray(n);
        int[] b = in.nextIntArray(n);
        long mod = 998244353;
        Vector<Long> c = new Vector<>();
        for (int i = 0; i < n; ++i) {
            c.add(a[i] * (i + 1) * (n - i));
        }
        //ArrayUtils.mergeSort(a);
        Collections.sort(c);
        ArrayUtils.mergeSort(b);
        long res = 0;
        for (int i = 0; i < n; ++i) {
            res += c.get(i) % mod * b[n - i - 1];
            if (res > mod) {
                res %= mod;
            }
        }
        res %= mod;
        out.println(res);
    }
}
