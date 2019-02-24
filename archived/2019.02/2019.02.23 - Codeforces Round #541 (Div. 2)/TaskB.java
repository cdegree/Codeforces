package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        a[0] = b[0] = 0;
        long res = 1;
        for (int i = 1; i <= n; ++i) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        for (int i = 1; i <= n; ++i) {
            res += Math.max(0, add(a[i - 1], a[i], b[i - 1], b[i]));
        }
        out.println(res);
    }

    int add(int a1, int a2, int b1, int b2) {
        if (a1 == b1) {
            return Math.min(a2, b2) - a1;
        } else if (a1 < b1) {
            return Math.min(a2, b2) - b1 + 1;
        } else {
            return Math.min(a2, b2) - a1 + 1;
        }

    }
}
