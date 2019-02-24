package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] s = new char[n][m];
        for (int i = 0; i < n; ++i) {
            s[i] = in.next().toCharArray();
        }
        int[][][] dp = new int[2][n + 1][m + 1];
        int[][] cnt = new int[26][m + 1];
        long res = 0;
        int p0 = 0;
        int p1 = 1;
        for (int r = 0; r < n; ++r) {
            for (int j = 0; j < 26; ++j) {
                Arrays.fill(cnt[j], 0);
            }
            for (int j = 0; j < m; ++j) {
                cnt[s[r][j] - 'a'][j]++;
                if (j > 0) {
                    for (int k = 0; k < 26; ++k) {
                        cnt[k][j] += cnt[k][j - 1];
                    }
                }
            }
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    if (isP(cnt, i, j)) {
                        ++dp[p1][i][j];
                    }
                    dp[p1][i][j] += dp[p0][i][j];
                    out.println(String.format("%d -> %d = %d",i,j,dp[p1][i][j]));
                }
            }
            int t = p0;
            p0 = p1;
            p1 = t;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                res += dp[p0][i][j];
            }
        }
        res += n*m;
        out.println(res);
    }

    boolean isP(int[][] cnt, int left, int right) {
        int odd = 0;
        for (int i = 0; i < 26; ++i) {
            int ccnt = cnt[i][right] - (left > 0 ? cnt[i][left - 1] : 0);
            if (ccnt % 2 == 1) {
                ++odd;
            }
        }
        return odd <= 1;
    }
}
