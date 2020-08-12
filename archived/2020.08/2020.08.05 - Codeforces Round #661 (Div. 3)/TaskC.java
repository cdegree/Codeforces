package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[] a = in.nextIntArray(n);
            int[] cnt = new int[n + 1];
            for (int i = 0; i < n; ++i) cnt[a[i]]++;
            int res = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    int s = a[i] + a[j];
                    int sum = 0;
                    for (int k = 1; k <= s && k <= n; ++k) {
                        if (k < s - k && s - k >= 1 && s - k <= n && cnt[k] > 0 && cnt[s - k] > 0) {
                            sum += Math.min(cnt[k], cnt[s - k]);
                        } else if (k == s - k) {
                            sum += cnt[k] / 2;
                        }
                    }
                    res = Math.max(res, sum);
                }
            }
            out.println(res);
        }
    }
}
