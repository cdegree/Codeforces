package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] a = new int[k][n];
        int[][] pos = new int[k + 1][n + 1];
        for (int j = 0; j < k; ++j) {
            for (int i = 0; i < n; ++i) {
                a[j][i] = in.nextInt() - 1;
                pos[j][a[j][i]] = i;
            }
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            for (int j = 0; j < i; ++j) {
                int pre = a[0][j];
                int cur = a[0][i];
                boolean sameRelation = true;
                for (int p = 1; p < k; ++p) {
                    if (pos[p][pre] > pos[p][cur]) {
                        sameRelation = false;
                    }
                }
                if (sameRelation) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //out.println("dp " + i + " = " + dp[i]);
            res = Math.max(res, dp[i]);
        }
        out.println(res);
    }
}
