package current;

import algorithms.BitUtil;
import algorithms.SegmentTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n + 1];
        int[][] b = new int[n + 2][31];
        int[] l = new int[m];
        int[] r = new int[m];
        int[] q = new int[m];
        for (int i = 0; i < m; ++i) {
            l[i] = in.nextInt();
            r[i] = in.nextInt();
            q[i] = in.nextInt();
            for (int j = 0; j < 30; ++j) {
                if (BitUtil.testBit(q[i], j)) {
                    b[l[i]][j]++;
                    b[r[i] + 1][j]--;
                }
            }
        }
        for (int j = 0; j < 30; ++j) {
            int cnt = 0;
            for (int i = 1; i <= n; ++i) {
                cnt += b[i][j];
                int bit = ((cnt > 0) ? 1 : 0) << j;
                a[i] |= bit;
            }
        }
        SegmentTree st = new SegmentTree(n);
        st.build(a);
        boolean OK = true;
        for (int i = 0; i < m; ++i) {
            if (q[i] != st.query(l[i], r[i])) {
                OK = false;
            }
        }
        if (OK) {
            out.println("YES");
            for (int i = 1; i <= n; ++i) {
                out.print(a[i] + " ");
            }
        } else {
            out.println("NO");
        }
    }
}
