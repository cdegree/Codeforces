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
            int[] a = in.nextIntArray(n);
            int[] b = in.nextIntArray(n);
            int minA = ArrayUtils.minElement(a, 0, n);
            int minB = ArrayUtils.minElement(b, 0, n);
            long sum = 0;
            for (int i = 0; i < n; ++i) {
                sum += Math.max(a[i] - minA, b[i] - minB);
            }
            out.println(sum);
        }
    }

}
