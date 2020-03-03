package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeMap;
import java.util.TreeSet;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = in.nextIntArray(n);
        long[] p = new long[64];
        p[0] = 1;
        TreeSet<Long> pw = new TreeSet<>();
        pw.add(1L);
        for (int i = 1; i < 60; ++i) {
            p[i] = p[i - 1] * k;
            pw.add(p[i]);
            if (p[i] >= 10000000000000000L) {
                break;
            }
        }
        TreeMap<Long, Long> sumCount = new TreeMap<>();
        long sum = 0;
        long res = 0;
        sumCount.put(0L, 1L);
        for (int i = 0; i < n; ++i) {
            sum += a[i];
            for (long v : pw) {
                long wanted = sum - v;
                long cnt = sumCount.getOrDefault(wanted, 0L);
                //System.out.println(String.format("%d %d %d",p[j],wanted,cnt));
                res += cnt;
            }
            long cnt = sumCount.getOrDefault(sum, 0L);
            sumCount.put(sum, cnt + 1);
        }
        out.println(res);
    }
}
