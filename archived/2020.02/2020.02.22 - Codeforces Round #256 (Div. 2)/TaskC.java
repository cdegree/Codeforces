package current;

import algorithms.RmqSparseTable;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskC {
    int[] a;
    RmqSparseTable rmq = null;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        a = in.nextIntArray(n);
        rmq = new RmqSparseTable(a);
        out.println(solve(0, n, 0));
    }

    int solve(int left, int right, int horizontalPaintedHeight) {
        if (left >= right) {
            return 0;
        }
        int minHeightIndex = rmq.minPos(left, right - 1);
        return Math.min(right - left, a[minHeightIndex] - horizontalPaintedHeight + solve(left, minHeightIndex, a[minHeightIndex]) + solve(minHeightIndex + 1, right, a[minHeightIndex]));
    }
}
