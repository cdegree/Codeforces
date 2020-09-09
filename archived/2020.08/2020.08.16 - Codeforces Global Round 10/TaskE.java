package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long[][] a = new long[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i % 2 == 0) {
                    a[i][j] = 0;
                } else {
                    a[i][j] = 1L << (i + j);
                }
                out.print(a[i][j] + " ");
            }
            out.println();
        }
        out.flush();
        int q = in.nextInt();
        while (q-- > 0) {
            long k = in.nextLong();
            int i = 0;
            int j = 0;
            while (i < n && j < n) {

                out.println((i + 1) + " " + (j + 1));
                int d = 0;
                if ((k & (1L << (i + j + 1))) != 0) {
                    d = 1;
                }
                if (i % 2 == 1) {
                    d ^= 1;
                }
                if (d == 1) {
                    ++i;
                } else {
                    ++j;
                }
            }
            out.println();
            out.flush();
        }
    }
}
