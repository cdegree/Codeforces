package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int m = n + n;
            int[] p = in.nextIntArray(m);
            Vector<Integer> cnt = new Vector<>();
            for (int i = 0; i < m; ++i) {
                int j = i;
                while (j + 1 < m && p[j + 1] < p[i]) ++j;
                cnt.add(j - i + 1);
                i = j;
            }
            boolean[] available = new boolean[n + 1];
            available[0] = true;
            for (int i = 0; i < cnt.size(); ++i) {
                for (int j = n; j - cnt.get(i) >= 0; --j) {
                    available[j] = available[j] | available[j - cnt.get(i)];
                }
            }
            out.println(available[n] ? "YES" : "NO");
        }
    }
}
