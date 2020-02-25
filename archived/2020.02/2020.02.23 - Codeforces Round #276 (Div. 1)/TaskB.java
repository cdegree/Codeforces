package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeSet;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Arrays.sort(a);
        int ans = 0;
        int N = 2000005;
        int[] preMax = new int[N];
        boolean[] occur = new boolean[N];
        for (int i = 0; i < n; ++i) {
            occur[a[i]] = true;
        }
        int mx = 0;
        int last = 0;
        for (int i = 1; i < N; ++i) {
            preMax[i] = last;
            if (occur[i]) {
                last = i;
            }
        }
        for (int v : a) {
            mx = Math.max(mx, v);
        }
        for (int j = 0; j < n; ++j) {
            if (j > 0 && a[j] == a[j - 1]) {
                continue;
            }
            for (int v = a[j] + a[j]; ; v += a[j]) {
                ans = Math.max(ans, preMax[v] % a[j]);
                if (v > mx) {
                    break;
                }
            }
        }
        out.println(ans);
    }
}
