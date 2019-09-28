package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        long res = 0;
        long r = 0;
        long d = 0;
        for (int i = 0; i < n; ++i) {
            d += a[i] / 2;
            r += a[i] % 2;
            long mn = Math.min(r, d);
            res += mn;
            r -= mn;
            d -= mn;
            if (d > 0) {
                res += d * 2 / 3;
                d = d * 2 % 3;
                r += d;
                d = 0;
            } else {

            }
        }
        out.println(res);
    }
}
