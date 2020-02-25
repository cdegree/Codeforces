package current;

import algorithms.ArrayUtils;
import algorithms.BinaryIndexedTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;


public class TaskF {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] x = in.nextIntArray(n);
        int[] v = in.nextIntArray(n);
        v = ArrayUtils.shrink(v);
        Point[] points = new Point[n];
        for (int i = 0; i < n; ++i) {
            points[i] = new Point(x[i], v[i]);
        }
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));
        BinaryIndexedTree Vcnt = new BinaryIndexedTree(n);
        BinaryIndexedTree VXsum = new BinaryIndexedTree(n);
        long res = 0;
        for (int i = n - 1; i >= 0; --i) {
            int X = points[i].x;
            int V = points[i].v;
            long cnt = Vcnt.queryRange(V, n);
            long sum = VXsum.queryRange(V, n);
            res += sum - X * cnt;
            Vcnt.update(V, 1);
            VXsum.update(V, X);
        }
        out.println(res);
    }

    class Point {
        int x;
        int v;

        public Point(int x, int v) {
            this.x = x;
            this.v = v;
        }
    }
}
