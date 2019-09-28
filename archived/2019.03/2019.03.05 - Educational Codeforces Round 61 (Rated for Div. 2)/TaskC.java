package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] left = new int[q];
        int[] right = new int[q];
        List<Integer>[] fence = new LinkedList[n + 1];
        int[][] dp = new int[q][q];

        for (int i = 0; i < q; ++i) {
            Arrays.fill(dp[i], 0);
            left[i] = in.nextInt();
            right[i] = in.nextInt();
            for (int j = left[i]; j <= right[i]; ++j) {
                if (fence[j] == null) {
                    fence[j] = new LinkedList<>();
                }
                if(fence[j].size()<3) {
                    fence[j].add(i);
                }
            }
        }
        int s = 0;
        for (int i = 1; i <= n; ++i) {
            if (fence[i] != null && !fence[i].isEmpty()) {
                ++s;
                if (fence[i].size() == 1) {
                    int x = fence[i].get(0);
                    for (int j = 0; j < q; ++j) {
                        dp[x][j]++;
                        dp[j][x]++;
                    }
                } else if (fence[i].size() == 2) {
                    int x = fence[i].get(0);
                    int y = fence[i].get(1);
                    dp[x][y]++;
                    dp[y][x]++;
                }
            }
        }
        int res = s - (right[0] - left[0] + 1) - (right[1] - left[1] + 1);
        for (int i = 0; i < q; ++i) {
            for (int j = i + 1; j < q; ++j) {
                res = Math.max(res, s - dp[i][j]);
            }
        }
        out.println(res);
    }
}
