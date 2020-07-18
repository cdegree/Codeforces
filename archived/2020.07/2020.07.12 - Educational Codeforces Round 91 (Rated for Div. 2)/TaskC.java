package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            long x = in.nextLong();
            int[] a = in.nextIntArray(n);
            ArrayUtils.mergeSort(a);
            long cnt = 0;
            long least = a[n - 1];
            int res = 0;
            for (int i = n - 1; i >= 0; --i) {
                ++cnt;
                least = Math.min(least, a[i]);
                if (least * cnt >= x) {
                    ++res;
                    cnt = 0;
                }
            }
            out.println(res);
        }
    }
}
