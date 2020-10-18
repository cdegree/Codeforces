package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = in.nextIntArray(n);
            ArrayUtils.mergeSort(a);
            long res = 0;
            for (int i = n - 1; i >= 0; --i) {
                res += a[i];
                if (k <= 0) break;
                --k;
            }
            out.println(res);
        }
    }
}
