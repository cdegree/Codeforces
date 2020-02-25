package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] b = new int[n + 1];
            int[] pos = new int[2 * n + 1];
            int[] a = new int[2 * n + 1];
            boolean[] occur = new boolean[n * 2 + 1];
            for (int i = 1; i <= n; ++i) {
                b[i] = in.nextInt();
                occur[b[i]] = true;
                pos[b[i]] = 2 * i - 1;
            }
            Vector<Integer> others = new Vector<>();
            for (int i = 1; i <= 2 * n; ++i) {
                if (!occur[i]) {
                    others.add(i);
                }
            }
            Collections.sort(others);
            Arrays.sort(b, 1, n + 1);
            boolean OK = true;
            for (int i = 1; i <= n; ++i) {
                int other = others.get(i - 1);
                if (b[i] > others.get(i - 1)) {
                    OK = false;
                    break;
                } else {
                    pos[other] = pos[b[i]] + 1;
                }
            }
            if (!OK) {
                out.println(-1);
                continue;
            }
            for (int i = 1; i <= 2 * n; ++i) {
                a[pos[i]] = i;
            }

            boolean move = false;
            do {
                move = false;
                for (int i = 2; i <= 2 * n; i += 2) {
                    for (int j = i + 2; j <= 2 * n; j += 2) {
                        int p = i;
                        int q = j;
                        if (a[p] > a[q] && a[p - 1] < a[q] && a[q - 1] < a[p]) {
                            int tmp = a[p];
                            a[p] = a[q];
                            a[q] = tmp;
                            move = true;
                        }
                    }
                }
            } while (move);


            for (int i = 1; i <= 2 * n; ++i) {
                out.print(a[i] + " ");
            }


            out.println();
        }
    }
}
