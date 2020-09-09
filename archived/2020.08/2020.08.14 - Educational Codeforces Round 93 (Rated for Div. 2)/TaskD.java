package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int m = 3;
        int[] n = in.nextIntArray(m);
        int[][] a = new int[m][];
        for (int i = 0; i < m; ++i) {
            a[i] = in.nextIntArray(n[i]);
            Arrays.sort(a[i]);
            ArrayUtils.reverse(a[i]);
        }
        long[][][] dp = new long[n[0] + 1][n[1] + 1][n[2] + 1];
        long res = 0;
        for (int i = 0; i <= n[0]; ++i) {
            for (int j = 0; j <= n[1]; ++j) {
                for (int k = 0; k <= n[2]; ++k) {
                    if (i > 0) dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);
                    if (j > 0) dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k]);
                    if (k > 0) dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j][k - 1]);
                    if (i > 0 && j > 0)
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k] + a[0][i - 1] * a[1][j - 1]);
                    if (i > 0 && k > 0)
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k - 1] + a[0][i - 1] * a[2][k - 1]);
                    if (j > 0 && k > 0)
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k - 1] + a[1][j - 1] * a[2][k - 1]);
                    res = Math.max(res, dp[i][j][k]);
                }
            }
        }
        out.println(dp[n[0]][n[1]][n[2]]);
    }
}
