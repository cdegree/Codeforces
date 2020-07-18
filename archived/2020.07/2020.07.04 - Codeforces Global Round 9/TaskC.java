package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int maxMin = a[0];
            for (int i = 1; i < n - 1; ++i) {
                if (a[i] < a[i - 1]) {
                    maxMin = Math.max(maxMin, a[i]);
                }
            }
            boolean OK = a[0] < a[n - 1];
            out.println(OK ? "YES" : "NO");
        }
    }
}
