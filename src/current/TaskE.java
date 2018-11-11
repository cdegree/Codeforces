package current;

import algorithms.SegmentTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        String s = in.next();
        int m = 26;
        int[][] cnt = new int[m][n + 1];
        for (int i = 0; i < s.length(); ++i) {
            cnt[s.charAt(i) - 'a'][i + 1] = 1;
        }
        SegmentTree[] st = new SegmentTree[m];
        for (int i = 0; i < m; ++i) {
            st[i] = new SegmentTree(n);
            st[i].build(1, cnt[i]);
        }
        int[] tcnt = new int[m];
        while (q-- > 0) {
            int from = in.nextInt();
            int to = in.nextInt();
            int k = in.nextInt();
            for (int i = 0; i < m; ++i) {
                tcnt[i] = st[i].query(1, from, to);
//                if (tcnt[i] > 0) out.println(String.format("[%d,%d]cnt[%d] = %d", from, to, i, tcnt[i]));
            }
            if (k == 1) {
                int pos = from;
                for (int i = 0; i < m; ++i) {
                    if (tcnt[i] > 0) {

                        st[i].update(1, from, to, 0);
                        st[i].update(1, pos, pos + tcnt[i] - 1, 1);
                        pos = pos + tcnt[i];
                    }
                }
            } else {
                int pos = to;
                for (int i = 0; i < m; ++i) {
                    if (tcnt[i] > 0) {
                        st[i].update(1, from, to, 0);
                        st[i].update(1, pos - tcnt[i] + 1, pos, 1);
                        pos = pos - tcnt[i];
                    }
                }
            }
        }
        for (int i = 1; i <= n; ++i) {
            int id = -1;
            for (int j = 0; j < m; ++j) {
                if (st[j].query(1, i, i) > 0) {
                    id = j;
                    break;
                }
            }
            if (id != -1) {
                char c = (char) (id + 'a');
                out.print(c);
            } else {
                out.print("What??????");
            }
        }
    }
}
