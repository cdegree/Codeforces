package current;

import algorithms.SegmentTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        char[] ch = in.nextLine().toCharArray();
        SegmentTree st = new SegmentTree(ch.length);
        st.build(ch);
        int n = in.nextInt();
        for (int i = 0; i < n; ++i) {
            int l = in.nextInt();
            int r = in.nextInt();
            out.println(st.query(l, r));
        }
        Scanner sc = new Scanner(System.in);
        sc.next();
    }
}
