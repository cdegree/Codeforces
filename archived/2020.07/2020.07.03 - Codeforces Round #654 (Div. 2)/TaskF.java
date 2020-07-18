package current;

import algorithms.SegmentTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        char[] s = in.nextLine().toCharArray();
        SegmentTree st = new SegmentTree(n);
        st.build(s);
        while (q-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            st.update(l, r, 1);
            out.println(st.query(l, r));
        }
    }
}
