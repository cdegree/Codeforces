package current;

import algorithms.ArrayUtils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskA {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int d = in.nextInt();
        int m = in.nextInt();
        Vector<Long> less = new Vector<>(n);
        Vector<Long> more = new Vector<>(n);
        int[] a = in.nextIntArray(n);
        for (int x : a) {
            if (x <= m) {
                less.add(1L * x);
            } else {
                more.add(1L * x);
            }
        }
        less.sort(Comparator.comparingLong(o -> -o));
        more.sort(Comparator.comparingLong(o -> -o));
        long res = 0;
        for (int i = 1; i < more.size(); ++i) {
            more.set(i, more.get(i - 1) + more.get(i));
        }
        for (int i = 1; i < less.size(); ++i) {
            less.set(i, less.get(i - 1) + less.get(i));
        }

        for (int i = 0; i < more.size(); ++i) {
            long need = 1L * i * d;
            need -= more.size() - i - 1;
            int x = less.size();
            long cur = more.get(i);
            if (need > x) continue;
            if (need > 0) {
                x -= need;
            }
            if (x >= 0) {
                if (x > 0) {
                    cur += less.get(x - 1);
                }
                res = Math.max(res, cur);
            }
        }
        if (less.size() > 0) {
            res = Math.max(res, less.get(less.size() - 1));
        }
        out.println(res);
    }
}
