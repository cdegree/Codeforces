package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int b = in.nextInt();
        int mod = in.nextInt();
        int[][][] dp = new int[2][m + 1][b + 1];
        int[] a = in.nextIntArray(n);
        dp[0][0][0] = 1;
        for (int it = 0; it < n; ++it) {
            int i = (it + 1) % 2;
            for (int j = 0; j <= m; ++j) {
                Arrays.fill(dp[i][j], 0);
                for (int k = 0; k <= b; ++k) {
                    dp[i][j][k] += dp[i ^ 1][j][k];
                    if (j - 1 >= 0 && k - a[it] >= 0) {
                        dp[i][j][k] += dp[i][j - 1][k - a[it]];
                    }
                    dp[i][j][k] %= mod;
                }
            }
        }
        long res = 0;
        for (int k = 0; k <= b; ++k) {
            res += dp[n % 2][m][k];
            res %= mod;
        }
        out.println(res);
    }
}
