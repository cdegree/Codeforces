package current;

import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Vector<Point> points = new Vector<>();
        long x0 = in.nextLong();
        long y0 = in.nextLong();
        long ax = in.nextLong();
        long ay = in.nextLong();
        long bx = in.nextLong();
        long by = in.nextLong();
        long xs = in.nextLong();
        long ys = in.nextLong();
        long t = in.nextLong();
        do {
            points.add(new Point(x0, y0));
            x0 = x0 * ax + bx;
            y0 = y0 * ay + by;
        } while (x0 <= t + xs && y0 <= t + ys);
        long res = 0;
        for (int len = 1; len <= points.size(); ++len) {
            for (int i = 0; i + len <= points.size(); ++i) {
                int j = len + i - 1;
                long cost = points.get(j).x - points.get(i).x + points.get(j).y - points.get(i).y;
                cost += Math.min(Math.abs(points.get(i).x - xs) + Math.abs(points.get(i).y - ys), Math.abs(points.get(j).x - xs) + Math.abs(points.get(j).y - ys));
                if (cost <= t && res < len) {
                    res = len;
                }
            }
        }
        out.println(res);
    }

    class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
