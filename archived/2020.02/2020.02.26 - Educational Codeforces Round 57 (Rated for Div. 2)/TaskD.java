package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] s = in.nextLine().toCharArray();
        int[] a = in.nextIntArray(n);
        long[][] dp = new long[n + 1][5];
        long inf = 1L << 62;
        char[] hard = "hard".toCharArray();
        for (int i = 0; i <= n; ++i) for (int j = 0; j < 5; ++j) dp[i][j] = inf;
        dp[0][0] = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (dp[i][j] < inf) {
                    if (s[i] == hard[j]) {
                        dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j]);
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + a[i]);
                    } else {
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                    }
                }
            }
        }
        out.println(ArrayUtils.minElement(dp[n], 0, 4));
    }
}
