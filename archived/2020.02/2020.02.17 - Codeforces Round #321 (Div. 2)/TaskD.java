package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n, m, k;
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[][] extra = new int[n][n];
        for (int i = 0; i < k; ++i) {
            int x = in.nextInt();
            int y = in.nextInt();
            int c = in.nextInt();
            x--;
            y--;
            extra[x][y] = c;
        }
        long[][] dp = new long[1 << n][n];
        for (int i = 0; i < n; ++i) {
            dp[1 << i][i] = a[i];
        }
        long mx = 0;
        for (int mask = 0; mask < (1 << n); ++mask) {
            for (int i = 0; i < n; ++i) {
                if (testBit(mask, i)) {
                    for (int j = 0; j < n; ++j) {
                        if (i != j && !testBit(mask, j)) {
                            int nextMask = mergeBit(mask, j);
                            dp[nextMask][j] = Math.max(dp[nextMask][j], dp[mask][i] + a[j] + extra[i][j]);
                        }
                    }
                }
            }
        }
        for (int mask = 0; mask < (1 << n); ++mask) {
            for (int i = 0; i < n; ++i) {
                if (countBit(mask, n) == m) {
                    mx = Math.max(mx, dp[mask][i]);
                }
            }
        }
        out.println(mx);
    }

    int countBit(int mask, int n) {
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (testBit(mask, i)) {
                cnt++;
            }
        }
        return cnt;
    }

    int mergeBit(int mask, int pos) {
        return mask | (1 << pos);
    }

    boolean testBit(int mask, int pos) {
        return (mask & (1 << pos)) != 0;
    }
}
