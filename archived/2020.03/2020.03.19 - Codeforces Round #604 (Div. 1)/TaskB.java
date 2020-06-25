package current;

import algorithms.ArrayUtils;
import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Vector<Integer> res = new Vector<>();
        int[] a = in.nextIntArray(4);
        long m = ArrayUtils.sum(a);
        for (int st = 0; st < 4; ++st) {
            res.clear();
            int p = st;
            int[] b = Arrays.copyOf(a, a.length);
            while (true) {
                if (b[p] > 0) {
                    res.add(p);
                    b[p]--;
                    if (p - 1 >= 0 && b[p - 1] > 0) {
                        --p;
                    } else if (p + 1 < 4 && b[p + 1] > 0) {
                        ++p;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (res.size() == m) {
                break;
            }
        }
        if (res.size() == m) {
            out.println("YES");
            for (int i = 0; i < res.size(); ++i) {
                out.print(res.get(i) + " ");
            }
        } else {
            out.println("NO");
        }
    }
}
