package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] s = in.nextLine().toCharArray();
        char[] t = in.nextLine().toCharArray();
        int[] mark = new int[n];
        for (int i = 0; i < n; ++i) {
            if (s[i] != t[i]) {
                mark[i] = (s[i] == '0') ? 1 : 2;
            }
        }
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < n; ++i) {
            if (mark[i] == 1) ++cnt1;
            if (mark[i] == 2) ++cnt2;
        }
        if (cnt1 != cnt2) {
            out.println(-1);
        } else {
            int n1 = 0;
            int n2 = 0;
            int res = 0;
            for (int i = 0; i < n; ++i) {
                if (mark[i] == 1) {
                    if (n2 > 0) {
                        --n2;
                        ++n1;
                    } else {
                        ++n1;
                    }
                } else if (mark[i] == 2) {
                    if (n1 > 0) {
                        --n1;
                        ++n2;
                    } else {
                        ++n2;
                    }
                }
                res = Math.max(res, n1 + n2);
            }
            out.println(res);
        }
    }
}
