package current;

import algorithms.BinaryIndexedTree;
import algorithms.SegmentTree;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Vector;


public class TaskC {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = in.nextIntArray1(n);
        Vector<Integer>[] endHere = new Vector[n + 1];
        int[] leftPoint = new int[q + 1];
        long[] ans = new long[q + 1];
        for (int i = 1; i <= n; ++i) {
            endHere[i] = new Vector<>();
        }
        for (int i = 0; i < q; ++i) {
            int l = in.nextInt() + 1;
            int r = n - in.nextInt();
            endHere[r].add(i);
            leftPoint[i] = l;
        }
        BinaryIndexedTree bit = new BinaryIndexedTree(n);
        for (int r = 1; r <= n; ++r) {
            a[r] = r - a[r];
            if (a[r] >= 0) {
                int left = 1;
                int right = r;
                int pv = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (bit.sum(mid) >= a[r]) {
                        left = mid + 1;
                        pv = mid;
                    } else {
                        right = mid - 1;
                    }
                }
                bit.update(1, 1);
                bit.update(pv + 1, -1);
            }
            for (int j : endHere[r]) {
                ans[j] = bit.sum(leftPoint[j]);
            }
        }
        for (int i = 0; i < q; ++i) {
            out.println(ans[i]);
        }

    }
}
