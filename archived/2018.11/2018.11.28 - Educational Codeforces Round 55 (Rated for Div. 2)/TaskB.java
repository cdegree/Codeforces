package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char[] s = in.next().toCharArray();

        int p0 = 0;
        int p1 = 0;
        int[] l = new int[n];
        int[] r = new int[n];
        int len = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == 'G') {
                int j = i;
                while (j + 1 < n && s[j + 1] == 'G') {
                    ++j;
                }
                l[len] = i;
                r[len] = j;
                len++;

                //out.println(String.format("%d -> %d",i,j));
                i = j+1;
            }
        }
        int res = 0;
        if (len == 0) {
            res = 0;
        } else if (len == 1) {
            res = r[0] - l[0] + 1;
        } else if (len == 2) {
            if (r[0] + 2 == l[1]) {
                res = (r[0] - l[0] + 1) + (r[1] - l[1] + 1);
            } else {
                res = Math.max((r[0] - l[0] + 1), (r[1] - l[1] + 1)) + 1;
            }
        } else {
            for (int i = 0; i < len; ++i) {
                res = Math.max(res, r[i] - l[i] + 1);
                if (i > 0) {
                    if (l[i] - 2 == r[i - 1]) {
                        res = Math.max(res, (r[i - 1] - l[i - 1] + 1) + (r[i] - l[i] + 1)+1);
                    }
                }
            }
        }
        out.println(res);

    }
}
