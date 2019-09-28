package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        char[][] s = new char[n][m];
        for (int i = 0; i < n; ++i) {
            s[i] = in.next().toCharArray();
        }
        int[] cnt = new int[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s[j][i] == '.') {
                    cnt[i]++;
                }
            }
        }
        int[][] dp1 = new int[m][m + 1];
        int[][] dp2 = new int[m][m + 1];
        int[] nobar = new int[m];
        int inf = 10000000;
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp1[i], inf);
            Arrays.fill(dp2[i], inf);
        }
        dp1[0][1] = cnt[0];
        dp2[0][1] = n - cnt[0];
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (dp1[i - 1][j] < inf) {
                    if (j + 1 <= y && j + 1 <= m) {
                        dp1[i][j + 1] = Math.min(dp1[i][j + 1], dp1[i - 1][j] + cnt[i]);
                    }
                    if (x <= j && j <= y) {
                        dp2[i][1] = Math.min(dp2[i][1], dp1[i - 1][j] + (n - cnt[i]));
                    }
                }
                if (dp2[i - 1][j] < inf) {
                    if (j + 1 <= y && j + 1 <= m) {
                        dp2[i][j + 1] = Math.min(dp2[i][j + 1], dp2[i - 1][j] + (n - cnt[i]));
                    }
                    if (x <= j && j <= y) {
                        dp1[i][1] = Math.min(dp1[i][1], dp2[i - 1][j] + cnt[i]);
                    }
                }
            }
        }
        int res = inf;
        for(int i=x;i<=y&&i<=m;++i){
            res = Math.min(res, dp1[m-1][i]);
            res = Math.min(res, dp2[m-1][i]);
        }
        out.println(res);
    }
}
