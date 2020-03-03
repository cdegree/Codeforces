package current;

import algorithms.ArrayUtils;
import algorithms.BinaryIndexedTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        a = ArrayUtils.shrink(a);
        BinaryIndexedTree counter = new BinaryIndexedTree(n);
        long[] larger = new long[n];
        for (int i = 0; i < n; ++i) {
            larger[i] = counter.queryRange(a[i] + 1, n);
            counter.update(a[i], 1);
        }
        counter.reset();
        long res = 0;
        for (int i = n - 1; i >= 0; --i) {
            long smaller = counter.queryRange(1, a[i] - 1);
            res += smaller * larger[i];
            counter.update(a[i], 1);
        }
        out.println(res);
    }
}
