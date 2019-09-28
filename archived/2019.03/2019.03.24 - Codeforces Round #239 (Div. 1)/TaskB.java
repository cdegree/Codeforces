package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] dp = new int[n];
        int[] p = in.readIntArray(n);
        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < n; ++i) {
            dp[i] = 2;
            for (int j = p[i] - 1; j < i; ++j) {
                dp[i] += dp[j];
                if (dp[i] >= mod) {
                    dp[i] -= mod;
                }
            }
            res = (res + dp[i]) % mod;
        }
        out.println(res);
    }
}
