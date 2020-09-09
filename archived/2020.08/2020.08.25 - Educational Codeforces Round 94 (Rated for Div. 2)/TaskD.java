package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray1(n);

            long[][] smaller = new long[n + 1][n + 1];
            long[][] bigger = new long[n + 1][n + 1];

            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (a[i] == j) {
                        smaller[i][j]++;
                    }
                    smaller[i][j] += smaller[i - 1][j];
                }
            }
            for (int i = n; i >= 1; --i) {
                for (int j = n; j >= 1; --j) {
                    if (a[i] == j) {
                        bigger[i][j]++;
                    }
                    if (i + 1 <= n) bigger[i][j] += bigger[i + 1][j];
                }
            }
            long res = 0;
            for (int i = 1; i <= n; ++i) {
                for (int j = i + 1; j <= n; ++j) {
                    if (i - 1 >= 0 && j + 1 <= n) {
                        res += smaller[i - 1][a[j]] * bigger[j + 1][a[i]];
                    }
                }
            }
            out.println(res);

        }
    }
}
