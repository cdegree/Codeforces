package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[][] dp = new int[n][n];
        int inf = 500000;
        int[] a = in.nextIntArray(n);
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], inf);
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; ++len) {
            for (int st = 0; st < n; ++st) {
                int ed = st + len - 1;
                if (ed < n) {
                    for (int mid = st + 1; mid <= ed; ++mid) {
                        if (a[st] == a[mid]) {
                            if (st + 1 <= mid - 1) {
                                dp[st][ed] = Math.min(dp[st][ed], dp[st + 1][mid - 1] + ((mid + 1 > ed) ? 0 : dp[mid + 1][ed]));
                            } else {
                                dp[st][ed] = Math.min(dp[st][ed], 1 + ((mid + 1 > ed) ? 0 : dp[mid + 1][ed]));
                            }
                        } else {
                            dp[st][ed] = Math.min(dp[st][ed], dp[st][mid - 1] + ((mid > ed) ? 0 : dp[mid][ed]));
                        }
                    }
                }
            }
        }
        out.println(dp[0][n - 1]);
    }
}
