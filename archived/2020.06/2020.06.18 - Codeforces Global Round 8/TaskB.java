package current;

import fastio.InputReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;



public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out){
        long k = in.nextLong();
        int[] cnt = new int[11];
        Arrays.fill(cnt, 1);
        int n = 10;
        long cur = 1;
        while (cur < k) {
            for (int i = 0; i < n; ++i) {
                cur = cur / cnt[i] * (cnt[i] + 1);
                cnt[i]++;
                if (cur >= k) {
                    break;
                }
            }
        }
        char[] cf = "codeforces".toCharArray();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                out.print(cf[i]);
            }
        }
        out.println();
    }
}
