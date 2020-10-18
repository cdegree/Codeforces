package current;

import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskD {
    public void solve(int testNumber, InputReader in, PrintWriter out) {

        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] s = in.nextLine().toCharArray();
            int loan = 0;
            int ops = 0;
            for (int i = 0; i + loan < n; ++i) {
                int j = i;
                while (j + 1 < n && s[j + 1] == s[i]) ++j;
                int cnt = j - i + 1;
                if (cnt > 1) {
                    loan -= (cnt - 2);
                    loan = Math.max(loan, 0);
                    ++ops;
                } else {
                    ops++;
                    ++loan;
                }
                i = j;
                //Utils.pf("%d %d = %d", i, j, ops);
            }
            out.println(ops);
        }
    }
}
