package current;

import algorithms.ArrayUtils;
import algorithms.Utils;
import fastio.InputReader;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.InputMismatchException;


public class TaskE {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int res = getOpToTarget(0, n, a, 0);
        out.println(res);
    }

    int getOpToTarget(int left, int right, int[] a, int target) {
        int min = ArrayUtils.minElement(a, left, right);
        int add = (min > target) ? (min - target) : 0;
        int ret = 0;
        //Utils.pf("%d -> %d add = %d", left, right, ret);
        for (int i = left; i < right; ++i) {
            if (a[i] > min) {
                int j = i;
                while (j + 1 < right && a[j + 1] > min) ++j;
                ret += getOpToTarget(i, j + 1, a, min);
                i = j;
            }
        }
        ret = Math.min(ret + add, right - left);
        //Utils.pf("%d -> %d to target = %d need %d", left, right, target, ret);
        return ret;
    }
}
