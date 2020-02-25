package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int d = in.nextInt();
        long m = 1;
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = i;
        }
        for (int i = 0; i < d; ++i) {
            m *= k;
            if (m >= n) {
                m = n;
            }
        }
        if (n > m) {
            out.println(-1);
        } else {
            long p = 1;
            for (int i = 0; i < d; ++i) {
                p *= k;
                for (int j = 0; j < n; ++j) {
                    out.print((a[j] % k) + 1 + " ");
                    a[j] /= k;
                }
                out.println();
            }
        }
    }
}
