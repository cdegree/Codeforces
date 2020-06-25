package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskF2 {
    final int N = 200005;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int[] dp = new int[N];
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);

            int lastIndex = -1;
            for (int i = 0; i < n; ++i) {
                if (lastIndex == -1 || dp[lastIndex] <= a[i]) {
                    dp[++lastIndex] = a[i];
                } else {
                    int left = 0;
                    int right = lastIndex;
                    int ans = -1;
                    while (left <= right) {
                        int mid = (left + right) / 2;
                        if (dp[mid] > a[i]) {
                            ans = mid;
                            right = mid - 1;
                        } else {
                            left = mid + 1;
                        }
                    }
                    dp[ans] = a[i];
                }
            }
            out.println(n - (lastIndex + 1));
        }
    }
}
