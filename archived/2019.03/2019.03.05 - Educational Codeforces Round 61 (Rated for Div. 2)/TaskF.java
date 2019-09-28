package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] s = in.next().toCharArray();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], 505);
            dp[i][i] = 1;
            if (i + 1 < n && s[i] == s[i + 1]) {
                dp[i][i + 1] = 1;
            } else {
                dp[i][i + 1] = 2;
            }
        }
        for (int len = 3; len <= n; ++len) {
            for (int st = 0; st < n; ++st) {
                int i = st;
                int j = st + len - 1;
                if (j >= n) {
                    break;
                }
                for (int k = i; k <= j; ++k) {
                    if (s[i] == s[k]) {
                        int p1 = 0;
                        if (i+1<= k - 1) {
                            p1 = dp[i+1][k - 1];
                        }
                        int p2 = 0;
                        if (k  <= j) {
                            p2 = dp[k][j];
                        }
                        dp[i][j] = Math.min(dp[i][j], p1 + p2);
                    }else{
                        dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k][j]);
                    }
                }
            }
        }
//        for (int i = 0; i < n; ++i) {
//            for (int j = 0; j < n; ++j) {
//                out.print(dp[i][j] + "\t");
//            }
//            out.println();
//        }

        out.println(dp[0][n - 1]);

    }
}
