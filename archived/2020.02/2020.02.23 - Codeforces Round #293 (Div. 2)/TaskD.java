package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        double p = in.nextDouble();
        int t = in.nextInt();
        double[][] dp = new double[t + 1][n + 1];
        dp[0][n] = 1;

        for (int i = 1; i <= t; ++i) {
            for (int j = n; j >= 0; --j) {
                dp[i][j] += dp[i - 1][j] * (1 - p);
                if (j + 1 <= n) dp[i][j] += dp[i - 1][j + 1] * p;
            }
        }
        double res = n;
        for (int i = 0; i <= n; ++i) {
            res -= dp[t][i] * i;
        }
        out.println(res);
    }
}
