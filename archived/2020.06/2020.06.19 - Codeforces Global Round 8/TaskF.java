package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] light = new int[n + 1];
        int[] best = new int[n + 1];
        if (n <= 3) {
            out.println(0);
            out.flush();
            return;
        } else {
            for (int i = 1; i < n; i += 2) {
                best[i] = 1;
            }
            while (true) {
                Vector<Integer> need = new Vector<>();
                for (int i = 1; i < n; i += 2) {
                    if (best[i] == 1 && light[i] == 0) {
                        need.add(i);
                        light[i] = 1;
                    }
                }
                if (need.size() <= 1) {
                    break;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(need.size());
                for (int x : need) {
                    sb.append(" " + x);
                }
                out.println(sb);
                out.flush();
                int off = in.nextInt();
                if (off == -1) {
                    return;
                }
                for (int i = 0; i < need.size(); ++i) {
                    light[off] = 0;
                    ++off;
                    if (off > n) {
                        off = 1;
                    }
                }
            }
            out.println(0);
            out.flush();
            return;
        }
    }
}
