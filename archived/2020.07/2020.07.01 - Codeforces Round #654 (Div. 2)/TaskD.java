package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            int[][] a = new int[n][n];
            for (int i = 0; i < n && k > 0; ++i) {
                a[i][i] = 1;
                --k;
            }
            if (k > 0) {
                for (int p = 1; p < n && k > 0; ++p) {
                    for (int j = 0; j < n && k > 0; ++j) {
                        a[(p + j) % n][j] = 1;
                        --k;
                    }
                }
            }
            int rmin = n;
            int rmax = 0;
            for (int i = 0; i < n; ++i) {
                int sum = 0;
                for (int j = 0; j < n; ++j) {
                    sum += a[i][j];
                }
                rmin = Math.min(rmin, sum);
                rmax = Math.max(rmax, sum);
            }
            int cmin = n;
            int cmax = 0;
            for (int j = 0; j < n; ++j) {
                int sum = 0;
                for (int i = 0; i < n; ++i) {
                    sum += a[i][j];
                }
                cmin = Math.min(cmin, sum);
                cmax = Math.max(cmax, sum);
            }
            int res = (rmax - rmin) * (rmax - rmin) + (cmax - cmin) * (cmax - cmin);
            out.println(res);
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    out.print(a[i][j]);
                }
                out.println();
            }
        }
    }
}
