package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;


public class TaskB {
    long res = 0;
    PrintWriter ot;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        long x = in.nextLong();
        res = 0;
        char[] s = Long.toString(x).toCharArray();
        char[] d = new char[s.length];
        dfs(0, d, s, true, out);
        ot = out;
        out.println(res);
    }

    void dfs(int p, char[] d, char[] od, boolean match, PrintWriter out) {

        int n = od.length;
        if (p == n) {
            long now = 1;
            int i = 0;
            while (i < n && d[i] == '0') ++i;
            for (; i < n; ++i) {
                now *= (d[i] - '0');
//                out.print(d[i] + " ");
            }
//            out.println();
            res = Math.max(res, now);
            return;
        }
        if (match) {
            for (int i = '0'; i <= od[p]; ++i) {
                d[p] = (char) i;
                dfs(p + 1, d, od, match && i == od[p], out);
            }
        } else {
            d[p] = 9 + '0';
            dfs(p + 1, d, od, match, out);
        }
    }
}
