package current;

import algorithms.ArrayUtils;
import algorithms.BitUtil;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] s = new char[n][];
        for (int i = 0; i < n; ++i) {
            s[i] = in.nextLine().toCharArray();
        }
        if (n == 1 || m == 1) {
            out.println(0);
        } else if (n >= 4) {
            out.println(-1);
        } else {
            int[][] dp = new int[m][(1 << n)];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dp[i], 1 << 30);
            }
            for (int mask = 0; mask < (1 << n); ++mask) {
                dp[0][mask] = n - count(mask, n, s, 0);
            }
            for (int i = 1; i < m; ++i) {
                for (int newMask = 0; newMask < (1 << n); ++newMask) {
                    int count = n - count(newMask, n, s, i);
                    for (int mask = 0; mask < (1 << n); ++mask) {
                        if (OK(mask, newMask, n)) {
                            dp[i][newMask] = Math.min(dp[i][newMask], dp[i - 1][mask] + count);
                        }
                    }
                }
            }
            int res = ArrayUtils.minElement(dp[m - 1]);
            out.println(res);
        }
    }

    boolean OK(int mask1, int mask2, int m) {
        if (m == 2) {
            return (Integer.bitCount(mask1^mask2)) % 2 == 1;
        } else {
            int sum1 = Integer.bitCount((mask1 & 3) ^ (mask2 & 3));
            int sum2 = Integer.bitCount((mask1 & 6) ^ (mask2 & 6));
            return (sum1 % 2 == 1) && (sum2 % 2 == 1);
        }
    }

    int getBit(int mask, int pos) {
        return BitUtil.testBit(mask, pos) ? 1 : 0;
    }

    int count(int mask, int m, char[][] s, int pos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            sb.append(s[i][pos]);
        }
        int rmask = Integer.parseInt(new String(sb), 2);
        return Integer.bitCount(rmask ^ mask);
    }

    void pf(char[][] s, int n, int m) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                System.out.print(s[i][j]);
            }
            System.out.println(" " + count(4, m, s, i));
        }
    }
}
