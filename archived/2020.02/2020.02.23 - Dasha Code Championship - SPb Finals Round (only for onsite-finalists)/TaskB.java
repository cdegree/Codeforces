package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.TreeSet;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[] a = in.nextLongArray(n);
        long[] b = in.nextLongArray(n);
        TreeSet<Long> candMask = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (a[i] == a[j]) {
                    candMask.add(a[i]);
                }
            }
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            for (long mask : candMask) {
                if ((a[i] | mask) == mask) {
                    res += b[i];
                    break;
                }
            }
        }
        out.println(res);
    }
}
