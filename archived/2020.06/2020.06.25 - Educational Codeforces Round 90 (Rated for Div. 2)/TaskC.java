package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            char[] s = in.next().toCharArray();
            int n = s.length;
            int[] diff = new int[n + 1];
            int mn = 0;
            for (int i = 0; i < n; ++i) {
                diff[i + 1] = diff[i] + ((s[i] == '+') ? 1 : -1);
                mn = Math.min(mn, diff[i + 1]);
            }
            if (mn >= 0) {
                out.println(n);
            } else {
                long res = n;
                int min = 0;
                for (int i = 1; i <= n; ++i) {
                    if (min > diff[i]) {
                        min = diff[i];
                        res += i;
                    }
                }
                out.println(res);
            }
        }
    }
}
