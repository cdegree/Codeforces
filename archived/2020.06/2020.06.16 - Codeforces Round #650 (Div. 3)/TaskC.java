package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int tt = in.nextInt();
        while (tt-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            char[] c = in.nextLine().toCharArray();
            int i = 0;
            int res = 0;
            while (i < n) {
                int idx = getRemoteOne(c, i, k);
                if (idx == -1) {
                    res++;
                    c[i] = '1';
                    i = i + k + 1;
                } else {
                    i = idx + k + 1;
                }
            }
            out.println(res);
        }
    }

    int getRemoteOne(char[] c, int pos, int k) {
        int ret = -1;
        for (int i = pos; i <= pos + k & i < c.length; ++i) {
            if (c[i] == '1') {
                ret = i;
            }
        }
        return ret;
    }
}
