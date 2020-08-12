package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] l = new int[n];
            int[] r = new int[n];
            int[] a = new int[n + n];
            for (int i = 0; i < n; ++i) {
                a[i] = l[i] = in.nextInt();
                a[i + n] = r[i] = in.nextInt();
            }
            a = ArrayUtils.shrink(a);
            int m = a.length + 1;
            for (int i = 0; i < n; ++i) {
                l[i] = a[i];
                r[i] = a[i + n];
                //m = Math.max(m, Math.max(l[i], r[i]));
            }
            int[][] dp = new int[m][m];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dp[i], -1);
            }
            Vector<Integer>[] lg = new Vector[m];

            for (int i = 0; i < n; ++i) {
                if (lg[l[i]] == null) {
                    lg[l[i]] = new Vector<>();
                }
                lg[l[i]].add(r[i]);
            }
            out.println(getMaxSubset(1, m - 1, dp, lg));
        }
    }

    int getMaxSubset(int l, int r, int[][] dp, Vector<Integer>[] lg) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        dp[l][r] = 0;
        int haveEquals = 0;
        if (lg[l] != null) {
            haveEquals = (lg[l].indexOf(r) != -1) ? 1 : 0;
            for (int i = 0; i < lg[l].size(); ++i) {
                int right = lg[l].get(i);
                if (right < r) {
                    dp[l][r] = Math.max(dp[l][r], haveEquals + getMaxSubset(l, right, dp, lg) + ((right + 1 <= r) ? getMaxSubset(right + 1, r, dp, lg) : 0));
                }
            }
        }
        dp[l][r] = Math.max(dp[l][r], haveEquals + ((l + 1 <= r) ? getMaxSubset(l + 1, r, dp, lg) : 0));
        return dp[l][r];
    }
}
