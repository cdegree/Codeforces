package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];
        int[][] dp1 = new int[n][m];
        int[][] dp2 = new int[n][m];
        int[][] dp3 = new int[n][m];
        int[][] dp4 = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                a[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i > 0) {
                    dp1[i][j] = Math.max(dp1[i][j], dp1[i - 1][j]);
                }
                if (j > 0) {
                    dp1[i][j] = Math.max(dp1[i][j], dp1[i][j - 1]);
                }
                dp1[i][j] += a[i][j];
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < m; ++j) {
                if (i < n - 1) {
                    dp2[i][j] = Math.max(dp2[i][j], dp2[i + 1][j]);
                }
                if (j > 0) {
                    dp2[i][j] = Math.max(dp2[i][j], dp2[i][j - 1]);
                }
                dp2[i][j] += a[i][j];
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                if (i < n - 1) {
                    dp3[i][j] = Math.max(dp3[i][j], dp3[i + 1][j]);
                }
                if (j < m - 1) {
                    dp3[i][j] = Math.max(dp3[i][j], dp3[i][j + 1]);
                }
                dp3[i][j] += a[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = m - 1; j >= 0; --j) {
                if (i > 0) {
                    dp4[i][j] = Math.max(dp4[i][j], dp4[i - 1][j]);
                }
                if (j < m - 1) {
                    dp4[i][j] = Math.max(dp4[i][j], dp4[i][j + 1]);
                }
                dp4[i][j] += a[i][j];
            }
        }
        int res = 0;
        for (int i = 1; i < n - 1; ++i) {
            for (int j = 1; j < m - 1; ++j) {
                res = Math.max(res, dp1[i][j - 1] + dp3[i][j + 1] + dp2[i + 1][j] + dp4[i - 1][j]);
                res = Math.max(res, dp1[i - 1][j] + dp3[i + 1][j] + dp2[i][j - 1] + dp4[i][j + 1]);
            }
        }
        out.println(res);
    }
}
