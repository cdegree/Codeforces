package current;

import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        long r1 = in.nextLong();
        long r2 = in.nextLong();
        long r3 = in.nextLong();
        long d = in.nextLong();
        long[] a = in.nextLongArray(n);
        long res = r3 * n;
        long[][] dp = new long[n][3];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], 1L << 61);
        }
        dp[0][0] = Math.min(r1 * a[0] + r3, r1 * a[0] + r1 + d + d + r1);
        dp[0][1] = Math.min(r2, r1 * a[0] + r1);
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.min(dp[i][0], dp[i - 1][0] + d + r1 * a[i] + r3);
            dp[i][0] = Math.min(dp[i][0], dp[i - 1][0] + d + r2 + d + d + r3);
            dp[i][0] = Math.min(dp[i][0], dp[i - 1][0] + d + r1 * a[i] + r1 + d + d + r1);
            dp[i][0] = Math.min(dp[i][0], dp[i - 1][1] + d + r1 * a[i] + r3 + d + r1 + d);
            dp[i][0] = Math.min(dp[i][0], dp[i - 1][1] + d + r1 * a[i] + r1 + d + r1 + d + r1);
            dp[i][0] = Math.min(dp[i][0], dp[i - 1][1] + d + r2 + d + r1 + d + r1);
            dp[i][2] = Math.min(dp[i][2], dp[i - 1][1] + d + r1 * a[i] + r3 + d + r1);
            dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + d + r1 * a[i] + r1);
            dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + d + r2);
            dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + d + r1 * a[i] + r1 + d + r1 + d);
            dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + d + r2 + d + r1 + d);
        }
        for (int i = 0; i < n; ++i) {
            //Utils.pf("%d %d %d", dp[i][0], dp[i][1], dp[i][2]);
        }
        out.println(Math.min(dp[n - 1][0], dp[n - 1][2]));
    }
}
