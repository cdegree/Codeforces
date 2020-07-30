package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            char[] a = in.nextLine().toCharArray();
            char[] b = in.nextLine().toCharArray();
            Vector<Integer> res = new Vector<>();
            for (int i = 0; i < n; ++i) {
                int j = n - i - 1;
                if (j == 0) {
                    if (a[0] != b[0]) {
                        res.add(1);
                        break;
                    }
                }
                if (a[0] == b[j]) {
                    res.add(1);
                    if (a[0] == '1') a[0] = '0';
                    else a[0] = '1';
                }
                res.add(j + 1);
                for (int k = 0; k < j + 1; ++k) {
                    if (a[k] == '1') a[k] = '0';
                    else a[k] = '1';
                }
                ArrayUtils.reverse(a, 0, j + 1);
            }
            out.print(res.size() + " ");
            for (int i = 0; i < res.size(); ++i) {
                out.print(res.get(i) + " ");
            }
            out.println();
        }
    }
}
