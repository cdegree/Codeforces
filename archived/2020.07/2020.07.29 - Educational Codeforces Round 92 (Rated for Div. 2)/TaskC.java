package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            char[] s = in.nextLine().toCharArray();
            int n = s.length;
            if (n <= 2) {
                out.println(0);
            } else {
                int[] cnt = new int[10];
                for (char c : s) {
                    cnt[c - '0']++;
                }
                int res = n;
                for (int i = 0; i < 10; ++i) {
                    res = Math.min(res, n - cnt[i]);
                }
                for (int a = 0; a <= 9; ++a) {
                    for (int b = 0; b <= 9; ++b) {
                        if (a == b) continue;
                        boolean first = true;
                        int longest = 0;
                        for (int i = 0; i < n; ++i) {
                            char c = (char) (s[i] - '0');
                            if (first) {
                                if (a == c) {
                                    first = !first;
                                }
                            } else {
                                if (b == c) {
                                    first = !first;
                                    longest += 2;
                                }
                            }
                        }
                        res = Math.min(res, n - longest);
                    }
                }
                out.println(res);
            }
        }
    }
}
