package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = in.nextIntArray(n);
            boolean existK = false;
            boolean existP = n == 1;
            for (int i = 0; i < n; ++i) {
                if (a[i] == k) existK = true;
                if (i > 0) {
                    if (a[i] >= k && a[i - 1] >= k) existP = true;
                }
                if (i > 1) {
                    if (a[i] >= k && a[i - 2] >= k) existP = true;
                }
            }
            out.println(existK && existP ? "yes" : "no");
        }
    }
}
